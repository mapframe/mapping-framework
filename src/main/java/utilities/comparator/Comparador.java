/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities.comparator;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author rhau
 */
public class Comparador {
    
    public static List<Field> compararObjetos(Object p1, Object p2) throws IllegalArgumentException, IllegalAccessException {
        List<Field> differentFields = new LinkedList<>();
        if (p1 != p2) {
            Class clazz = p1.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field f: fields) {
                boolean accessible = f.isAccessible();
                if (!accessible)
                    f.setAccessible(true);
                Object o1f = f.get(p1);
                Object o2f = f.get(p2);
                if ( !Objects.equals(o1f, o2f) ) 
                    differentFields.add(f);
                f.setAccessible(accessible);
            }
        }
        return differentFields;
    }
    
}
