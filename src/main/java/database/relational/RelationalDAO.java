/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.relational;

import database.relational.mysql.ConexaoMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import database.AbstractDAO;
import java.sql.ResultSetMetaData;
import java.util.Iterator;
import org.bson.Document;

/**
 *
 * @param <T>
 */
public abstract class RelationalDAO<T extends database.Document> extends AbstractDAO<T> {

    private String createSql;
    private String updateSql;
    private String deleteSql;
    private String findSql;
    private String findAllSql;

    public String getCreateSql() {
        return createSql;
    }

    public void setCreateSql(String createSql) {
        this.createSql = createSql;
    }

    public String getUpdateSql() {
        return updateSql;
    }

    public void setUpdateSql(String updateSql) {
        this.updateSql = updateSql;
    }

    public String getDeleteSql() {
        return deleteSql;
    }

    public void setDeleteSql(String deleteSql) {
        this.deleteSql = deleteSql;
    }

    public String getFindSql() {
        return findSql;
    }

    public void setFindSql(String findSql) {
        this.findSql = findSql;
    }

    public String getFindAllSql() {
        return findAllSql;
    }

    public void setFindAllSql(String findAllSql) {
        this.findAllSql = findAllSql;
    }

    /**
     * @param t
     */    
    @Override
    public Integer create(T t) {
        Document doc = build_doc(t);
        process_queries(doc);         
        return 1; //success
    }
   
    
    @Override
    public void update(T t) {
        Document doc = build_doc(t);
        process_queries(doc); 
    }

    @Override
    public void delete(T t) {
        Connection c = ConexaoMySQL.open();
        try {
            PreparedStatement ps = c.prepareStatement(getDeleteSql());
            fillDelete(ps, t);
            ps.execute();
            ps.close();
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(RelationalDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
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
    protected Document reset_doc(String id) {
        Document doc = new Document();
        Connection c = ConexaoMySQL.open();
        try {
            String sql = (id == null) ? getFindAllSql() : getFindSql();
            PreparedStatement ps = c.prepareStatement(sql);
            if (id != null) 
                ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            Collection<Document> documents = new ArrayList<>();
            while ( rs.next() ) {
                Document docRow = new Document();
                for (int i = 1; i <= metaData.getColumnCount(); i++) 
                   docRow.append(metaData.getColumnName(i), rs.getObject(i));
                documents.add(docRow);
            }      
            String key = (id == null) ? "docs" : id;
            doc.append(key, documents);
            init_parser_vars(documents);
            ps.close();
            rs.close();
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(RelationalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doc;
    }
    
    public void process_queries(Document doc){
        Document parameters = (Document) doc.get("sqlParameters");
        int number_parameters = 1;
        String sqlCommand = (String) doc.get("sqlCommand");
        String querySql = null;
        if("insert".equals(sqlCommand))
            querySql = getCreateSql();
        else if("update".equals(sqlCommand))
            querySql = getUpdateSql();

        Connection c = ConexaoMySQL.open();
        try {
            PreparedStatement ps = c.prepareStatement(querySql, PreparedStatement.RETURN_GENERATED_KEYS);
             if("insert".equals(sqlCommand))
                fillCreate(ps, parameters);
            else if("update".equals(sqlCommand))
                fillUpdate(ps, parameters);
            ps.execute();
            ps.close();
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(RelationalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
    protected abstract void fillCreate(PreparedStatement ps, Document parameters) throws SQLException;

    protected abstract void fillUpdate(PreparedStatement ps, Document parameters) throws SQLException;

    protected abstract void fillDelete(PreparedStatement ps, T t) throws SQLException;
    
    protected Iterator docIterator;
    protected String lastObjectName;
    protected String lastRootId;
    protected Boolean hasNextDoc;
    protected Document lastProcessedDoc;

    private void init_parser_vars(Collection<Document> documents) {
        docIterator = documents.iterator();
        lastObjectName = "";
        lastRootId = "";
        hasNextDoc = false;
        lastProcessedDoc = null;
    }
    
}
