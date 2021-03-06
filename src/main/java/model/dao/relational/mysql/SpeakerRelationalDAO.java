/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.relational.mysql;

import database.relational.RelationalDAO;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Speaker;
import org.bson.Document;

/**
 *
 */
public class SpeakerRelationalDAO extends RelationalDAO<Speaker> {

    @Override
    protected Boolean has_next() { return false; }

    @Override
    protected Speaker next() {
        Speaker s = new Speaker();
        s.setId((Integer) lastProcessedDoc.get("lecture_id"));
        s.setName((String) lastProcessedDoc.get("speaker_name"));
        s.setCity((String) lastProcessedDoc.get("speaker_city"));
        return s;
    }
   
    @Override
    protected Document process(Speaker s) {
        Boolean speakerHasId = s.getId() != null;
        Document sqlAsDoc = new Document();
        Document paramaters = new Document();

        sqlAsDoc.put("sqlCommand", speakerHasId ? "insert" : "update");
        paramaters.put("1", s.getName());
        paramaters.put("2", s.getCity());
        if ( speakerHasId )
            paramaters.put("3", s.getId());
        sqlAsDoc.put("sqlParameters", paramaters);
        
        return sqlAsDoc;
    }

    @Override
    protected void fillCreate(PreparedStatement ps, Document parameters) throws SQLException {
        ps.setString(1, (String) parameters.get("1"));
        ps.setString(2, (String) parameters.get("2")); 
    }

    @Override
    protected void fillUpdate(PreparedStatement ps, Document parameters) throws SQLException {
        ps.setString(1, (String) parameters.get("1"));
        ps.setString(2, (String) parameters.get("2"));    
        ps.setString(3, (String) parameters.get("3")); 
    }

    @Override
    protected void fillDelete(PreparedStatement ps, Speaker s) throws SQLException {
        ps.setInt(1, s.getId());
    }
    
}
