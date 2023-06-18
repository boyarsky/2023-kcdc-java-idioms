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
    // START writeWorkshopsSortedByRoomNumber()
    public void writeWorkshopsSortedByRoomNumber(Map<String, Workshop> map, Path outputPath) {
        // TODO Implement without using a loop
    }
    // END writeWorkshopsSortedByRoomNumber()

    // re-order so all lines with odd room numbers are first (keeping sort order within odds)
    // followed by all even room numbers (keeping sort order within evens)
    // write the result back to the file
    // START musicalRooms()
    public void musicalRooms(Path path) throws IOException {
        // TODO Implement without using a loop or reading the file multiple times
    }
    // END musicalRooms()

    // START shortestLine()
    public Optional<String> shortestLine(Path path) throws IOException {
        // TODO Implement calling lines()
        return null;
    }
    // END shortestLine()

    // START musicalRoomsStreamVersion()
    public void musicalRoomsStreamVersion(Path path) throws IOException {
        // TODO Implement calling lines() twice
    }
    // END musicalRoomsStreamVersion()

    // START deleteFileIfNotAbsolutePath()
    public boolean deleteFileIfNotAbsolutePath(Path path) throws IOException {
        // TODO Implement
        return false;
    }
    // END deleteFileIfNotAbsolutePath()

    // challenge
    // START absolutePathOfLargestLabSolutionFile()
    public Optional<String> absolutePathOfLargestLabSolutionFile() throws IOException {
        // TODO Implement using walk()
        return null;
    }
    // END absolutePathOfLargestLabSolutionFile()

    // build the date for today: June 21, 2023
    // START startOfKcdc()
    public LocalDate startOfKcdc() {
        //TODO implement by hardcoding LocalDate
        return null;
    }
    // END startOfKcdc()

    // add days to start to get last day of KCDC
    // START endOfKcdc()
    public LocalDate endOfKcdc() {
        //TODO implement by performing date arithmetic
        return null;
    }
    // END endOfKcdc()

    // START roomNumbersSmallerThan()
    public List<Integer> roomNumbersSmallerThan(Map<String, Workshop> map, int max) {
        //TODO implement using a stream and Math.min
        return null;
    }
    // END roomNumbersSmallerThan()

    // challenge
    // START getFiveRandomSessions()
    public List<Integer> getFiveRandomSessions(Map<String, Workshop> map) {
        //TODO implement to return any 5 random sessions (no duplicates)
        return null;
    }
    // END getFiveRandomSessions()
}
