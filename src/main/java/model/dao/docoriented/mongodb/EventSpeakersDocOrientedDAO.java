/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.docoriented.mongodb;

import database.docoriented.DocumentOrientedDAO;
import java.util.Collection;
import model.EventSpeakers;
import model.Lecture;
import model.Speaker;
import org.bson.Document;

/**
 *
 * @author isabella
 */
public class EventSpeakersDocOrientedDAO extends DocumentOrientedDAO<EventSpeakers> {
  
    @Override
    protected Boolean has_next() {
        return docIterator.hasNext() || lastSubDoc.hasNext() || lastObjectName.equals("lecture");
    }

    @Override
    protected Object next() {
        Object obj = null;
        
        if (lastObjectName.equals("speaker")) {
            if ( !lastSubDoc.hasNext() )
                lastObjectName = "";
        }
        
        if ( lastObjectName.isEmpty() ) {
            lastProcessedDoc = (Document) docIterator.next();
            EventSpeakers es = new EventSpeakers();
            es.setId(lastProcessedDoc.get("_id").toString());
            es.setName((String) lastProcessedDoc.get("event_name"));
            obj = es;
            lastObjectName = "eventSpeakers";
            Collection<Document> lectures = (Collection<Document>) lastProcessedDoc.get("lectures");
            lastSubDoc = lectures.iterator();
        }
        else if ( lastObjectName.toLowerCase().contains("speaker") ) { 
            lastProcessedDoc = (Document) lastSubDoc.next();
            Lecture l = new Lecture();
            l.setTitle((String) lastProcessedDoc.get("lecture_title"));
            obj = l;
            lastObjectName = "lecture";                
        }
        else if ( lastObjectName.equals("lecture") ) { 
            Speaker sp = new Speaker();
            Document spDoc = (Document) lastProcessedDoc.get("speaker");
            sp.setName((String) spDoc.get("speaker_name"));
            sp.setCity((String) spDoc.get("speaker_city"));
            obj = sp;
            lastObjectName = "speaker";
        }
        return obj;
    }

    @Override
    protected Document process(EventSpeakers e) {
        Document doc = new Document();
        doc.put("event_name", e.getName());
        return doc;    
    }
    
    @Override
    protected String getDocumentId(EventSpeakers e) {
        return e.getId();
    }
    
}
