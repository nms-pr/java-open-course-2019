package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FormBuilderTest {

    @Test
    void generateIntermediateTokenForm_generatesCorrectly() {
        List<String> expected = new ArrayList<>();
        expected.add("5");
        expected.add("+");
        expected.add("3");

        assertEquals(expected, FormBuilder.generateIntermediateTokenForm("5+3"));
        assertEquals(expected, FormBuilder.generateIntermediateTokenForm("5 +3"));
        assertEquals(expected, FormBuilder.generateIntermediateTokenForm("5+ 3"));
        assertEquals(expected, FormBuilder.generateIntermediateTokenForm("5 + 3"));

        expected.clear();
        expected.add("m");
        expected.add("2");

        assertEquals(expected, FormBuilder.generateIntermediateTokenForm("-2"));

        expected.clear();
        expected.add("m");
        expected.add("26");

        assertEquals(expected, FormBuilder.generateIntermediateTokenForm("-26"));
    }

    @Test
    void generateIntermediateTokenForm_throwsOnInvalidCharacter() {
        assertThrows(IllegalArgumentException.class, () -> FormBuilder.generateIntermediateTokenForm("4a2"));
    }


    @Test
    void generatePostfixTokenForm() {
        List<String> expected = new ArrayList<>();
        expected.add("5");
        expected.add("3");
        expected.add("+");

        List<String> intermediateForm = FormBuilder.generateIntermediateTokenForm("5+3");

        assertEquals(expected, FormBuilder.generatePostfixTokenForm(intermediateForm));

        expected.clear();
        expected.add("2");
        expected.add("m");

        intermediateForm = FormBuilder.generateIntermediateTokenForm("-2");

        assertEquals(expected, FormBuilder.generatePostfixTokenForm(intermediateForm));

        expected.clear();
        expected.add("4");
        expected.add("2");
        expected.add("3");
        expected.add("+");
        expected.add("*");

        intermediateForm = FormBuilder.generateIntermediateTokenForm("4*(2+3)");

        assertEquals(expected, FormBuilder.generatePostfixTokenForm(intermediateForm));
    }




    @Test
    void checkValidity_doesNotThrowOnValidInput() {
        assertDoesNotThrow(() -> FormBuilder.checkValidity(FormBuilder.generateIntermediateTokenForm("1+2")));
        assertDoesNotThrow(() -> FormBuilder.checkValidity(FormBuilder.generateIntermediateTokenForm("42 + 23")));
        assertDoesNotThrow(() -> FormBuilder.checkValidity(FormBuilder.generateIntermediateTokenForm("(1+3)")));
        assertDoesNotThrow(() -> FormBuilder.checkValidity(FormBuilder.generateIntermediateTokenForm("3*(5+8)")));
        assertDoesNotThrow(() -> FormBuilder.checkValidity(FormBuilder.generateIntermediateTokenForm("-2")));
        assertDoesNotThrow(() -> FormBuilder.checkValidity(FormBuilder.generateIntermediateTokenForm("--2")));
        assertDoesNotThrow(() -> FormBuilder.checkValidity(FormBuilder.generateIntermediateTokenForm("22")));
    }

    @Test
    void checkValidity_ThrowsOnInvalidInput() {
        assertThrows(IllegalArgumentException.class,
            () -> FormBuilder.checkValidity(FormBuilder.generateIntermediateTokenForm("")));
        assertThrows(IllegalArgumentException.class,
            () -> FormBuilder.checkValidity(FormBuilder.generateIntermediateTokenForm("()")));
        assertThrows(IllegalArgumentException.class,
            () -> FormBuilder.checkValidity(FormBuilder.generateIntermediateTokenForm("-2-")));
        assertThrows(IllegalArgumentException.class,
            () -> FormBuilder.checkValidity(FormBuilder.generateIntermediateTokenForm("(1+")));
        assertThrows(IllegalArgumentException.class,
            () -> FormBuilder.checkValidity(FormBuilder.generateIntermediateTokenForm("2-)")));
        assertThrows(IllegalArgumentException.class,
            () -> FormBuilder.checkValidity(FormBuilder.generateIntermediateTokenForm("2+")));
        assertThrows(IllegalArgumentException.class,
            () -> FormBuilder.checkValidity(FormBuilder.generateIntermediateTokenForm("-22+")));
        assertThrows(IllegalArgumentException.class,
            () -> FormBuilder.checkValidity(FormBuilder.generateIntermediateTokenForm("2//2 2")));
        assertThrows(IllegalArgumentException.class,
            () -> FormBuilder.checkValidity(FormBuilder.generateIntermediateTokenForm("(/2)")));
    }


    @Test
    void hasBiggerPriority() {
    }

    @Test
    void getPriority() {
    }

    @Test
    void isBeginningOfOperand() {
    }

    @Test
    void isOperation() {
    }
}