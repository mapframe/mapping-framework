/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class DateUtils {
   
    public static Date toDate(String dataString, String mask) {
        SimpleDateFormat formatter = new SimpleDateFormat(mask);
        Date data = null;
        try {
            data = formatter.parse(dataString);
        } catch (ParseException ex) {
            Logger.getLogger(DateUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
       
    public static String toString(Date data, String mask) {
        SimpleDateFormat formatter = new SimpleDateFormat(mask);        
        return formatter.format(data);
    }

}
