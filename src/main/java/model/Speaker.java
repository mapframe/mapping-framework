/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import banco.Document;

/**
 *
 * @author rhau
 */
public class Speaker extends Document {

    private Integer id;
    private String name;
    private String city;
    private Lecture lecture;

    public Speaker() {
        
    }
   
    public Speaker(Integer id) {
        this.id = id;
    }

    public Speaker(String name, String city, Lecture lecture) {
        this.name = name;
        this.city = city;
        this.lecture = lecture;
    }

    public Speaker(Integer id, String name, String city, Lecture lecture) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.lecture = lecture;
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
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the lecture
     */
    public Lecture getLecture() {
        return lecture;
    }

    /**
     * @param lecture the lecture to set
     */
    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    @Override
    public String toString() {
        return "Speaker{" + "id=" + id + ", name=" + name + ", city=" + city + ", lecture=" + lecture + '}';
    }
    
}
