How to run
  - cd to project directory (such as ~/MusicSchedule)
  - run: src/main/resources/verifier/verify-music.sh src/main/resources/verifier/run.sh

Approach
  - Each performance is presented as a Performance object: band, start time, finish time and priority
  - Read the input file and store input data into a list of Performance.
  - Have a TimePoint object that presents a timepoint: time, time type (start time or finish time), and Performance that this TimePoint belong to.
  - Every Performance will have 2 TimePoints (Start TimePoint and Finish TimePoint). Iterate list of Performance and create a list of Timepoint.
  - Initialize a variable intervalList that will contain list of interval after we reschedule. Each interval can present the whole of Performance or a segrament of Performane.
  - Initialize a variable lastStart that presents the time of previous TimePoint.
  - Initialize a variable currentList that will contain list of Performance of a interval
  - Iterate list of TimePoint, for each TimePoint: 
    + if lastStart is different with the time of current timepoint, find the most priority performance (in currentList), then create a new interval and add it into intervalList.
    + if current timepoint is a start time, we will add the performance of this timepoint into currentList
    + else (current timepoint is a finish time) we will remove the performace of this timepoint from currentList
    + lastStart = the time of current timepoint.
  - Adjust intervalList, if two sequence intervals have the same band, merge them into one interval
  - Write intervalList into output file.

Unit Test
  - Used Junit to write some unit test.

Expansion in the future
  - Instead of read/write from a JSON file, we can use other format, such as XML
  - Easy to change the date time format

Note
  - In my output file, I print
  { 
    "start" : "1993-05-25T02:00:00Z", 
    "finish" : "1993-05-25T02:15:00Z",
    "band" : "Soundgarden"
  }
  instead of 
  { 
    "band" : "Soundgarden",
    "start" : "1993-05-25T02:00:00Z", 
    "finish" : "1993-05-25T02:15:00Z",
  }
  so I changed .expected file (just change the order of fields).
