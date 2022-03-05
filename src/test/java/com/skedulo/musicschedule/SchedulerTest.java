/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skedulo.musicschedule;

import com.skedulo.musicschedule.helper.TimeFormatter;
import com.skedulo.musicschedule.object.Performance;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author toanhx
 */
public class SchedulerTest {

    public SchedulerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Empty input
     */
    @Test
    public void testScheduleCase1() {
        Scheduler instance = new Scheduler(Collections.EMPTY_LIST);
        List<Performance> expResult = Collections.EMPTY_LIST;
        List<Performance> result = instance.schedule();
        assertEquals(expResult, result);
    }

    /**
     * 2 performances don't overlap
     */
    @Test
    public void testScheduleCase2() {
        List<Performance> input = Arrays.asList(
                new Performance(
                        "Performance 1",
                        ZonedDateTime.parse("1993-05-25T02:00:00Z", TimeFormatter.FORMATTER),
                        ZonedDateTime.parse("1993-05-25T02:10:00Z", TimeFormatter.FORMATTER),
                        1
                ),
                new Performance(
                        "Performance 2",
                        ZonedDateTime.parse("1993-05-25T02:20:00Z", TimeFormatter.FORMATTER),
                        ZonedDateTime.parse("1993-05-25T02:30:00Z", TimeFormatter.FORMATTER),
                        2
                )
        );
        Scheduler instance = new Scheduler(input);
        List<Performance> expResult = input;
        List<Performance> result = instance.schedule();
        assertEquals(expResult, result);
    }

    /**
     * 2 performances overlap
     */
    @Test
    public void testScheduleCase3() {
        List<Performance> input = Arrays.asList(
                new Performance(
                        "Performance 1",
                        ZonedDateTime.parse("1993-05-25T02:00:00Z", TimeFormatter.FORMATTER),
                        ZonedDateTime.parse("1993-05-25T02:30:00Z", TimeFormatter.FORMATTER),
                        1
                ),
                new Performance(
                        "Performance 2",
                        ZonedDateTime.parse("1993-05-25T02:20:00Z", TimeFormatter.FORMATTER),
                        ZonedDateTime.parse("1993-05-25T02:40:00Z", TimeFormatter.FORMATTER),
                        2
                )
        );
        Scheduler instance = new Scheduler(input);
        List<Performance> expResult = Arrays.asList(
                new Performance(
                        "Performance 1",
                        ZonedDateTime.parse("1993-05-25T02:00:00Z", TimeFormatter.FORMATTER),
                        ZonedDateTime.parse("1993-05-25T02:20:00Z", TimeFormatter.FORMATTER),
                        1
                ),
                new Performance(
                        "Performance 2",
                        ZonedDateTime.parse("1993-05-25T02:20:00Z", TimeFormatter.FORMATTER),
                        ZonedDateTime.parse("1993-05-25T02:40:00Z", TimeFormatter.FORMATTER),
                        2
                )
        );;
        List<Performance> result = instance.schedule();
        assertEquals(expResult, result);
    }

    /**
     * 2 performances happen in the same time, but difference priority
     */
    @Test
    public void testScheduleCase4() {
        List<Performance> input = Arrays.asList(
                new Performance(
                        "Performance 1",
                        ZonedDateTime.parse("1993-05-25T02:00:00Z", TimeFormatter.FORMATTER),
                        ZonedDateTime.parse("1993-05-25T02:30:00Z", TimeFormatter.FORMATTER),
                        1
                ),
                new Performance(
                        "Performance 2",
                        ZonedDateTime.parse("1993-05-25T02:00:00Z", TimeFormatter.FORMATTER),
                        ZonedDateTime.parse("1993-05-25T02:30:00Z", TimeFormatter.FORMATTER),
                        2
                )
        );
        Scheduler instance = new Scheduler(input);
        List<Performance> expResult = Arrays.asList(
                new Performance(
                        "Performance 2",
                        ZonedDateTime.parse("1993-05-25T02:00:00Z", TimeFormatter.FORMATTER),
                        ZonedDateTime.parse("1993-05-25T02:30:00Z", TimeFormatter.FORMATTER),
                        2
                )
        );;
        List<Performance> result = instance.schedule();
        assertEquals(expResult, result);
    }

