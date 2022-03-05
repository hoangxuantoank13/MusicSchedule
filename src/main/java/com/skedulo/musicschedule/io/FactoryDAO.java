/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skedulo.musicschedule.io;

/**
 *
 * @author toanhx
 */
public class FactoryDAO {
    // We can do an expansion here, so we read/write from some other formats, such as XML
    // What we need to do here is to create a XML class that implements from Reader/Writer and implement its behavious.
    // With Factory pattern, we can return any file format we want, but now it is a expansion
    
    public static Reader getReader() {
        return new JSON();
    }
    
    public static Writer getWriter() {
        return new JSON();
    }
}
