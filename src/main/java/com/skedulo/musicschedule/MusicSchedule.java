/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skedulo.musicschedule;

import com.skedulo.musicschedule.io.Reader;
import com.skedulo.musicschedule.io.Writer;
import com.skedulo.musicschedule.object.Performance;
import com.skedulo.musicschedule.object.TimePoint;
import com.skedulo.musicschedule.object.TimeType;
import java.io.File;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author toanhx
 */
public class MusicSchedule {

    public static void main(String[] args) {
        String inputPath = args[0];

        // Read input
        Reader reader = FactoryDAO.getReader();
        List<Performance> input = reader.read(inputPath);

        // Do solution
        List<Performance> output = MusicSchedule.solution(input);

        // Write output
        File f = new File(inputPath);
        String outputPath = f.getParent() + "/"
                + f.getName().substring(0, f.getName().lastIndexOf("."))
                + ".optimal" + f.getName().substring(f.getName().lastIndexOf("."));
        Writer writer = FactoryDAO.getWriter();
        writer.write(output, outputPath);
    }

    public static List<Performance> solution(List<Performance> list) {   
        List<TimePoint> timeList = MusicSchedule.initTimePoint(list);

        List<Performance> currentSet = new LinkedList();
        List<Performance> intervalList = new LinkedList();
        ZonedDateTime lastStart = null;
        for (TimePoint time : timeList) {
            if (time.getType() == TimeType.START) {
                if (lastStart != null && !lastStart.equals(time.getTime())) {
                    Performance mostPriority = MusicSchedule.getMostPriorityPerformance(currentSet);
                    if (mostPriority != null) {
                        intervalList.add(
                                new Performance(
                                        mostPriority.getBand(),
                                        lastStart,
                                        time.getTime()
                                )
                        );
                    }
                }
                currentSet.add(time.getPerformance());
                lastStart = time.getTime();
            } else {
                if (!lastStart.equals(time.getTime())) {
                    Performance mostPriority = MusicSchedule.getMostPriorityPerformance(currentSet);
                    if (mostPriority != null) {
                        intervalList.add(
                                new Performance(
                                        mostPriority.getBand(),
                                        lastStart,
                                        time.getTime()
                                )
                        );
                    }
                }
                currentSet.remove(time.getPerformance());
                lastStart = time.getTime();
            }
        }
        
        List<Performance> result = MusicSchedule.mergeIntervalWithSameBand(intervalList);
        
        return result;
    }
    
    private static List<TimePoint> initTimePoint(List<Performance> list) {
        List<TimePoint> timeList = new LinkedList();
        for (Performance p : list) {
            timeList.add(new TimePoint(p.getStart(), TimeType.START, p));
            timeList.add(new TimePoint(p.getFinish(), TimeType.END, p));
        }
        Collections.sort(timeList);
        return timeList;
    }

    private static Performance getMostPriorityPerformance(List<Performance> list) {
        if (list.isEmpty()) {
            return null;
        }
        Performance mostPriority = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).getPriority() > mostPriority.getPriority()) {
                mostPriority = list.get(i);
            }
        }
        return mostPriority;
    }
    
    private static List<Performance> mergeIntervalWithSameBand(List<Performance> intervalList) {
        List<Performance> result = new LinkedList();
        int from = 0, to = 0;
        for (int i = 1; i < intervalList.size(); i++) {
            if (intervalList.get(i - 1).getBand().equals(intervalList.get(i).getBand())
                    && intervalList.get(i - 1).getFinish().equals(intervalList.get(i).getStart())) {
                to = i;
            } else {
                result.add(
                        new Performance(
                                intervalList.get(from).getBand(),
                                intervalList.get(from).getStart(),
                                intervalList.get(to).getFinish()
                        )
                );
                from = to = i;
            }
        }
        result.add(
                new Performance(
                        intervalList.get(from).getBand(),
                        intervalList.get(from).getStart(),
                        intervalList.get(to).getFinish()
                )
        );
        return result;
    }
}
