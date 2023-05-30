package com.jeanneboyarsky.lab;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;

public class Module4Lab {

    //TODO change Workshop to a record. how much code can you get rid of?
    public static class Workshop {
        private String title;
        private int roomNumber;
        private List<String> presenters;

        public Workshop(String title, int roomNumber, String... presenters) {
            this.title = title;
            this.roomNumber = roomNumber;
            //TODO implement (copy from module 3)
        }

        public String title() {
            return title;
        }

        public int roomNumber() {
            return roomNumber;
        }

        public List<String> presenters() {
            //TODO implement (copy from module 3)
            return null;
        }
    }

    // format: key,room,"title","presenters"
    public void writeWorkshopsSortedByRoomNumber(Map<String, Workshop> map, Path outputPath) {
        // TODO Implement without using a loop
    }

    // re-order so all lines with odd room numbers are first (keeping sort order within odds)
    // followed by all even room numbers (keeping sort order within evens)
    // write the result back to the file
    public void musicalRooms(Path path) throws IOException {
        // TODO Implement without using a loop or reading the file multiple times
    }

    public Optional<String> shortestLine(Path path) throws IOException {
        // TODO Implement calling lines()
        return null;
    }

    public void musicalRoomsStreamVersion(Path path) throws IOException {
        // TODO Implement calling lines() twice
    }

    public boolean deleteFileIfNotAbsolutePath(Path path) throws IOException {
        // TODO Implement
        return false;
    }

    // challenge
    public Optional<String> absolutePathOfLargestLabSolutionFile() throws IOException {
        // TODO Implement using walk()
        return null;
    }

    // build the date for today: February 19, 2020
    public LocalDate startOfDevNexus() {
        //TODO implement by hardcoding LocalDate
        return null;
    }

    // add days to start to get last day of Dev Nexus
    public LocalDate endOfDevNexus() {
        //TODO implement by performing date arithmetic
        return null;
    }

    public List<Integer> roomNumbersSmallerThan(Map<String, Workshop> map, int max) {
        //TODO implement using a stream and Math.min
        return null;
    }

    // challenge
    public List<Integer> getFiveRandomSessions(Map<String, Workshop> map) {
        //TODO implement to return any 5 random sessions (no duplicates)
        return null;
    }
}
