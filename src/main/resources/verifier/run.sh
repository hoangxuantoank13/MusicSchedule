#!/usr/bin/env bash
mvn exec:java -Dexec.args="$1" -Dexec.mainClass=com.skedulo.musicschedule.MusicSchedule

