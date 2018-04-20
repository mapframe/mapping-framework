    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import java.util.Collection;
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
    
    protected abstract Collection<T> find(T t); /* remove */
    
    protected abstract T find(String id);

    protected abstract Collection<T> findAll();
    
    protected abstract org.bson.Document reset_doc(String id);
    
    protected abstract Boolean has_next(org.bson.Document doc);
    
    protected abstract T next(org.bson.Document doc);
    
    protected T build_object(String id) {
        org.bson.Document doc = reset_doc(id);
        T root_object = null;
        T map;
        if ( has_next(doc) ) {
            root_object = next(doc);
            map = root_object;
        }
        while ( has_next(doc) ) {
            T obj = next(doc);
            
        }
        return root_object;
    }
    
    public static void main(String[] args) {
        EventoDAOMySQL edao = new EventoDAOMySQL();
        edao.build_object("1");
    }
    
}
