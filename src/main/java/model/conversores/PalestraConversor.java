/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.conversores;

import model.Lecture;
import org.bson.Document;
import util.DateUtils;

/**
 *
 * @author rhau
 */
public class PalestraConversor extends Conversor<Lecture> {

    @Override
    public Document toDocument(Lecture p) {
        Document doc = new Document("titulo", p.getTitulo())
                        .append("assunto", p.getAssunto())
                        .append("descricao", p.getDescricao())
                        .append("data", DateUtils.toString(p.getData(), "dd/MM/yyyy"))
                        .append("inicio", DateUtils.toString(p.getHoraInicio(), "HH:mm"))
                        .append("fim", DateUtils.toString(p.getHoraFim(), "HH:mm"))
                        .append("palestrante", new PalestranteConversor().toDocument(p.getPalestrante()))
                        .append("sala", new SalaConversor().toDocument(p.getSala()));
        return doc;
    }

    @Override
    public Lecture toModel(Document doc) {
        Lecture p = new Lecture();
        if ( doc.get("id") != null )
            p.setId((Integer) doc.get("id"));
        p.setTitulo((String) doc.get("titulo"));
        p.setAssunto((String) doc.get("assunto"));
        p.setDescricao((String) doc.get("descricao"));
        p.setData(DateUtils.toDate((String) doc.get("data"), "dd/MM/yyyy"));
        p.setHoraInicio(DateUtils.toDate((String) doc.get("inicio"), "HH:mm"));
        p.setHoraFim(DateUtils.toDate((String) doc.get("fim"), "HH:mm"));
        p.setPalestrante(new PalestranteConversor().toModel(doc));
        p.setSala(new SalaConversor().toModel(doc));
        return p;      
    }
    
}
