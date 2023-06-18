package com.jeanneboyarsky.rules;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Used to use Java Parser library. Switched to methods with comments around them since J
 * ava Parser doesn't support the latest syntax
 *
 * <a href="https://javaparser.org">...</a>
 *
 * <code>
 *     // START newLine()
 *     void newLine() {
 *         String newLine = "\n";
 *     }
 *     // END newLine()
 * </code>
 * </code>
 */
public class CodeRulesForMethods {

    private final String javaClassText;

    public CodeRulesForMethods(Path folder, String packageName, String fileName) {
        var javaFile = folder.resolve(packageName.replace('.', '/'))
                .resolve(fileName);
        try {
            javaClassText = Files.readString(javaFile);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public long numberNonCommentedOutLines(String methodName) {
        String methodDeclaration = getMethodDeclarationWithoutSingleLineComments(methodName);
        long numLines = Arrays.stream(methodDeclaration.split("\n"))
                .map(String::strip)
                .filter(s -> ! s.isEmpty())
                .peek(System.out::println)
                .count();
        // don't count first or last line (method signature and close }
        return numLines - 2;

    }

    public boolean containsLambda(String methodName) {
        String methodDeclaration = getMethodDeclarationWithoutSingleLineComments(methodName);
        return methodDeclaration.contains("->");
    }

    public boolean containsMethodReference(String methodName) {
        String methodDeclaration = getMethodDeclarationWithoutSingleLineComments(methodName);
        return methodDeclaration.contains("::");
    }

    public boolean containsStream(String methodName) {
        return containsMethod(methodName, "stream", "line");
    }

    private boolean containsMethod(String methodName, String... targetMethods) {
        String methodDeclaration = getMethodDeclarationWithoutSingleLineComments(methodName);
        return Arrays.stream(targetMethods).anyMatch(
                m -> methodDeclaration.contains(m + "(")
                  || methodDeclaration.contains("m ("));
    }

    public boolean containsNewLine(String methodName) {
        String methodText = getMethodDeclarationWithoutSingleLineComments(methodName);
        return methodText.contains("\\n");
    }

    public boolean containsLoop(String methodName) {
        String methodDeclaration = getMethodDeclarationWithoutSingleLineComments(methodName);
        return methodDeclaration.contains("for ")
                || methodDeclaration.contains("for(")
                || methodDeclaration.contains("while ")
                || methodDeclaration.contains("while(");
    }

    public boolean containsIf(String methodName) {
        String methodDeclaration = getMethodDeclarationWithoutSingleLineComments(methodName);
        return methodDeclaration.contains("if ")
                || methodDeclaration.contains("if(");
    }

    public int countLoops(String methodName) {
        String methodDeclaration = getMethodDeclarationWithoutSingleLineComments(methodName);
        Pattern pattern = Pattern.compile("(for|while)[ (]");
        Matcher matcher = pattern.matcher(methodDeclaration);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    public int countNewLineCharacters(String methodName) {
        return methodName.split("\n").length;
    }

    public boolean containsOptionalEmpty(String methodName) {
        return containsMethod(methodName, "Optional.empty");
    }

    public boolean containsOptionalOf(String methodName) {
        return containsMethod(methodName, "Optional.of");
    }

    public boolean containsRemoveIf(String methodName) {
        return containsMethod(methodName, "removeIf");
    }

    private String getMethodDeclarationWithoutSingleLineComments(String methodName) {
        var startComment = "// START " + methodName + "()";
        var endComment = "// END " + methodName + "()";;
        var anyText = ".*?";
        var regex = "(?s)" + startComment + anyText + endComment;
        var pattern = Pattern.compile(regex);
        var matcher = pattern.matcher(javaClassText);
        if (matcher.find()) {
            var method = matcher.group();
            return removeSingleLineComments(method);
        }
        throw new IllegalStateException(methodName + " not found");
    }

    private String removeSingleLineComments(String text) {
        var startComment = "//";
        var anyCharacters = ".*";
        var endOfLine = "$";
        var regex = "(?m)" + startComment + anyCharacters + endOfLine;
        return text.replaceAll(regex, "");
    }

}