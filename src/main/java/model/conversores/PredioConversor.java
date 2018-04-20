/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.conversores;

import model.Predio;
import org.bson.Document;

/**
 *
 * @author rhau
 */
public class PredioConversor extends Conversor<Predio> {

    @Override
    public Document toDocument(Predio p) {
        Document doc = new Document("nome", p.getNome());
        return doc;
    }

    @Override
    public Predio toModel(Document doc) {
        Document predioDoc = (Document) doc.get("predio");        
        Predio p = new Predio();
        p.setNome((String) predioDoc.get("nome"));
        return p;    
    }
    
}
