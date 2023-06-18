package com.jeanneboyarsky.rules;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CodeRulesForMethodsTest {

    private CodeRulesForMethods target;

    @BeforeEach
    void setUp() {
        Path folder = Paths.get("lab/src/test/java/");
        target = new CodeRulesForMethods(folder, "com.jeanneboyarsky.rules",
                "CodeRulesForMethodsTest.java");
    }

    @Test
    void methodNotFound() {
        assertThrows(IllegalStateException.class, () -> target.countLoops("unknownMethod"));
    }

    @Test
    void numberNonCommentedOutLines_withComments() {
        assertEquals(0, target.numberNonCommentedOutLines("commentsOnly"), "# lines");
    }

    @Test
    void numberNonCommentedOutLines() {
        assertEquals(1, target.numberNonCommentedOutLines("lambda"), "# lines");
    }

    @Test
    void containsLambda() {
        assertTrue(target.containsLambda("lambda"), "must contain lambda");
        assertFalse(target.containsLambda("methodReference"), "cannot contain lambda");
    }

    @Test
    void containsMethodReference() {
        assertTrue(target.containsMethodReference("methodReference"), "must contain method reference");
        assertFalse(target.containsMethodReference("lambda"), "cannot contain method reference");
    }

    @Test
    void containsStream() {
        assertTrue(target.containsStream("stream"), "must contain stream");
        assertFalse(target.containsStream("lambda"), "cannot contain stream");
    }

    @Test
    void containsNewLine() {
        assertTrue(target.containsNewLine("newLine"), "must contain newLine");
        assertFalse(target.containsNewLine("lambda"), "cannot contain newLine");
    }

    @Test
    void containsLoop() {
        assertTrue(target.containsLoop("forLoop"), "must contain loop");
        assertTrue(target.containsLoop("whileLoop"), "must contain loop");
        assertFalse(target.containsLoop("print"), "cannot contain loop");
        assertFalse(target.containsLoop("format"), "can contain loop");
    }

    @Test
    void containsIf() {
        assertTrue(target.containsIf("ifStatement"), "must contain if statement");
        assertFalse(target.containsIf("forLoop"), "cannot contain if");
        assertFalse(target.containsIf("commentsOnly"), "cannot contain if");
    }

    @Test
    void countLoops() {
        assertEquals(1, target.countLoops("forLoop"), "# loops");
        assertEquals(1, target.countLoops("whileLoop"), "# loops");
        assertEquals(0, target.countLoops("print"), "# loops");
        assertEquals(0, target.countLoops("format"), "# loops");
    }

    void countNewLine() {
        assertEquals(0, target.countNewLineCharacters("forLoop"), "# \n");
        assertEquals(1, target.countNewLineCharacters("oneNewLine"), "# \n");
        assertEquals(2, target.countNewLineCharacters("twoNewLines"), "# \n");
    }

    @Test
    void containsOptionalEmpty() {
        assertTrue(target.containsOptionalEmpty("optionalEmpty"), "must contain Optional.empty");
        assertFalse(target.containsOptionalEmpty("optionalOf"), "cannot contain Optional.of");
    }

    @Test
    void containsOptionalOf() {
        assertTrue(target.containsOptionalOf("optionalOf"), "must contain Optional.of");
        assertFalse(target.containsOptionalOf("optionalEmpty"), "cannot contain Optional.empty");
    }

    @Test
    void containsRemoveIf() {
        assertTrue(target.containsRemoveIf("removeIf"), "must contain removeIf");
        assertFalse(target.containsRemoveIf("optionalEmpty"), "cannot contain removeIf");
    }

    // sample methods
    // START commentsOnly()
    void commentsOnly() {
        // comment that mentions an if statement
    }
    // END commentsOnly()

    // START lambda()
    void lambda(List<String> list) {
        list.removeIf(a -> true);
    }
    // END lambda()

    // START methodReference()
    void methodReference(List<String> list) {
        list.removeIf(String::isEmpty);
    }
    // END methodReference()

    // START print()
    void print(List<String> list) {
        System.out.print(list);
    }
    // END print()

    // START newLine()
    void newLine() {
        String newLine = "\n";
    }
    // END newLine()

    // START println()
    void println(List<String> list) {
        System.out.println(list);
    }
    // END println()

    // START printStream()
    void printStream(PrintStream printStream) {
        printStream.format("");
    }
    // END printStream()

    // START stream()
    void stream(List<String> list) {
        list.stream();
    }
    // END stream()

    // START format()
    void format() {
        System.console().format("label");
    }
    // END format()

    // START removeIf()
    void removeIf(List<String> list) {
        list.removeIf(p -> true);
    }
    // END removeIf()

    // START forLoop()
    void forLoop(List<String> list) {
        for(String s : list) {

        }
    }
    // END forLoop()

    // START ifStatement()
    void ifStatement() {
        if (true) {

        }
    }
    // END ifStatement()

    // START whileLoop()
    void whileLoop() {
        while(true) {

        }
    }
    // END whileLoop()

    // START optionalEmpty()
    void optionalEmpty() {
        Optional.empty();
    }
    // END optionalEmpty()

    // START optionalOf()
    void optionalOf() {
        Optional.of(1);
    }
    // END optionalOf()

    // START oneNewLine()
    String oneNewLine() {
        return "\n";
    }
    // END oneNewLine()

    // START twoNewLines()
    String twoNewLines() {
        return "\n \n";
    }
    // END twoNewLines()
}