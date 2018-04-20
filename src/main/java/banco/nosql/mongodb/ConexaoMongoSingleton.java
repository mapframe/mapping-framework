/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.nosql.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 *
 * @author rhau
 */
public class ConexaoMongoSingleton {
    
    private static ConexaoMongoSingleton unicaInstacia;
    private MongoClient mongoClient;
    private MongoDatabase database;
    
    private ConexaoMongoSingleton() {
        
    }
    
    /* Garante sempre uma unica instancia da classe */
    public static synchronized ConexaoMongoSingleton getInstancia() {
        if (unicaInstacia == null)
            unicaInstacia = new ConexaoMongoSingleton();
        return unicaInstacia;
    }
    
    /* Garante sempre um unico objeto mongo */
    public MongoDatabase getMongoDatabase() {
        if (mongoClient == null) {
            MongoClientURI connectionString = new MongoClientURI(BDMongo.URL_CONEXAO);
            mongoClient = new MongoClient(connectionString);
            database = mongoClient.getDatabase(BDMongo.DATABASE);
        }
        return database;
    }

    public MongoCollection<Document> getMongoCollection() {
        return getMongoDatabase().getCollection(BDMongo.COLLECTION);
    }
    
}
