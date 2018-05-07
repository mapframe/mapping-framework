/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.docoriented;

import database.docoriented.mongodb.ConexaoMongoSingleton;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import java.util.Collection;
import org.bson.Document;
import org.bson.types.ObjectId;
import database.AbstractDAO;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author rhau
 * @param <T>
 */
public abstract class DocumentOrientedDAO<T extends database.Document> extends AbstractDAO<T> {
    
    private final MongoCollection<Document> collection;

    public DocumentOrientedDAO() {
        this.collection = ConexaoMongoSingleton.getInstancia().getMongoCollection();
    }
    
    @Override
    public Integer create(T t) {
        Document doc = build_doc(t);
        return 1; // success
    }
    
    @Override
    public void update(T t) {
        Document doc = build_doc(t);
        String documentId = getDocumentId(t);
        collection.updateOne(eq( "_id", new ObjectId(documentId)), new Document("$set", doc));
    }
    
    @Override
    public void delete(T t) {
        String documentId = getDocumentId(t);
        collection.deleteOne( eq("_id",  new ObjectId(documentId)) );       
    }
    
    @Override
    public T find(String id) {
        return build_object(id);
    }

    @Override
    public Collection<T> findAll() {
        return build_objects();
    }

    @Override
    protected org.bson.Document reset_doc(String id) {
        Collection<Document> documents = new ArrayList<>();
        if (id == null) {
            for (Document cur : collection.find()) 
                documents.add(cur);
        } else {
            for (Document cur : collection.find( eq("_id", new ObjectId(id)) ))
                documents.add(cur);
        }      
        init_parser_vars(documents);
        return new Document("docs", documents);
    }
    
    protected abstract String getDocumentId(T t);
    
    /* protected abstract Document prepararDocumento(T t);

    protected abstract T prepararRegistro(Document doc); */
    
    protected Iterator docIterator;
    protected Iterator lastSubDoc;
    protected String lastObjectName;
    protected Document lastProcessedDoc;
    
    private void init_parser_vars(Collection<Document> documents) {
        docIterator = documents.iterator();
        lastSubDoc = null;
        lastObjectName = "";
        lastProcessedDoc = null;
    }
    
}