package ru.mail.polis.open.project.statemachine.states;

import ru.mail.polis.open.project.Bot;
import ru.mail.polis.open.project.statemachine.ChatStateMachine;

import java.util.List;
import java.util.Objects;

/**
 * State that provides bot with ability to move between the menus
 * @see ChatState
 * @see ChatStateMachine
 */
public class MainMenuChatState implements ChatState {

    private final ChatStateMachine stateMachine;

    public MainMenuChatState(ChatStateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public String update(String message, long chatId, List<String> mostFrequentRequests) {
        switch (message) {
            case Bot.MENU_COMMAND : {
                mostFrequentRequests.addAll(getButtonsNames());
                return "Ты уже в главном меню!";
            } case Bot.WEATHER_COMMAND: {
                stateMachine.setState(new WeatherChatState(stateMachine));
                return null;
            } case Bot.NEWS_COMMAND: {
                stateMachine.setState(new NewsChatState(stateMachine));
                return null;
            } default : {
                return "У меня нет такой функции (";
            }
        }
    }

    @Override
    public String getInitialData(List<String> mostFrequentRequest) {
        mostFrequentRequest.addAll(getButtonsNames());
        return "Ты в главном меню!";
    }

    private List<String> getButtonsNames() {
        return List.of(Bot.WEATHER_COMMAND, Bot.NEWS_COMMAND);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MainMenuChatState that = (MainMenuChatState) o;
        return Objects.equals(stateMachine, that.stateMachine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stateMachine);
    }
}
