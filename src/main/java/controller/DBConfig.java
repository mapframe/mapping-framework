/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.AbstractDAO;
import model.dao.docoriented.mongodb.EventSpeakersDocOrientedDAO;
import model.dao.relational.mysql.EventSpeakersRelationalDAO;

/**
 *
 */
public class DBConfig {
    
    private static final AbstractDAO abstractDAO = new EventSpeakersRelationalDAO();
    // private static final AbstractDAO abstractDAO = new EventSpeakersDocOrientedDAO();
           
    /*
    static {
        abstractDAO = new EventSpeakersRelationalDAO();
        // abstractDAO = new EventSpeakersDocOrientedDAO();
    }
    */
    
    public static AbstractDAO getAbstractDAO() {
        return abstractDAO;
    }

}
