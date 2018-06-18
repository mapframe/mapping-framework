/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.docoriented.mongodb;

import database.docoriented.DocumentOrientedDAO;
import model.Lecture;
import org.bson.Document;

/**
 *
 */

public class LectureDocOrientedDAO extends DocumentOrientedDAO<Lecture> {

    @Override
    protected Boolean has_next() { return false; }

    @Override
    protected Lecture next() {
        Lecture l = new Lecture();
        Document lectureDoc = (Document) lastProcessedDoc.get("lecture");
        l.setTitle((String) lectureDoc.get("lecture_title"));
        return l;
    }

    @Override
    protected Document process(Lecture l) {
        Document doc = new Document();
        doc.put("lecture_title", l.getTitle());
        return doc;
    }

    @Override
    protected String getDocumentId(Lecture l) { return null; }

}
