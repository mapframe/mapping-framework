/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.relacional.mysql;

import banco.Document;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import banco.AbstractDAO;
import java.sql.ResultSetMetaData;
import java.util.Iterator;

/**
 *
 * @author isabella
 * @param <T>
 */
public abstract class RelationalDAO<T extends Document> extends AbstractDAO<T> {

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
     * @return id do elemento inserido
     */    
    @Override
    public Integer create(T t) {
        Connection c = ConexaoMySQL.open();
        Integer id = null;
        try {
            PreparedStatement ps = c.prepareStatement(getCreateSql(), PreparedStatement.RETURN_GENERATED_KEYS);
            fillCreate(ps, t);
            ps.execute();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next())
                id = rs.getInt(1); // pk
            rs.close();
            ps.close();
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(RelationalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    @Override
    public void update(T t) {
        Connection c = ConexaoMySQL.open();
        try {
            PreparedStatement ps = c.prepareStatement(getUpdateSql());
            fillUpdate(ps, t);
            ps.execute();
            ps.close();
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(RelationalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    protected org.bson.Document reset_doc(String id) {
        org.bson.Document doc = new org.bson.Document();
        Connection c = ConexaoMySQL.open();
        try {
            String sql = (id == null) ? getFindAllSql() : getFindSql();
            PreparedStatement ps = c.prepareStatement(sql);
            if (id != null) 
                ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            Collection<org.bson.Document> documents = new ArrayList<>();
            while ( rs.next() ) {
                org.bson.Document docRow = new org.bson.Document();
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
    
    protected abstract void fillCreate(PreparedStatement ps, T t) throws SQLException;

    protected abstract void fillUpdate(PreparedStatement ps, T t) throws SQLException;

    protected abstract void fillDelete(PreparedStatement ps, T t) throws SQLException;

    protected abstract void fillFind(PreparedStatement ps, T t) throws SQLException;

    protected abstract T fill(ResultSet rs) throws SQLException;

    protected abstract Collection<T> fillList(ResultSet rs) throws SQLException;
    
    protected Iterator docIterator;
    protected String lastObjectName;
    protected String lastRootId;
    protected Boolean hasNextDoc;
    protected org.bson.Document lastDoc;

    private void init_parser_vars(Collection<org.bson.Document> documents) {
        docIterator = documents.iterator();
        lastObjectName = "";
        lastRootId = "";
        hasNextDoc = false;
        lastDoc = null;
    }
    
}
