/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.relational.mysql;

import database.relational.RelationalDAO;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Lecture;
import org.bson.Document;

/**
 *
 */
public class LectureRelationalDAO extends RelationalDAO<Lecture> {

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
        Boolean lectureHasId = l.getId() != null;
        Document sqlAsDoc = new Document();
        Document paramaters = new Document();

        sqlAsDoc.put("sqlCommand", lectureHasId ? "insert" : "update");
        paramaters.put("1", l.getTitle());
        if (lectureHasId)
            paramaters.put("2", l.getId());
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
    protected void fillDelete(PreparedStatement ps, Lecture l) throws SQLException {
        ps.setInt(1, l.getId());
    }
    
}
