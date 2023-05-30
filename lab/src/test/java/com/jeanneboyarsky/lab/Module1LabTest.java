package com.jeanneboyarsky.lab;

import com.jeanneboyarsky.rules.CodeRulesForMethods;
import com.jeanneboyarsky.solutions.Module1LabSolution;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Module1LabTest {

    private Module1Lab target;
    private CodeRulesForMethods codeRules;

    @BeforeEach
    void setUp() {
        target = new Module1Lab();

        Path folder = Paths.get("lab/src/test/java/");
        codeRules = new CodeRulesForMethods(folder, "com.jeanneboyarsky.rules",
                "CodeRulesForMethodsTest.java");
    }

    // ---------------------------------------------------------

    @Test
    void pipeSeparatedValuesOfAllNoneWhitespaceStrings() {
        String expected = "sea lion|penguin";
        String actual = target.pipeSeparatedValuesOfAllNoneWhitespaceStrings("sea lion", "  ", "penguin", "", "");
        assertEquals(expected, actual);
    }

    @Test
    void pipeSeparatedValuesOfAllNoneWhitespaceStrings_noMatches() {
        String expected = "";
        String actual = target.pipeSeparatedValuesOfAllNoneWhitespaceStrings(" ", "", "");
        assertEquals(expected, actual);
    }

    @Test
    void requirements_pipeSeparatedValuesOfAllNoneWhitespaceStrings() {
        assertFalse(codeRules.containsStream("pipeSeparatedValuesOfAllNoneWhitespaceStrings"),
                "cannot contain stream");
        assertFalse(codeRules.containsLambda("pipeSeparatedValuesOfAllNoneWhitespaceStrings"),
                "cannot contain lambda");
        assertFalse(codeRules.containsMethodReference("pipeSeparatedValuesOfAllNoneWhitespaceStrings"),
                "cannot contain method reference");
    }

    // ---------------------------------------------------------

    @Test
    void threeCopiesOfFirstStringContainingNexus() {
        List<String> list = List.of("Braves", "DevNexus", "Nexus");
        String expected = "DevNexusDevNexusDevNexus";
        String actual = target.threeCopiesOfFirstStringContainingNexus(list);
        assertEquals(expected, actual);
    }

    @Test
    void threeCopiesOfFirstStringContainingNexus_noMatches() {
        List<String> list = List.of("braves", "nexus");
        String expected = "";
        String actual = target.threeCopiesOfFirstStringContainingNexus(list);
        assertEquals(expected, actual);
    }

    @Test
    void requirements_threeCopiesOfFirstStringContainingNexus() {
        assertFalse(codeRules.containsStream("threeCopiesOfFirstStringContainingNexus"),
                "cannot contain stream");
        assertFalse(codeRules.containsLambda("threeCopiesOfFirstStringContainingNexus"),
                "cannot contain lambda");
        assertFalse(codeRules.containsMethodReference("threeCopiesOfFirstStringContainingNexus"),
                "cannot contain method reference");
    }

    // ---------------------------------------------------------

    @Test
    void allCaseInsensitiveMatchesOfTree() {
        List<String> list = List.of("Tree", "TreeHouse", "tree");
        List<String> expected = List.of("Tree", "tree");
        List<String> actual = target.allCaseInsensitiveMatchesOfTree(list);
        assertEquals(expected, actual);
    }

    @Test
    void requirements_allCaseInsensitiveMatchesOfTree() {
        assertFalse(codeRules.containsStream("allCaseInsensitiveMatchesOfTree"),
                "cannot contain stream");
        assertFalse(codeRules.containsLambda("allCaseInsensitiveMatchesOfTree"),
                "cannot contain lambda");
        assertFalse(codeRules.containsMethodReference("allCaseInsensitiveMatchesOfTree"),
                "cannot contain method reference");
    }

    // ---------------------------------------------------------

    @Test
    void formatting() {
        String expected = "e=2.718";
        var actual = target.formatConstant("e", 2.7182818284);
        assertEquals(expected, actual);
    }

    @Test
    void requirements_formatConstant() {
        assertFalse(codeRules.containsLoop("formatConstant"),
                "cannot contain loop");
        assertFalse(codeRules.containsNewLine("formatConstant"),
                "cannot contain new line");
    }

    // ---------------------------------------------------------

    @Test
    void createTicTacToeBoardWithTextBlock() {
        // not using a text block here so can't just copy it
        String expected = "--------------\n"
                + "|   | X | O |\n"
                + "--------------\n"
                + "| O | X | O |\n"
                + "--------------\n"
                + "|   | X |   |\n"
                + "--------------";
        var values = new char[3][3];
        values[0] = new char[]{' ', 'X', 'O'};
        values[1] = new char[]{'O', 'X', 'O'};
        values[2] = new char[]{' ', 'X', ' '};
        var actual = target.createTicTacToeBoardWithTextBlock(values);
        assertEquals(expected, actual);
    }

    void requirements_createTicTacToeBoardWithTextBlock() {
        assertEquals(0, codeRules.countLoops("createTicTacToeBoardWithTextBlock"),
                "cannot contain loop");
        assertFalse(codeRules.containsNewLine("createTicTacToeBoardWithTextBlock"),
                "cannot contain new line");
    }

    @Test
    void createBlankTicTacToeBoardWithoutTextBlock() {
        String expected = """
                ----------
                |  |  |  |
                ----------
                |  |  |  |
                ----------
                |  |  |  |
                ----------""";
        var actual = target.createBlankTicTacToeBoardWithoutTextBlock();
        assertEquals(expected, actual);
    }

    @Test
    void requirements_createBlankTicTacToeBoardWithoutTextBlock() {
        assertEquals(0, codeRules.countLoops("createBlankTicTacToeBoardWithoutTextBlock"),
                "no loops allowed");
        assertEquals(1, codeRules.countNewLineCharacters("createBlankTicTacToeBoardWithoutTextBlock"),
                "must contain exactly one new line");
    }
}