/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/ 
package model;

import banco.Document;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author isabella
 */
public class EventSpeakers extends Document {
    
    private String id; // string para incluir uso do mongodb 
    private String name;
    private Collection<Lecture> lectures = new ArrayList<>(); // referencia para criar obj mongo

    public EventSpeakers() {
    }

    public EventSpeakers(String id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the lectures
     */
    public Collection<Lecture> getLectures() {
        return lectures;
    }

    /**
     * @param lectures the lectures to set
     */
    public void setLectures(Collection<Lecture> lectures) {
        this.lectures = lectures;
    }

    @Override
    public String toString() {
        return "EventSpeakers{" + "id=" + id + ", name=" + name + ", lectures=" + lectures + '}';
    }
    
}