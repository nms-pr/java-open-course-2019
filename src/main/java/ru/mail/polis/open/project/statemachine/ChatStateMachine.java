package ru.mail.polis.open.project.statemachine;

import ru.mail.polis.open.project.statemachine.states.ChatState;
import ru.mail.polis.open.project.statemachine.states.MainMenuChatState;
import ru.mail.polis.open.project.statemachine.states.NewsChatState;
import ru.mail.polis.open.project.statemachine.states.WeatherChatState;
import ru.mail.polis.open.project.utils.UserSearchStatisticsProvider;

import java.util.List;
import java.util.Objects;

/**
 * Class that represents State Machine for chat
 * Each chat has it's own state machine
 * It us created with the view to divide users from each other. Otherwise,
 * they will have conflicts doing their requests.
 * For instance, user #1 goes to Weather menu. At the same time user #2 goes to News menu.
 * User #1 asks fo a whether at city,
 * but gets a news overview
 * This is the implementation of State Machine pattern
 *
 * For now, state machine can be at following states:
 *  1. Main menu (MainMenuChatState) - where user can choose between bots features
 *  2. Weather menu (WeatherChatState) - where user can get info about weather at given city
 *  3. News menu (NewsChatState) - where user can get info about news at given city
 *
 * @see MainMenuChatState
 * @see WeatherChatState
 * @see NewsChatState
 */
public class ChatStateMachine {

    private ChatState state;

    /**
     * Keeps utils for given chat
     */
    private UserSearchStatisticsProvider statistics;

    public ChatStateMachine() {

        state = new MainMenuChatState(this);
        statistics = new UserSearchStatisticsProvider();
    }

    /**
     * Updates the state of machine
     * @param message - what to do
     * @param chatId - where the request was made
     * @param buttonsNames - out parameter: by the end of method execution
     *                    it will be filled with strings that should be printed on buttons
     * @return result of operation
     */
    public String update(String message, Long chatId, List<String> buttonsNames) {
        String result = state.update(message, chatId, buttonsNames);

        if (result == null) {
            result = state.getInitialData(buttonsNames);
        }

        return result;
    }

    public void setState(ChatState state) {
        this.state = state;
    }

    public ChatState getState() {
        return state;
    }

    public UserSearchStatisticsProvider getStatisticsProvider() {
        return statistics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChatStateMachine that = (ChatStateMachine) o;
        return Objects.equals(getState(), that.getState())
            && Objects.equals(statistics, that.statistics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getState(), statistics);
    }
}
