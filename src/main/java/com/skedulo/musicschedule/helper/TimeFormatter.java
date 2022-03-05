/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skedulo.musicschedule.helper;

import java.time.format.DateTimeFormatter;

/**
 *
 * @author toanhx
 */
public class TimeFormatter {
    public static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
}
