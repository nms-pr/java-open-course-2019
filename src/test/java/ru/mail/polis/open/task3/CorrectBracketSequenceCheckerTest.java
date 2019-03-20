package ru.mail.polis.open.task3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CorrectBracketSequenceCheckerTest {


    @Test
    void checkSequence() {
        // Последовательность = null => ошибка
        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.checkSequence(null));
        // Пустая строка => правильная последовательность
        assertTrue(CorrectBracketSequenceChecker.checkSequence(""));
        // Правильная последовательность, взятая в скобки одного типа,
        // к которой слева или справа приписана правильная последовательность => правильная последовательность
        assertTrue(CorrectBracketSequenceChecker.checkSequence("(({})[])([]){}"));
        // Не хватает закрывающих => в массиве останутся элементы
        assertFalse(CorrectBracketSequenceChecker.checkSequence("{{[]}"));
        // Не хватает открывающих => попытка удалить элемент из пустого массива
        assertFalse(CorrectBracketSequenceChecker.checkSequence("{{[]}}]"));
        // Не та закрывающая скобка => скобки не парные
        assertFalse(CorrectBracketSequenceChecker.checkSequence("{{[)}}"));
        // Правильная последовательность из 100 скобок
        assertTrue(CorrectBracketSequenceChecker.checkSequence(
            "[[[[[[[(((({{{{{{{{{((([[[((([(((((((({([[[[[[(({()}))]]]]]])}"
            + "))))))))])))]]])))}}}}}}}}}))))]]]]]]]"));
        // 101 элемент => длина строки > 100
        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.checkSequence(
            "[[[[[[[(((({{{{{{{{{((([[[((([(((((((({([[[[[[(({()}))]]]]]])}"
            + "))))))))])))]]])))}}}}}}}}}))))]]]]]]])"));
        // 100 элементов, открывающих скобок 51 => попытка переполнить массив
        assertFalse(CorrectBracketSequenceChecker.checkSequence(
            "[[[[[[[(((({{{{{{{{{((([[[((([(((((((({([[[[[[(({([}))]]]]]])}"
            + "))))))))])))]]])))}}}}}}}}}))))]]]]]]]"));
        // Символ, не являющийся скобкой, до определения того, что последовательность некорректная => ошибка
        assertThrows(IllegalArgumentException.class, () -> CorrectBracketSequenceChecker.checkSequence("[(())*"));
        // Символ, не являющийся скобкой, после определения того, что последовательность некорректная => false
        assertFalse(CorrectBracketSequenceChecker.checkSequence("{{[)*}"));
    }

}