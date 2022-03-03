/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skedulo.musicschedule.object;

import java.time.ZonedDateTime;
import java.util.Objects;

/**
 *
 * @author toanhx
 */
public class TimePoint implements Comparable<TimePoint> {

    private ZonedDateTime time;
    private TimeType type;
    private Performance performance;

    public TimePoint(ZonedDateTime time, TimeType type, Performance performance) {
        this.time = time;
        this.type = type;
        this.performance = performance;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }

    public TimeType getType() {
        return type;
    }

    public void setType(TimeType type) {
        this.type = type;
    }

    @Override
    public int compareTo(TimePoint o) {
        if (this.getTime().isAfter(o.getTime())) {
            return 1;
        } else if (this.getTime().isBefore(o.getTime())) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TimePoint other = (TimePoint) obj;
        if (!Objects.equals(this.time, other.time)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        return true;
    }

    public Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }

}
