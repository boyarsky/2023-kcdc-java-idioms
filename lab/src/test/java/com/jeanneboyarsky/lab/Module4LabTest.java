package com.jeanneboyarsky.lab;

import static com.jeanneboyarsky.lab.Module4Lab.Workshop;

import com.jeanneboyarsky.rules.CodeRulesForMethods;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class Module4LabTest {

    private Module4Lab target;
    private Map<String, Workshop> workshops;
    // TODO not using due to https://github.com/javaparser/javaparser/issues/3556
    //private CodeRulesForMethods codeRules;

    private Path path;

    @BeforeEach
    void devNexusWorkshops() {
        workshops = Map.ofEntries(
                Map.entry("Kotlin",
                        new Workshop("Kotlin Full Day Workshop", 302,
                                "Kenneth Kousen")),
                Map.entry("TDD",
                        new Workshop("Test Driven Development: From Principles to Practice", 303
                                , "Venkat Subramaniam")),
                Map.entry("Idioms",
                        new Workshop("Java Idioms for becoming a more powerful developer", 304,
                                "Jeanne Boyarsky")),
                Map.entry("Security",
                        new Workshop("Java Security Workshop", 305,
                                "Steve Poole", "Brian Vermeer")),
                Map.entry("Advanced-Kubernetes",
                        new Workshop("Advanced Kubernetes workshop", 311,
                                "Adarsh Shah")),
                Map.entry("Kubernetes-101",
                        new Workshop("Kubernetes 101 Workshop", 312,
                                "JJ Asghar")),
                Map.entry("CloudNativeMicroprofile",
                        new Workshop("Cloud Native Microservice with MicroProfile, Docker, Kubernetes, Istio and Open Shift",
                                313, "Emily Jiang")),
                Map.entry("Quarkus",
                        new Workshop("The Quarkus Tutorial", 314,
                                "Edson Yanaga")),
                Map.entry("SpringBoot",
                        new Workshop("Extending Spring Boot for Enterprise", 315,
                                "Billy Korando")),
                Map.entry("Microservices",
                        new Workshop("Responsible Microservices Architecture", 403,
                                "Nathaniel Schutta")),
                Map.entry("DDD",
                        new Workshop("Domain Driven Design Workshop", 404,
                                "Rob Curry", "Kelly Morrison", "Tony Stuchel", "Steve Fordham", "Sharma Vedula")),
                Map.entry("CloudNativeSpringBoot",
                        new Workshop("Google Cloud Native with Spring Boot", 405,
                                "Ray Tsang", "James Ward")));
        target = new Module4Lab();
        path = Paths.get("lab/src/main/resources/workshops.csv");
        target.writeWorkshopsSortedByRoomNumber(workshops, path);

        Path folder = Paths.get("lab/src/main/java/");
        // TODO not using due to https://github.com/javaparser/javaparser/issues/3556
        //codeRules = new CodeRulesForMethods(folder, "com.jeanneboyarsky.lab",
          //      "Module4Lab.java");
    }

    @AfterEach
    void tearDown() throws IOException {
        if (path != null) {
            Files.deleteIfExists(path);
        }
    }

    // ---------------------------------------------------------

    @Test
    void requirements_writeWorkshopsSortedByRoomNumber() {
        // TODO not using due to https://github.com/javaparser/javaparser/issues/3556
        //assertFalse(codeRules.containsLoop("writeWorkshopsSortedByRoomNumber"),
          //      "must not contain loop");
    }

    // ---------------------------------------------------------

    @Test
    void readFile() throws IOException {
        List<String> expected = List.of("Kotlin,302,\"Kotlin Full Day Workshop\",\"Kenneth Kousen\"",
                "TDD,303,\"Test Driven Development: From Principles to Practice\",\"Venkat Subramaniam\"",
                "Idioms,304,\"Java Idioms for becoming a more powerful developer\",\"Jeanne Boyarsky\"",
                "Security,305,\"Java Security Workshop\",\"Steve Poole,Brian Vermeer\"",
                "Advanced-Kubernetes,311,\"Advanced Kubernetes workshop\",\"Adarsh Shah\"",
                "Kubernetes-101,312,\"Kubernetes 101 Workshop\",\"JJ Asghar\"",
                "CloudNativeMicroprofile,313,\"Cloud Native Microservice with MicroProfile, Docker, Kubernetes, Istio and Open Shift\",\"Emily Jiang\"",
                "Quarkus,314,\"The Quarkus Tutorial\",\"Edson Yanaga\"",
                "SpringBoot,315,\"Extending Spring Boot for Enterprise\",\"Billy Korando\"",
                "Microservices,403,\"Responsible Microservices Architecture\",\"Nathaniel Schutta\"",
                "DDD,404,\"Domain Driven Design Workshop\",\"Rob Curry,Kelly Morrison,Tony Stuchel,Steve Fordham,Sharma Vedula\"",
                "CloudNativeSpringBoot,405,\"Google Cloud Native with Spring Boot\",\"Ray Tsang,James Ward\"");
        List<String> actual = Files.readAllLines(path);
        assertEquals(expected, actual);
    }

    // ---------------------------------------------------------

    @Test
    void musicalRooms_sortedOddsFirst() throws IOException {
        List<String> lines = List.of("key,123,name", "key,456,name", "key,7,name");
        Files.write(path, lines);
        List<String> expected = List.of("key,123,name", "key,7,name", "key,456,name");
        target.musicalRooms(path);
        List<String> actual = Files.readAllLines(path);
        assertEquals(expected, actual);
    }

    @Test
    void musicalRooms() throws IOException {
        List<String> expected = List.of("TDD,303,\"Test Driven Development: From Principles to Practice\",\"Venkat Subramaniam\"",
                "Security,305,\"Java Security Workshop\",\"Steve Poole,Brian Vermeer\"",
                "Advanced-Kubernetes,311,\"Advanced Kubernetes workshop\",\"Adarsh Shah\"",
                "CloudNativeMicroprofile,313,\"Cloud Native Microservice with MicroProfile, Docker, Kubernetes, Istio and Open Shift\",\"Emily Jiang\"",
                "SpringBoot,315,\"Extending Spring Boot for Enterprise\",\"Billy Korando\"",
                "Microservices,403,\"Responsible Microservices Architecture\",\"Nathaniel Schutta\"",
                "CloudNativeSpringBoot,405,\"Google Cloud Native with Spring Boot\",\"Ray Tsang,James Ward\"",
                "Kotlin,302,\"Kotlin Full Day Workshop\",\"Kenneth Kousen\"",
                "Idioms,304,\"Java Idioms for becoming a more powerful developer\",\"Jeanne Boyarsky\"",
                "Kubernetes-101,312,\"Kubernetes 101 Workshop\",\"JJ Asghar\"",
                "Quarkus,314,\"The Quarkus Tutorial\",\"Edson Yanaga\"",
                "DDD,404,\"Domain Driven Design Workshop\",\"Rob Curry,Kelly Morrison,Tony Stuchel,Steve Fordham,Sharma Vedula\"");
        target.musicalRooms(path);
        List<String> actual = Files.readAllLines(path);
        assertEquals(expected, actual);
    }

    @Test
    void requirements_musicalRooms() {
        // TODO not using due to https://github.com/javaparser/javaparser/issues/3556
        //assertFalse(codeRules.containsLoop("musicalRooms"),
          //      "must not contain loop");
    }

    // ---------------------------------------------------------
    @Test
    void shortestLine() throws IOException {
        String expected = "Quarkus,314,\"The Quarkus Tutorial\",\"Edson Yanaga\"";
        Optional<String> actual = target.shortestLine(path);
        assertEquals(expected, actual.get());
    }

    @Test
    void requirements_shortestLine() {
        // TODO not using due to https://github.com/javaparser/javaparser/issues/3556
        //assertTrue(codeRules.containsLines("shortestLine"),
          //      "must contains lines()");
    }

    // ---------------------------------------------------------

    @Test
    void musicalRoomsStreamVersion_sortedOddsFirst() throws IOException {
        List<String> lines = List.of("key,123,name", "key,456,name", "key,7,name");
        Files.write(path, lines);
        List<String> expected = List.of("key,123,name", "key,7,name", "key,456,name");
        target.musicalRoomsStreamVersion(path);
        List<String> actual = Files.readAllLines(path);
        assertEquals(expected, actual);
    }

    @Test
    void musicalRoomsStreamVersion() throws IOException {
        List<String> expected = List.of("TDD,303,\"Test Driven Development: From Principles to Practice\",\"Venkat Subramaniam\"",
                "Security,305,\"Java Security Workshop\",\"Steve Poole,Brian Vermeer\"",
                "Advanced-Kubernetes,311,\"Advanced Kubernetes workshop\",\"Adarsh Shah\"",
                "CloudNativeMicroprofile,313,\"Cloud Native Microservice with MicroProfile, Docker, Kubernetes, Istio and Open Shift\",\"Emily Jiang\"",
                "SpringBoot,315,\"Extending Spring Boot for Enterprise\",\"Billy Korando\"",
                "Microservices,403,\"Responsible Microservices Architecture\",\"Nathaniel Schutta\"",
                "CloudNativeSpringBoot,405,\"Google Cloud Native with Spring Boot\",\"Ray Tsang,James Ward\"",
                "Kotlin,302,\"Kotlin Full Day Workshop\",\"Kenneth Kousen\"",
                "Idioms,304,\"Java Idioms for becoming a more powerful developer\",\"Jeanne Boyarsky\"",
                "Kubernetes-101,312,\"Kubernetes 101 Workshop\",\"JJ Asghar\"",
                "Quarkus,314,\"The Quarkus Tutorial\",\"Edson Yanaga\"",
                "DDD,404,\"Domain Driven Design Workshop\",\"Rob Curry,Kelly Morrison,Tony Stuchel,Steve Fordham,Sharma Vedula\"");
        target.musicalRoomsStreamVersion(path);
        List<String> actual = Files.readAllLines(path);
        assertEquals(expected, actual);
    }

    @Test
    void requirements_musicalRoomsStreamVersion() {
        // TODO not using due to https://github.com/javaparser/javaparser/issues/3556
        //assertTrue(codeRules.containsLines("musicalRoomsStreamVersion"),
          //      "must contains lines()");
    }

    // ---------------------------------------------------------

    @Test
    void deleteFileIfNotAbsolutePath_deleting() throws IOException {
        boolean actual = target.deleteFileIfNotAbsolutePath(path);
        assertTrue(actual, "deleted");
        assertFalse(Files.exists(path), "exists");
    }

    @Test
    void deleteFileIfNotAbsolutePath_notDeleting() throws IOException {
        Path absolute = Paths.get("/temp");
        boolean actual = target.deleteFileIfNotAbsolutePath(absolute);
        assertFalse(actual, "deleted");
    }

    // ---------------------------------------------------------

    @Test
    void absolutePathOfLargestLabSolutionFile() throws IOException {
        Optional<String> actual = target.absolutePathOfLargestLabSolutionFile();
        assertTrue(actual.get().endsWith("java"));
    }

    @Test
    void requirements_absolutePathOfLargestLabSolutionFile() {
        // TODO not using due to https://github.com/javaparser/javaparser/issues/3556
        //assertTrue(codeRules.containsWalk("absolutePathOfLargestLabSolutionFile"),
          //      "must contains walk()");
    }

    // ---------------------------------------------------------

    @Test
    void startOfDevNexus() {
        LocalDate actual = target.startOfDevNexus();
        assertEquals(2020, actual.getYear());
        assertEquals(Month.FEBRUARY, actual.getMonth());
        assertEquals(19, actual.getDayOfMonth());
    }

    @Test
    void endOfDevNexus() {
        LocalDate actual = target.endOfDevNexus();
        assertEquals(2020, actual.getYear());
        assertEquals(Month.FEBRUARY, actual.getMonth());
        assertEquals(21, actual.getDayOfMonth());
    }

    @Test
    void roomNumbersSmallerThan() {
        List<Integer> expected = List.of(302, 303, 304, 305);
        List<Integer> actual = target.roomNumbersSmallerThan(workshops,310);
        assertEquals(expected, actual);
    }

    @Test
    void requirements_roomNumbersSmallerThan() {
        // TODO not using due to https://github.com/javaparser/javaparser/issues/3556
        //assertTrue(codeRules.containsStream("roomNumbersSmallerThan"),
          //      "must contain stream");
        //assertTrue(codeRules.containsMathMin("roomNumbersSmallerThan"),
          //      "must contain Math.min");
    }

    @RepeatedTest(100)
    void getFiveRandomSessions() {
        List<Integer> actual = target.getFiveRandomSessions(workshops);
        Set<Integer> unique = new HashSet<>(actual);
        assertEquals(5, unique.size());
    }
}
