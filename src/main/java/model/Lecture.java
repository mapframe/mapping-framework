/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import database.Document;
import java.util.Date;

/**
 *
 */
public class Lecture extends Document {

    private Integer id;
    private String title;
    private Speaker speaker;
    private EventSpeakers parent_object; // referencia para criar obj mysql

    public Lecture() {

    }

    public Lecture(Integer id) {
        this.id = id;
    }

    public Lecture(String title, Speaker speaker, EventSpeakers event) {
        this.title = title;
        this.speaker = speaker;
        this.parent_object = event;
    }

    public Lecture(Integer id, String title, Speaker speaker, EventSpeakers event) {
        this.id = id;
        this.title = title;
        this.speaker = speaker;
        this.parent_object = event;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the speaker
     */
    public Speaker getSpeaker() {
        return speaker;
    }

    /**
     * @param speaker the speaker to set
     */
    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    /**
     * @return the event
     */
    public EventSpeakers getEvent() {
        return parent_object;
    }

    /**
     * @param event the event to set
     */
    public void setEvent(EventSpeakers event) {
        this.parent_object = event;
    }

    @Override
    public String toString() {
        return "Lecture{" + "id=" + id + ", title=" + title + ", speaker=" + speaker + '}';
    }

}