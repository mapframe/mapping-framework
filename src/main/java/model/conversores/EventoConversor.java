/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.conversores;

import java.util.ArrayList;
import java.util.Collection;
import model.EventSpeakers;
import model.Lecture;
import org.bson.Document;
import util.DateUtils;

/**
 *
 * @author isabella
 */
public class EventoConversor extends Conversor<EventSpeakers> {

    @Override
    public Document toDocument(EventSpeakers e) {
        Document doc = new Document("nome", e.getNome())
                        .append("descricao", e.getDescricao())
                        .append("endereco", e.getEndereco())
                        .append("dataInicio", DateUtils.toString(e.getDataInicio(), "dd/MM/yyyy"))
                        .append("dataFim", DateUtils.toString(e.getDataFim(), "dd/MM/yyyy"))
                        .append("predio", new PredioConversor().toDocument(e.getPredio()));
        Collection<Document> documents = new ArrayList<>();
        for (Lecture p : e.getPalestras()) 
            documents.add(new PalestraConversor().toDocument(p));
        doc.append("palestras", documents);
        return doc;
    }

    @Override
    public EventSpeakers toModel(Document doc) { 
        EventSpeakers e = new EventSpeakers();
        if ( doc.get("_id") != null )
            e.setId(doc.get("_id").toString());
        e.setNome((String) doc.get("nome"));
        e.setDescricao((String) doc.get("descricao"));
        e.setEndereco((String) doc.get("endereco"));
        e.setDataInicio(DateUtils.toDate((String) doc.get("dataInicio"), "dd/MM/yyyy"));
        e.setDataFim(DateUtils.toDate((String) doc.get("dataFim"), "dd/MM/yyyy"));
        e.setPredio(new PredioConversor().toModel(doc));
        for (Document d : (Collection<Document>) doc.get("palestras")) 
            e.getPalestras().add(new PalestraConversor().toModel(d));
        return e;
    }
    
}
