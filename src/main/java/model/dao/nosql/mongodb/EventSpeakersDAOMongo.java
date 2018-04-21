/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.nosql.mongodb;

import banco.nosql.mongodb.DocumentOrientedDAO;
import java.util.Collection;
import java.util.Iterator;
import model.EventSpeakers;
import model.Lecture;
import model.Speaker;
import model.conversores.EventoConversor;
import org.bson.Document;

/**
 *
 * @author isabella
 */
public class EventSpeakersDAOMongo extends DocumentOrientedDAO<EventSpeakers> {

    @Override
    protected String getDocumentId(EventSpeakers e) {
        return e.getId();
    }
    
    @Override
    protected Document prepararDocumento(EventSpeakers e) {
      //  return new EventoConversor().toDocument(e);
      Document doc = new Document("event_name", e.getName());
      return doc;
    }

    @Override
    protected EventSpeakers prepararRegistro(Document doc) {
        return //new EventoConversor().toModel(doc); 
                null;
    }

    @Override
    protected Boolean has_next(Document doc) {
        return docIterator.hasNext() || lastSubDoc.hasNext() || lastObjectName.equals("lecture");
}

    @Override
    protected Object next(Document doc) {
        Object obj = null;
        
        if (lastObjectName.equals("speaker")) {
            if ( !lastSubDoc.hasNext() )
                lastObjectName = "";
        }
        
        if ( lastObjectName.isEmpty() ) {
            lastDoc = (Document) docIterator.next();
            EventSpeakers es = new EventSpeakers();
            es.setId(lastDoc.get("_id").toString());
            es.setName((String) lastDoc.get("event_name"));
            obj = es;
            lastObjectName = "eventSpeakers";
            Collection<Document> lectures = (Collection<Document>) lastDoc.get("lectures");
            lastSubDoc = lectures.iterator();
        }
        else if ( lastObjectName.toLowerCase().contains("speaker") ) { 
            lastDoc = (Document) lastSubDoc.next();
            Lecture l = new Lecture();
            l.setTitle((String) lastDoc.get("lecture_title"));
            obj = l;
            lastObjectName = "lecture";                
        }
        else if ( lastObjectName.equals("lecture") ) { 
            Speaker sp = new Speaker();
            Document spDoc = (Document) lastDoc.get("speaker");
            sp.setName((String) spDoc.get("speaker_name"));
            sp.setCity((String) spDoc.get("speaker_city"));
            obj = sp;
            lastObjectName = "speaker";
        }
        return obj;
    }
    
}
