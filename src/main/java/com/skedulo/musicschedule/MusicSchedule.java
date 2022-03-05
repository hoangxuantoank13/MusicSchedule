/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skedulo.musicschedule;

import com.skedulo.musicschedule.io.FactoryDAO;
import com.skedulo.musicschedule.io.Reader;
import com.skedulo.musicschedule.io.Writer;
import com.skedulo.musicschedule.object.Performance;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author toanhx
 */
public class MusicSchedule {

    public static void main(String[] args) {
        String inputPath = args[0];

        try {
            // Read input
            Reader reader = FactoryDAO.getReader();
            List<Performance> input = reader.read(inputPath);

            // optimal schedule
            Scheduler scheduler = new Scheduler(input);
            List<Performance> output = scheduler.schedule();

            // Write output
            String outputPath = inputPath.substring(0, inputPath.lastIndexOf("."))
                    + ".optimal" + inputPath.substring(inputPath.lastIndexOf("."));
            Writer writer = FactoryDAO.getWriter();
            writer.write(output, outputPath);
        } catch (Exception ex) {
            Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