    /**
     * 2 performances start in the same time, but difference finish time and
     */
    @Test
    public void testScheduleCase5() {
        List<Performance> input = Arrays.asList(
                new Performance(
                        "Performance 1",
                        ZonedDateTime.parse("1993-05-25T02:00:00Z", TimeFormatter.FORMATTER),
                        ZonedDateTime.parse("1993-05-25T02:40:00Z", TimeFormatter.FORMATTER),
                        1
                ),
                new Performance(
                        "Performance 2",
                        ZonedDateTime.parse("1993-05-25T02:00:00Z", TimeFormatter.FORMATTER),
                        ZonedDateTime.parse("1993-05-25T02:30:00Z", TimeFormatter.FORMATTER),
                        2
                )
        );
        Scheduler instance = new Scheduler(input);
        List<Performance> expResult = Arrays.asList(
                new Performance(
                        "Performance 2",
                        ZonedDateTime.parse("1993-05-25T02:00:00Z", TimeFormatter.FORMATTER),
                        ZonedDateTime.parse("1993-05-25T02:30:00Z", TimeFormatter.FORMATTER),
                        2
                ),
                new Performance(
                        "Performance 1",
                        ZonedDateTime.parse("1993-05-25T02:30:00Z", TimeFormatter.FORMATTER),
                        ZonedDateTime.parse("1993-05-25T02:40:00Z", TimeFormatter.FORMATTER),
                        1
                )
        );
        List<Performance> result = instance.schedule();
        assertEquals(expResult, result);
    }
    
    /**
     * 2 performances finish in the same time, but different start time and
     */
    @Test
    public void testScheduleCase6() {
        List<Performance> input = Arrays.asList(
                new Performance(
                        "Performance 1",
                        ZonedDateTime.parse("1993-05-25T02:00:00Z", TimeFormatter.FORMATTER),
                        ZonedDateTime.parse("1993-05-25T02:30:00Z", TimeFormatter.FORMATTER),
                        1
                ),
                new Performance(
                        "Performance 2",
                        ZonedDateTime.parse("1993-05-25T02:10:00Z", TimeFormatter.FORMATTER),
                        ZonedDateTime.parse("1993-05-25T02:30:00Z", TimeFormatter.FORMATTER),
                        2
                )
        );
        Scheduler instance = new Scheduler(input);
        List<Performance> expResult = Arrays.asList(
                new Performance(
                        "Performance 1",
                        ZonedDateTime.parse("1993-05-25T02:00:00Z", TimeFormatter.FORMATTER),
                        ZonedDateTime.parse("1993-05-25T02:10:00Z", TimeFormatter.FORMATTER),
                        1
                ),
                new Performance(
                        "Performance 2",
                        ZonedDateTime.parse("1993-05-25T02:10:00Z", TimeFormatter.FORMATTER),
                        ZonedDateTime.parse("1993-05-25T02:30:00Z", TimeFormatter.FORMATTER),
                        2
                )
        );
        List<Performance> result = instance.schedule();
        assertEquals(expResult, result);
    }
    
    /**
     * 2 performances happen in the same time and priority
     */
    @Test
    public void testScheduleCase7() {
        List<Performance> input = Arrays.asList(
                new Performance(
                        "Performance 1",
                        ZonedDateTime.parse("1993-05-25T02:00:00Z", TimeFormatter.FORMATTER),
                        ZonedDateTime.parse("1993-05-25T02:30:00Z", TimeFormatter.FORMATTER),
                        1
                ),
                new Performance(
                        "Performance 2",
                        ZonedDateTime.parse("1993-05-25T02:00:00Z", TimeFormatter.FORMATTER),
                        ZonedDateTime.parse("1993-05-25T02:30:00Z", TimeFormatter.FORMATTER),
                        1
                )
        );
        Scheduler instance = new Scheduler(input);
        List<Performance> expResult = Arrays.asList(
                new Performance(
                        "Performance 1",
                        ZonedDateTime.parse("1993-05-25T02:00:00Z", TimeFormatter.FORMATTER),
                        ZonedDateTime.parse("1993-05-25T02:30:00Z", TimeFormatter.FORMATTER),
                        1
                )
        );
        List<Performance> result = instance.schedule();
        assertEquals(expResult, result);
    }
    
    /**
     * 2 performances that the higher priority performance overlap all the smaller priority performance
     *
     */
    @Test
    public void testScheduleCase8() {
        List<Performance> input = Arrays.asList(
                new Performance(
                        "Performance 1",
                        ZonedDateTime.parse("1993-05-25T02:10:00Z", TimeFormatter.FORMATTER),
                        ZonedDateTime.parse("1993-05-25T02:30:00Z", TimeFormatter.FORMATTER),
                        1
                ),
                new Performance(
                        "Performance 2",
                        ZonedDateTime.parse("1993-05-25T02:00:00Z", TimeFormatter.FORMATTER),
                        ZonedDateTime.parse("1993-05-25T02:40:00Z", TimeFormatter.FORMATTER),
                        2
                )
        );
        Scheduler instance = new Scheduler(input);
        List<Performance> expResult = Arrays.asList(
                new Performance(
                        "Performance 2",
                        ZonedDateTime.parse("1993-05-25T02:00:00Z", TimeFormatter.FORMATTER),
                        ZonedDateTime.parse("1993-05-25T02:40:00Z", TimeFormatter.FORMATTER),
                        2
                )
        );
        List<Performance> result = instance.schedule();
        assertEquals(expResult, result);
    }

}
