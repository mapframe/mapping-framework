/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.nosql.mongodb;

import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import java.util.Collection;
import org.bson.Document;
import org.bson.types.ObjectId;
import banco.AbstractDAO;
import java.util.ArrayList;

/**
 *
 * @author rhau
 * @param <T>
 */
public abstract class DocumentOrientedDAO<T extends banco.Document> extends AbstractDAO<T> {
    
    private final MongoCollection<Document> collection;

    public DocumentOrientedDAO() {
        this.collection = ConexaoMongoSingleton.getInstancia().getMongoCollection();
    }

    public Integer create(T t) {
        Document doc = prepararDocumento(t);
        collection.insertOne(doc);
        return 1; // success
    }

    public void update(T t) {
        Document doc = prepararDocumento(t);
        String documentId = getDocumentId(t);
        collection.updateOne(eq( "_id", new ObjectId(documentId)), new Document("$set", doc));
    }

    public void delete(T t) {
        String documentId = getDocumentId(t);
        collection.deleteOne( eq("_id",  new ObjectId(documentId)) );       
    }

    public Collection<T> find(T t) {
        Collection<T> registros = new ArrayList<>();
        String documentId = getDocumentId(t);
        for (Document cur : collection.find( eq("_id", new ObjectId(documentId))) )
            registros.add( prepararRegistro(cur) );
        return registros;
    }

    public T find(String id) {
        T registro = null;
        for (Document cur : collection.find( eq("_id", new ObjectId(id)) ))
            registro = prepararRegistro(cur);
        return registro;
    }

    public Collection<T> findAll() {
        Collection<T> registros = new ArrayList<>();
        for (Document cur : collection.find())
            registros.add( prepararRegistro(cur) );
        return registros;
    }

    protected abstract String getDocumentId(T t);
    
    protected abstract Document prepararDocumento(T t);

    protected abstract T prepararRegistro(Document doc);
    
}
