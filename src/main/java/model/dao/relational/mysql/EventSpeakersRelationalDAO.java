/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.relational.mysql;

import database.relational.RelationalDAO;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.EventSpeakers;
import model.Lecture;
import model.Speaker;
import org.bson.Document;

/**
 *
 */
public class EventSpeakersRelationalDAO extends RelationalDAO<EventSpeakers> {

    public EventSpeakersRelationalDAO() {
        setCreateSql("INSERT INTO event (event_name) VALUES (?)");
        setUpdateSql("UPDATE event SET event_name = ? WHERE event_id = ?");
        setDeleteSql("DELETE FROM event WHERE event_id = ?;");
        setFindSql("SELECT * FROM event NATURAL JOIN lecture NATURAL JOIN speaker where event_id = ?;");
        setFindAllSql("SELECT * FROM event NATURAL JOIN lecture NATURAL JOIN speaker;");
    }

    @Override
    protected Boolean has_next() {
        return docIterator.hasNext() || hasNextDoc;
    }

    @Override
    protected Object next() {
        Object obj = null;
        if ( !lastRootId.isEmpty() && !lastRootId.equals(Integer.toString((Integer) lastProcessedDoc.get("event_id"))) ) 
            lastObjectName = "";
        
        if ( lastObjectName.isEmpty() ) {
            if (lastProcessedDoc == null) 
                lastProcessedDoc = (Document) docIterator.next();
            EventSpeakers es = new EventSpeakers();
            es.setId(Integer.toString((Integer) lastProcessedDoc.get("event_id")));
            es.setName((String) lastProcessedDoc.get("event_name"));
            obj = es;
            lastObjectName = "eventSpeakers";
            lastRootId = es.getId();
        }
        else if ( lastObjectName.toLowerCase().contains("speaker") ) { 
            Lecture l = new Lecture();
            l.setId((Integer) lastProcessedDoc.get("lecture_id"));
            l.setTitle((String) lastProcessedDoc.get("lecture_title"));
            obj = l;
            lastObjectName = "lecture";
        }
        else if ( lastObjectName.equals("lecture") ) { 
            Speaker sp = new Speaker();
            sp.setId((Integer) lastProcessedDoc.get("lecture_id"));
            sp.setName((String) lastProcessedDoc.get("speaker_name"));
            sp.setCity((String) lastProcessedDoc.get("speaker_city"));
            obj = sp;
            if ( docIterator.hasNext() ) {
                lastProcessedDoc = (Document) docIterator.next();
                hasNextDoc = true;
            } else 
                hasNextDoc = false;
            lastObjectName = "speaker";
        }
        return obj;
    }

    @Override
    protected Document process(EventSpeakers e) {
        Boolean eventSpeakerHasId = e.getId() != null;
        Document sqlAsDoc = new Document();
        Document paramaters = new Document();

        sqlAsDoc.put("sqlCommand", eventSpeakerHasId ? "insert" : "update");
        paramaters.put("1", e.getName());
        if ( eventSpeakerHasId )
            paramaters.put("2", e.getId());
        sqlAsDoc.put("sqlParameters", paramaters);
        
        return sqlAsDoc;
    }
    
    @Override
    protected void fillCreate(PreparedStatement ps, Document parameters) throws SQLException {
        ps.setString(1, (String) parameters.get("1"));
    }
    
    @Override
    protected void fillUpdate(PreparedStatement ps, Document parameters) throws SQLException {
        ps.setString(1, (String) parameters.get("1"));
        ps.setString(2, (String) parameters.get("2"));
    }

    @Override
    protected void fillDelete(PreparedStatement ps, EventSpeakers e) throws SQLException {
        ps.setString(1, e.getId());
    }
    
    
}