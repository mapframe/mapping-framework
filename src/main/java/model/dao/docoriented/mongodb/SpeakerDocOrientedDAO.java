/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.docoriented.mongodb;

import database.docoriented.DocumentOrientedDAO;
import model.Speaker;
import org.bson.Document;

/**
 *
 */

public class SpeakerDocOrientedDAO extends DocumentOrientedDAO<Speaker> {

    @Override
    protected Boolean has_next() { return false; }

    @Override
    protected Speaker next() {
        Speaker s = new Speaker();
        Document speakerDoc = (Document) lastProcessedDoc.get("speaker");
        s.setName((String) speakerDoc.get("speaker_name"));
        s.setCity((String) speakerDoc.get("speaker_city"));
        return s;
    }

    @Override
    protected Document process(Speaker s) {
        Document doc = new Document();
        doc.put("speaker_name", s.getName());
        doc.put("speaker_city", s.getCity());
        return doc;
    }

    @Override
    protected String getDocumentId(Speaker s) { return null; }
       
}
