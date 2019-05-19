package ru.mail.polis.open.project.statemachine.states;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.mail.polis.open.project.statemachine.ChatStateMachine;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MainMenuChatStateTest {

    private static ChatStateMachine chatStateMachine;
    private static MainMenuChatState mainMenuChatState;

    @BeforeAll
    static void createInstance() {
        chatStateMachine = new ChatStateMachine();
        mainMenuChatState = new MainMenuChatState(chatStateMachine);
    }

    @Test
    void testWorkingUpdate() {

        assertEquals(
             "Ты уже в главном меню!",
            mainMenuChatState.update(
                "/menu",
                4455L,
                new ArrayList<>()
            )
        );
        assertEquals(
            new MainMenuChatState(chatStateMachine),
            chatStateMachine.getState()
        );
        assertThrows(
            NullPointerException.class,
            () -> chatStateMachine.update(
                null,
                564356L,
                new ArrayList<>())
        );
        assertNull(
            mainMenuChatState.update(
            "Weather",
            34534L,
            new ArrayList<>()
            )
        );

        assertEquals(
            new WeatherChatState(chatStateMachine),
            chatStateMachine.getState()
        );

        assertNull(
            mainMenuChatState.update(
                "News",
                34534L,
                new ArrayList<>()
            )
        );

        assertEquals(
            new NewsChatState(chatStateMachine),
            chatStateMachine.getState()
        );
    }

    @Test
    void testWorkingGetInitialData() {
        assertEquals(
            "Ты в главном меню!",
            mainMenuChatState.getInitialData(new ArrayList<>())
        );
    }
}
