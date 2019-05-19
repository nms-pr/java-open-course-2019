package ru.mail.polis.open.project.statemachine.states;

import java.util.List;

public interface ChatState {

    /**
     * Updates the state of state machine, process commands
     * @param message - command to process
     * @param chatId - where request was made
     * @param mostFrequentRequests - out parameter: by the end of method execution
     *      *                    it will be filled with strings that should be printed on buttons
     * @return - info about given request or null if machine was required to change it's state
     */
    String update(String message, long chatId, List<String> mostFrequentRequests);

    /**
     * Returns the title of the menu and list of strings that should be printed at buttons
     * @param mostFrequentRequest - out parameter: by the end of method execution
     *      *                    it will be filled with strings that should be printed on buttons
     * @return menu title
     */
    String getInitialData(List<String> mostFrequentRequest);
}
