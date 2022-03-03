/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skedulo.musicschedule.io;

import com.skedulo.musicschedule.object.Performance;
import java.util.List;

/**
 *
 * @author toanhx
 */
public interface Writer {
    public abstract void write(List<Performance> list, String path);
}
