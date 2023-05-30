package com.jeanneboyarsky.lab;

import static com.jeanneboyarsky.lab.Module3Lab.Workshop;

import com.jeanneboyarsky.rules.CodeRulesForMethods;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class Module3LabTest {

    private Module3Lab target;
    private Map<String, Workshop> workshops;
    private CodeRulesForMethods codeRules;

    @BeforeEach
    void setUp() {
        target = new Module3Lab();
        Path folder = Paths.get("lab/src/main/java/");
        codeRules = new CodeRulesForMethods(folder, "com.jeanneboyarsky.lab",
                "Module3Lab.java");
    }

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
    }

    // ---------------------------------------------------------

    @Test
    void constructor() {
        Workshop actual = new Workshop("title", 999, "speaker");
        assertEquals("title", actual.getTitle());
        assertEquals(999, actual.getRoomNumber());
        List<String> presenters = actual.getPresenters();
        assertEquals(1, presenters.size(), "# presenters");
        assertEquals("speaker", presenters.get(0));
    }

    @Test
    void constructorParameterDoesNotAffectValue() {
        String[] array = new String[] { "original"};
        Workshop actual = new Workshop("title", 999, array);
        array[0] = "new";
        List<String> presenters = actual.getPresenters();
        assertEquals(1, presenters.size(), "# presenters");
        assertEquals("original", presenters.get(0));
    }

    @Test
    void getPresentersImmutable() {
        Workshop actual = new Workshop("title", 999, "speaker");
        List<String> presenters1 = actual.getPresenters();
        List<String> presenters2 = actual.getPresenters();
        assertNotSame(presenters1, presenters2);
    }

    // ---------------------------------------------------------

    @Test
    void getOptionalByKey_match() {
        Optional<Workshop> actual = target.getOptionalByKey(workshops, "Kotlin");
        assertEquals("Kotlin Full Day Workshop", actual.get().getTitle());
    }

    @Test
    void getOptionalByKey_noMatch() {
        Optional<Workshop> actual = target.getOptionalByKey(workshops, "Bad");
        assertFalse(actual.isPresent());
    }

    @Test
    void requirements_getOptionalByKey() {
        assertFalse(codeRules.containsIf("getOptionalByKey"),
                "cannot contain if statement");
        assertFalse(codeRules.containsLoop("getOptionalByKey"),
                "cannot contain a loop");
        assertFalse(codeRules.containsStream("getOptionalByKey"),
                "cannot contain stream");
    }

    // ---------------------------------------------------------

    @Test
    void getPresentersNoStream_foundSingle() {
        List<String> expected = List.of("Billy Korando");
        List<String> actual = target.getPresentersNoStream(workshops, "Extending Spring Boot for Enterprise");
        assertEquals(expected, actual);
    }

    @Test
    void getPresentersNoStream_foundTwo() {
        List<String> expected = List.of("Ray Tsang", "James Ward");
        List<String> actual = target.getPresentersNoStream(workshops, "Google Cloud Native with Spring Boot");
        assertEquals(expected, actual);
    }

    @Test
    void getPresentersNoStream_foundNone() {
        List<String> actual = target.getPresentersNoStream(workshops, "Bad");
        assertEquals(0, actual.size());
    }

    @Test
    void requirements_getPresentersNoStream() {
        assertFalse(codeRules.containsIf("getPresentersNoStream"),
                "cannot contain if statement");
        assertFalse(codeRules.containsLoop("getPresentersNoStream"),
                "cannot contain a loop");
        assertFalse(codeRules.containsStream("getPresentersNoStream"),
                "cannot contain stream");
    }

    // ---------------------------------------------------------

    @Test
    void getPresentersStream_foundSingle() {
        List<String> expected = List.of("Billy Korando");
        List<String> actual = target.getPresentersStream(workshops, "Extending Spring Boot for Enterprise");
        assertEquals(expected, actual);
    }

    @Test
    void getPresentersStream_foundTwo() {
        List<String> expected = List.of("Ray Tsang", "James Ward");
        List<String> actual = target.getPresentersStream(workshops, "Google Cloud Native with Spring Boot");
        assertEquals(expected, actual);
    }

    @Test
    void getPresentersStream_foundNone() {
        List<String> actual = target.getPresentersStream(workshops, "Bad");
        assertEquals(0, actual.size());
    }

    @Test
    void requirements_getPresentersStream() {
        assertTrue(codeRules.containsStream("getPresentersStream"),
                "must contains stream()");
        assertFalse(codeRules.containsIf("getPresentersStream"),
                "cannot contain if statement");
        assertFalse(codeRules.containsLoop("getPresentersStream"),
                "cannot contain a loop");
        assertFalse(codeRules.containsRemoveIf("getPresentersStream"),
                "cannot contain removeIf");
    }

    // ---------------------------------------------------------

    @Test
    void getSessionKeysWithMultiplePresentersNoStream() {
        List<String> expected = List.of("Security", "DDD", "CloudNativeSpringBoot");
        List<String> actual = target.getSessionKeysWithMultiplePresentersNoStream(workshops);
        assertEquals(expected, actual);
    }

    @Test
    void requirements_getSessionKeysWithMultiplePresentersNoStream() {
        assertFalse(codeRules.containsIf("getSessionKeysWithMultiplePresentersNoStream"),
                "cannot contain if statement");
        assertFalse(codeRules.containsLoop("getSessionKeysWithMultiplePresentersNoStream"),
                "cannot contain a loop");
        assertFalse(codeRules.containsStream("getSessionKeysWithMultiplePresentersNoStream"),
                "cannot contain stream");
    }

    // ---------------------------------------------------------

    @Test
    void getSessionKeysWithMultiplePresentersStream() {
        List<String> expected = List.of("Security", "DDD", "CloudNativeSpringBoot");
        List<String> actual = target.getSessionKeysWithMultiplePresentersStream(workshops);
        assertEquals(expected, actual);
    }

    @Test
    void requirements_getSessionKeysWithMultiplePresentersStream() {
        assertTrue(codeRules.containsStream("getSessionKeysWithMultiplePresentersStream"),
                "must contains stream()");
        assertFalse(codeRules.containsIf("getSessionKeysWithMultiplePresentersStream"),
                "cannot contain if statement");
        assertFalse(codeRules.containsLoop("getSessionKeysWithMultiplePresentersStream"),
                "cannot contain a loop");
        assertFalse(codeRules.containsRemoveIf("getSessionKeysWithMultiplePresentersStream"),
                "cannot contain removeIf");
    }

    // ---------------------------------------------------------
    @Test
    void largestRoomNumberOnThirdFloor() {
        Optional<Integer> actual = target.largestRoomNumberOnThirdFloor(workshops);
        assertEquals(315, actual.get());
    }

    @Test
    void largestRoomNumberOnThirdFloor_forNoWorkshops() {
        Optional<Integer> actual = target.largestRoomNumberOnThirdFloor(Collections.emptyMap());
        assertFalse(actual.isPresent());
    }

    @Test
    void requirements_largestRoomNumberOnThirdFloor() {
        assertTrue(codeRules.containsStream("largestRoomNumberOnThirdFloor"),
                "must contains stream()");
        assertFalse(codeRules.containsIf("largestRoomNumberOnThirdFloor"),
                "cannot contain if statement");
        assertFalse(codeRules.containsLoop("largestRoomNumberOnThirdFloor"),
                "cannot contain a loop");
        assertFalse(codeRules.containsRemoveIf("largestRoomNumberOnThirdFloor"),
                "cannot contain removeIf");
    }

    // ---------------------------------------------------------

    @Test
    void titleOfRoomOneLowerThan() {
        Optional<String> actual = target.titleOfRoomOneLowerThan(workshops,304);
        assertEquals("Test Driven Development: From Principles to Practice", actual.get());
    }

    @Test
    void titleOfRoomOneLowerThan_lowest() {
        Optional<String> actual = target.titleOfRoomOneLowerThan(workshops,302);
        assertFalse(actual.isPresent());
    }

    @Test
    void requirements_titleOfRoomOneLowerThan() {
        assertTrue(codeRules.containsStream("titleOfRoomOneLowerThan"),
                "must contains stream()");
        assertFalse(codeRules.containsIf("titleOfRoomOneLowerThan"),
                "cannot contain if statement");
        assertFalse(codeRules.containsLoop("titleOfRoomOneLowerThan"),
                "cannot contain a loop");
        assertFalse(codeRules.containsRemoveIf("titleOfRoomOneLowerThan"),
                "cannot contain removeIf");
    }

    // ---------------------------------------------------------

    @Test
    void getTitlesAsCsvWithPrefix() {
        String expected = "Java Idioms for becoming a more powerful developer,Java Security Workshop";
        String actual = target.getTitlesAsCsvWithPrefix(workshops, "Java");
        assertEquals(expected, actual);
    }

    @Test
    void getTitlesAsCsvWithPrefix_noMatches() {
        String expected = "";
        String actual = target.getTitlesAsCsvWithPrefix(workshops, "X");
        assertEquals(expected, actual);
    }

    @Test
    void requirements_getTitlesAsCsvWithPrefix() {
        assertTrue(codeRules.containsStream("getTitlesAsCsvWithPrefix"),
                "must contains stream()");
        assertFalse(codeRules.containsIf("getTitlesAsCsvWithPrefix"),
                "cannot contain if statement");
        assertFalse(codeRules.containsLoop("getTitlesAsCsvWithPrefix"),
                "cannot contain a loop");
        assertFalse(codeRules.containsRemoveIf("getTitlesAsCsvWithPrefix"),
                "cannot contain removeIf");
    }

}
