/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skedulo.musicschedule;

import com.skedulo.musicschedule.object.Performance;
import com.skedulo.musicschedule.object.TimePoint;
import com.skedulo.musicschedule.object.TimeType;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author toanhx
 */
public class Scheduler {

    private List<Performance> input;

    public Scheduler(List<Performance> input) {
        this.input = input;
    }

    public List<Performance> schedule() {
        // initialize a list of TimePoint
        // Each TimePoint will contain a specific time, a time type (start/finish) and the performance happens in this time.
        List<TimePoint> timeList = this.initTimePoint(input);
        if (timeList.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        
        // For each Timepoint, 
        // if last start time is different with the time of current timepoint, 
        // we will have a interval that contains some performances, choose the most priority performance
        //
        // if it is a start time, we will add the performance of this timepoint into the current performance list
        // else it is a finish time, we will remove the performace of this timepoint away the current performance list
        List<Performance> intervalList = new LinkedList();
        List<Performance> currentList = new LinkedList();
        ZonedDateTime lastStart = timeList.get(0).getTime();
        for (TimePoint time : timeList) {
            if (!lastStart.equals(time.getTime())) {  // // if 2 timepoints have the same time, ignore
                Performance mostPriority = this.getMostPriorityPerformance(currentList); // get the most priority performance 
                if (mostPriority != null) {
                    intervalList.add(
                            new Performance(
                                    mostPriority.getBand(),
                                    lastStart,
                                    time.getTime(),
                                    mostPriority.getPriority()
                            )
                    );
                }
            }

            if (time.getType() == TimeType.START) {
                currentList.add(time.getPerformance());
            } else {
                currentList.remove(time.getPerformance());
            }
            
            lastStart = time.getTime();
        }

        // if two sequence intervals with the same band, merge them into one interval
        List<Performance> result = this.mergeIntervalWithSameBand(intervalList);

        return result;
    }

    private List<TimePoint> initTimePoint(List<Performance> input) {
        List<TimePoint> timeList = new LinkedList();
        for (Performance p : input) {
            timeList.add(new TimePoint(p.getStart(), TimeType.START, p));
            timeList.add(new TimePoint(p.getFinish(), TimeType.END, p));
        }
        Collections.sort(timeList);
        return timeList;
    }

    private Performance getMostPriorityPerformance(List<Performance> list) {
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

    private List<Performance> mergeIntervalWithSameBand(List<Performance> intervalList) {
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
                                intervalList.get(to).getFinish(),
                                intervalList.get(from).getPriority()
                        )
                );
                from = to = i;
            }
        }
        result.add(
                new Performance(
                        intervalList.get(from).getBand(),
                        intervalList.get(from).getStart(),
                        intervalList.get(to).getFinish(),
                        intervalList.get(from).getPriority()
                )
        );
        return result;
    }
}
