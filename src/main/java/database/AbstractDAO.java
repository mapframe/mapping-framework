    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @param <T>
 */
public abstract class AbstractDAO<T extends Document> {
        
    public abstract Integer create(T t);
    
    public abstract void update(T t);
    
    public abstract void delete(T t);
        
    public abstract T find(String id);

    public abstract Collection<T> findAll();
    
    protected abstract org.bson.Document reset_doc(String id);
    
    protected abstract Boolean has_next();
    
    protected abstract Object next();
    
    protected abstract org.bson.Document process(T t);
    
    protected T build_object(String id) {
        org.bson.Document doc = reset_doc(id);
        T root_object = null;
        Map<Class, Object> map = new HashMap<>();
        if ( has_next() ) {
            root_object = (T) next();
            map.put(root_object.getClass(), root_object);
        } 
        while ( has_next() ) {
            Object obj = next();
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
        T root_object = null;
        Map<Class, Object> map = new HashMap<>();
        if ( has_next() ) {
            root_object = (T) next();
            map.put(root_object.getClass(), root_object);
            root_objects.add(root_object);
        } 
        while ( has_next() ) {
            Object obj = next();
            if ( obj.getClass().equals(root_object.getClass()) ) { // another root
                root_object = (T) obj;
                root_objects.add(root_object);
            } else {
                Class parent_class = p_class(obj.getClass());
                Object parent_object = map.get(parent_class);
                addChild(parent_object, obj);
            }
            map.put(obj.getClass(), obj); 
        } 
            
        return root_objects;
    }
    
    private Class p_class(Class<?> c) {
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
                            Collection<database.Document> list =  (Collection<database.Document>) f.get(parent_object);
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
    
    protected org.bson.Document build_doc(T obj) {
        org.bson.Document doc = new org.bson.Document();
        if (obj != null) {
            doc = process(obj);
            for (T child_obj : composition(obj)) {
                doc.append(child_obj.getClass().getName(), build_doc(child_obj));
            }
        }
        return doc;
    }

    protected List<T> composition(T obj){
        Class obj_class = obj.getClass();
        Field[] fields = obj_class.getDeclaredFields();
        List<T> objects = new ArrayList();
        for (Field f : fields) {
            try {
                if (Document.class.isAssignableFrom(f.getType())){
                    boolean accessible = f.isAccessible();
                    if (!accessible)
                    {		
                        f.setAccessible(true);
                    }
                    objects.add((T) f.get(obj));
 		    f.setAccessible(accessible);
                } else if (f.getGenericType() instanceof ParameterizedType) { // se for uma collection
		    ParameterizedType pt = (ParameterizedType) f.getGenericType(); // descobre o tipo parametrizado (Collection<PALESTRA>)
		    Type[] types = pt.getActualTypeArguments();
		    for (Type tp: types) {
		        Class<?> clzz = (Class<?>) tp;
		        if (database.Document.class.isAssignableFrom(clzz)) { // verifica se o obj eh do tipo Document
		            boolean accessible = f.isAccessible();
		            if (!accessible)
		                f.setAccessible(true);
		            try {
		                objects.add((T) f.get(obj)); // tenta inserir o objeto dentro do outro (no caso isso aqui é o obj que a funcao vai retornar (f.get(t)), porque isso vai ser feito na build_obj acho eu)
		            } 
		            catch (ClassCastException ex) {
		                for (Document d : (Collection<Document>) f.get(obj))
		                    objects.add((T) d);
		            }
		            f.setAccessible(accessible); 
		        }
		    }
                }
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return objects;
    }
    
}
