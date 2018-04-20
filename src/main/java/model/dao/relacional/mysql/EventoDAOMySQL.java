/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.relacional.mysql;

import banco.relacional.mysql.ConexaoMySQL;
import banco.relacional.mysql.RelationalDAO;
import model.comparadores.EventoMySQLComparador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.EventSpeakers;
import model.Lecture;
import org.bson.Document;

/**
 *
 * @author isabella
 */
public class EventoDAOMySQL extends RelationalDAO<EventSpeakers> {

    public EventoDAOMySQL() {
        setCreateSql("INSERT INTO evento (nome_evento, descricao_evento, endereco, predio, data_inicio, data_fim) VALUES (?, ?, ?, ?, ?, ?)");
        setUpdateSql("UPDATE evento SET nome_evento = ?, descricao_evento = ?, endereco = ?, predio = ?, data_inicio = ?, data_fim = ? WHERE id_evento = ?");
        setDeleteSql("DELETE FROM evento WHERE id_evento = ?");
        setFindSql("SELECT * FROM event NATURAL JOIN lecture NATURAL JOIN speaker where event_id = ?;");
        setFindAllSql("SELECT * FROM event NATURAL JOIN lecture NATURAL JOIN speaker;");
        setSqlBusca("SELECT * FROM event NATURAL JOIN lecture NATURAL JOIN speaker where event_id = ?;");
    }

    @Override
    public Integer create(EventSpeakers e) { 
        Connection c = ConexaoMySQL.open();
        try {
            PreparedStatement ps = c.prepareStatement(getCreateSql(), PreparedStatement.RETURN_GENERATED_KEYS);
            fillCreate(ps, e);
            ps.execute();
            // recupera id_evento
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next())
                e.setId(Integer.toString( rs.getInt(1) )); // pk
            rs.close();
            ps.close();
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(RelationalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        
        // para cada evento salva as suas palestras
        PalestraDAOMySQL pdao = new PalestraDAOMySQL();
        for (Lecture p : e.getLectures()) {
            p.setEvent(e);
            pdao.create(p);
        }
        return 1; // sucess
    }
    
    @Override
    public void update(EventSpeakers e) {
        EventSpeakers antigo = new EventoDAOMySQL().find(e.getId());
        EventoMySQLComparador comparador = new EventoMySQLComparador();
        if (  comparador.comparaEventos(antigo, e) ) {
            Connection c = ConexaoMySQL.open();
            try {
                PreparedStatement ps = c.prepareStatement(getSqlBusca());
                fillUpdate(ps, e);
                ps.execute();
                ps.close();
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(RelationalDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    protected void fillCreate(PreparedStatement ps, EventSpeakers e) throws SQLException {
        ps.setString(1, e.getName());
    }

    @Override
    protected void fillUpdate(PreparedStatement ps, EventSpeakers e) throws SQLException {
        ps.setString(1, e.getName());
        ps.setInt(7, Integer.parseInt(e.getId()));
    }

    @Override
    protected void fillDelete(PreparedStatement ps, EventSpeakers e) throws SQLException {
        ps.setInt(1, Integer.parseInt(e.getId()));
    }

    @Override
    protected void fillFind(PreparedStatement ps, EventSpeakers e) throws SQLException {
        ps.setString(1, e.getName());
    }

    @Override
    protected EventSpeakers fill(ResultSet rs) throws SQLException {
        EventSpeakers e = new EventSpeakers();
        e.setId(rs.getString("event_id"));
        e.setName(rs.getString("event_name"));
        
        // busca palestras pelo id_evento
        Lecture p = new Lecture();
        p.setEvent(e); 
       // e.setPalestras( new PalestraDAOMySQL().find(p) );
        // preencher obj para updates
      //  for (Lecture palestra: e.getPalestras())
     //       palestra.setEvento(e);
        
        return e;
    }

    @Override
    protected Collection<EventSpeakers> fillList(ResultSet rs) throws SQLException {
        Collection<EventSpeakers> eventos = new ArrayList<>();
        while (rs.next())
            eventos.add(fill(rs) );
        return eventos;
    }

    @Override
    protected Boolean has_next(Document doc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected EventSpeakers next(Document doc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
