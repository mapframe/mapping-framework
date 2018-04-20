    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Lecture;
import model.dao.relacional.mysql.EventoDAOMySQL;

/**
 *
 * @author isabella
 * @param <T>
 */
public abstract class AbstractDAO<T extends Document> {
        
    protected abstract Integer create(T t);
    
    protected abstract void update(T t);
    
    protected abstract void delete(T t);
        
    protected abstract T find(String id);

    protected abstract Collection<T> findAll();
    
    protected abstract org.bson.Document reset_doc(String id);
    
    protected abstract Boolean has_next(org.bson.Document doc);
    
    protected abstract Object next(org.bson.Document doc);
    
    protected abstract Boolean has_root(org.bson.Document doc);
    
    protected T build_object(String id) {
        org.bson.Document doc = reset_doc(id);
        T root_object = null;
        Map<Class, Object> map = new HashMap<>();
        if ( has_next(doc) ) {
            root_object = (T) next(doc);
            map.put(root_object.getClass(), root_object);
        } 
        while ( has_next(doc) ) {
            Object obj = next(doc);
            Class parent_class = p_class(obj.getClass());
            Object parent_object = map.get(parent_class);
            addChild(parent_object, obj);
            map.put(obj.getClass(), obj);  
        } 
        return root_object;
    }
    
    protected Collection<T> build_objects() {
        org.bson.Document doc = reset_doc(null); // all documents
        Collection<T> root_objects = new ArrayList<>();
        while ( has_root(doc) ) {
            T root_object = null;
            Map<Class, Object> map = new HashMap<>();
            if ( has_next(doc) ) {
                root_object = (T) next(doc);
                map.put(root_object.getClass(), root_object);
            } 
            while ( has_next(doc) ) {
                Object obj = next(doc);
                Class parent_class = p_class(obj.getClass());
                Object parent_object = map.get(parent_class);
                addChild(parent_object, obj);
                map.put(obj.getClass(), obj);  
            } 
            root_objects.add(root_object);
        }
        return root_objects;
    }
    
    public static void main(String[] args) {
        EventoDAOMySQL edao = new EventoDAOMySQL();
        edao.build_object("1");
       //Lecture l = new Lecture();
       //p_class(l.getClass());
       
    }

    private static Class p_class(Class<?> c) {
        Class parent = null;
        for (Field f: c.getDeclaredFields()) {
            try {
                if ( f.getName().equals("parent_object") )
                    parent = Class.forName(f.getType().getName());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return parent;
    }

    private void addChild(Object parent_object, Object child_object) {
        Class parent_class = parent_object.getClass();
        Class child_class = child_object.getClass();
        
        for (Field f: parent_class.getDeclaredFields()) {                
            boolean accessible = f.isAccessible();
            if (!accessible)
                    f.setAccessible(true);
            
            if (f.getType().getName().equals(child_class.getTypeName())) {
                try {
                   f.set(parent_object, child_object);                
                   f.setAccessible(accessible);
                   return;
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                   Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                if (Collection.class.isAssignableFrom(f.getType())) {                       
                    ParameterizedType listType = (ParameterizedType) f.getGenericType();
                    if (listType.getActualTypeArguments()[0].getTypeName().equals(child_class.getTypeName())) {
                        try {
                            Collection<banco.Document> list =  (Collection<banco.Document>) f.get(parent_object);
                            list.add((Document) child_object);
                            f.setAccessible(accessible); 
                            return;
                        } catch (IllegalArgumentException | IllegalAccessException ex) {
                            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                
            }                
            f.setAccessible(accessible);
        }
    }
    
}
