package com.jeanneboyarsky.lab;

import java.io.PrintStream;
import java.util.*;

public class Module3Lab {

    //TODO make workshop immutable
    public static class Workshop {
        private String title;
        private int roomNumber;
        private List<String> presenters;

        public Workshop(String title, int roomNumber, String... presenters) {
            this.title = title;
            this.roomNumber = roomNumber;
            //TODO implement
        }

        public String getTitle() {
            return title;
        }

        public int getRoomNumber() {
            return roomNumber;
        }

        public List<String> getPresenters() {
            //TODO implement
            return null;
        }
    }

    public Optional<Workshop> getOptionalByKey(Map<String, Workshop> map, String key) {
        //TODO implement without using an if statement, loop or stream
        return null;
    }

    public List<String> getPresentersNoStream(Map<String, Workshop> map, String title) {
        //TODO implement without using an if statement, loop or stream (ternary operator is ok)
        return null;
    }

    public List<String> getPresentersStream(Map<String, Workshop> map, String title) {
        //TODO implement with streams (no removeIf() or if statements or loops)
        return null;
    }

    public List<String> getSessionKeysWithMultiplePresentersNoStream(Map<String, Workshop> map) {
        //TODO implement without using an if statement, loop or stream
        return null;
    }



    public List<String> getSessionKeysWithMultiplePresentersStream(Map<String, Workshop> map) {
        //TODO implement with streams (no removeIf() or if statements or loops)
        return null;
    }

    public Optional<Integer> largestRoomNumberOnThirdFloor(Map<String, Workshop> map) {
        //TODO implement with streams (no removeIf() or if statements or loops)
        return null;
    }

    public Optional<String> titleOfRoomOneLowerThan(Map<String, Workshop> map, int roomNumber) {
        //TODO implement with streams (no removeIf() or if statements or loops)
        return null;
    }

    public String getTitlesAsCsvWithPrefix(Map<String, Workshop> map, String prefix) {
        //TODO implement with streams (no removeIf() or if statements or loops)
        return null;
    }

}