/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skedulo.musicschedule.io;

import com.skedulo.musicschedule.helper.TimeFormatter;
import com.skedulo.musicschedule.object.Performance;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author toanhx
 */
public class JSON implements Reader, Writer {

    

    @Override
    public List<Performance> read(String path) {
        List<Performance> result = new LinkedList();
        try {
            File f = new File(path);
            if (f.exists()) {
                InputStream is = new FileInputStream(path);
                String jsonTxt = IOUtils.toString(is, "UTF-8");
                JSONArray a = new JSONArray(jsonTxt);
                for (int i = 0; i < a.length(); i++) {
                    JSONObject performance = a.getJSONObject(i);
                    Performance p = new Performance(
                            performance.getString("band"),
                            ZonedDateTime.parse(performance.getString("start"), TimeFormatter.FORMATTER),
                            ZonedDateTime.parse(performance.getString("finish"), TimeFormatter.FORMATTER),
                            performance.getInt("priority")
                    );
                    result.add(p);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void write(List<Performance> list, String path) {
        JSONArray array = new JSONArray();
        for (Performance p : list) {
            Map o = new LinkedHashMap();
//            o.put("priority", p.getPriority());
            o.put("start", TimeFormatter.FORMATTER.format(p.getStart()));
            o.put("band", p.getBand());
            o.put("finish", TimeFormatter.FORMATTER.format(p.getFinish()));
            array.put(o);
        }

        try (PrintWriter pw = new PrintWriter(path)) {
            pw.write(array.toString(2));

            pw.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JSON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
