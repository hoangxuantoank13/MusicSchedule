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
public class Performance implements Comparable<Performance> {
    private String band;
    private ZonedDateTime start;
    private ZonedDateTime finish;
    private int priority;

    public Performance(String band, ZonedDateTime start, ZonedDateTime finish) {
        this.band = band;
        this.start = start;
        this.finish = finish;
        this.priority = 0;
    }

    public Performance(String band, ZonedDateTime start, ZonedDateTime finish, int priority) {
        this.band = band;
        this.start = start;
        this.finish = finish;
        this.priority = priority;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public ZonedDateTime getStart() {
        return start;
    }

    public void setStart(ZonedDateTime start) {
        this.start = start;
    }

    public ZonedDateTime getFinish() {
        return finish;
    }

    public void setFinish(ZonedDateTime finish) {
        this.finish = finish;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }  

    @Override
    public int compareTo(Performance o) {
        return o.getPriority() - this.getPriority();
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
        final Performance other = (Performance) obj;
        if (this.priority != other.priority) {
            return false;
        }
        if (!Objects.equals(this.band, other.band)) {
            return false;
        }
        if (!Objects.equals(this.start, other.start)) {
            return false;
        }
        if (!Objects.equals(this.finish, other.finish)) {
            return false;
        }
        return true;
    }
    
    
}
