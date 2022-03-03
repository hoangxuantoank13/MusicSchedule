/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skedulo.musicschedule;

import com.skedulo.musicschedule.io.JSON;
import com.skedulo.musicschedule.io.Reader;
import com.skedulo.musicschedule.io.Writer;

/**
 *
 * @author toanhx
 */
public class FactoryDAO {
    public static Reader getReader() {
        return new JSON();
    }
    
    public static Writer getWriter() {
        return new JSON();
    }
}
