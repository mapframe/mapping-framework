/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.relacional.mysql;

import banco.relacional.mysql.RelationalDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import model.Speaker;

/**
 *
 * @author rhau
 */
public class PalestranteDAOMySQL extends RelationalDAO<Speaker> {
    
    public PalestranteDAOMySQL() {
        setSqlInsercao("INSERT INTO palestrante (nome_palestrante, sexo, grau_academico, nome_curso, instituicao) VALUES (?, ?, ?, ?, ?)");
        setSqlAlteracao("UPDATE palestrante SET nome_palestrante = ?, sexo = ?, grau_academico = ?, nome_curso = ?, instituicao = ? WHERE id_palestrante = ?");
        setSqlExclusao("DELETE FROM palestrante");
        setSqlBuscaChavePrimaria("SELECT * FROM palestrante WHERE id_palestrante = ?");
        setSqlBusca("SELECT * FROM palestrante WHERE nome_palestrante = ?");
        setSqlBuscaTodos("SELECT * FROM palestrante");
    }

    @Override
    protected void fillCreate(PreparedStatement ps, Speaker p) throws SQLException {
        ps.setString(1, p.getNome());
        ps.setString(2, String.valueOf(p.getSexo()));
        ps.setString(3, p.getGrauAcademico());
        ps.setString(4, p.getCursoFormacao());
        ps.setString(5, p.getInstituicaoFormacao());
    }

    @Override
    protected void fillUpdate(PreparedStatement ps, Speaker p) throws SQLException {
        ps.setString(1, p.getNome());
        ps.setString(2, String.valueOf(p.getSexo()));
        ps.setString(3, p.getGrauAcademico());
        ps.setString(4, p.getCursoFormacao());
        ps.setString(5, p.getInstituicaoFormacao());    
        ps.setInt(6, p.getId());
    }

    @Override
    protected void fillDelete(PreparedStatement ps, Speaker p) throws SQLException {
        ps.setInt(1, p.getId());
    }

    @Override
    protected void fillFind(PreparedStatement ps, Speaker p) throws SQLException {
        ps.setString(1, p.getNome());
    }

    @Override
    protected Speaker fill(ResultSet rs) throws SQLException {
        Speaker p = new Speaker();
        p.setId(rs.getInt("id_palestrante"));
        p.setNome(rs.getString("nome_palestrante"));
        p.setSexo(rs.getString("sexo").charAt(0));
        p.setGrauAcademico(rs.getString("grau_academico"));
        p.setCursoFormacao(rs.getString("nome_curso"));
        p.setInstituicaoFormacao(rs.getString("instituicao"));
        return p;
    }

    @Override
    protected Collection<Speaker> fillList(ResultSet rs) throws SQLException {
        Collection<Speaker> palestrantes = new ArrayList<>();
        while (rs.next())
            palestrantes.add(fill(rs) );
        return palestrantes;
    }
    
}
