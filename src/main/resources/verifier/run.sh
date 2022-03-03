#!/usr/bin/env bash
JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_171.jdk/Contents/Home
mvn clean install
mvn exec:java -Dexec.args="$1" -Dexec.mainClass=com.skedulo.musicschedule.MusicSchedule

