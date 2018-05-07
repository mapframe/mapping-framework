/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities.conversor;

import org.bson.Document;

/**
 *
 * @author rhau
 */
public abstract class Conversor<T> {
    
    public abstract Document toDocument(T t);
    
    public abstract T toModel(Document doc);

}
