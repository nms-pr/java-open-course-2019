package ru.mail.polis.open.project;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.mail.polis.open.project.statemachine.ChatStateMachine;
import ru.mail.polis.open.project.statemachine.states.ChatState;
import ru.mail.polis.open.project.statemachine.states.NewsChatState;
import ru.mail.polis.open.project.statemachine.states.WeatherChatState;
import ru.mail.polis.open.project.utils.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class that manages bots activity
 */
public class Bot extends TelegramLongPollingBot {

    /**
     * Enumeration representing whether the buttons should be shown
     * at the bottom of the chat or attached to the message
     */
    enum ButtonsMode {
        CHAT,
        MESSAGE
    }

    public static final String WEATHER_COMMAND = "Weather";
    public static final String NEWS_COMMAND = "News";
    public static final String MENU_COMMAND = "/menu";
    private static final String START_COMMAND = "/start";
    private static final String RESET_COMMAND = "/reset";
    private static final String HELP_COMMAND = "/help";
    private static final Integer BUTTONS_IN_ROW = 2;

    private static Bot instance = null;
    private static ExecutorService executorService = Executors.newFixedThreadPool(8);

    /**
     * Each chat has it's own state machine describing it's state.
     * ChatId is the key
     * Instance of ChatStateMachine corresponds to each ChatId
     */
    private final Map<Long, ChatStateMachine> chatStateMachineSet;


    protected Bot(DefaultBotOptions botOptions) {
        super(botOptions);

        chatStateMachineSet = new HashMap<>();
    }

    public static synchronized Bot createInstance(DefaultBotOptions botOptions) {
        if (instance != null) {
            throw new IllegalArgumentException("Bot is already created");
        }

        instance = new Bot(botOptions);
        return instance;
    }

    public static synchronized Bot getInstance() {
        return instance;
    }

    private void sendMsg(Message message, String text, boolean replyRequired) {
        sendMsg(
            message,
            text,
            replyRequired,
            ButtonsMode.CHAT,
            List.of(
                START_COMMAND,
                HELP_COMMAND,
                RESET_COMMAND,
                MENU_COMMAND
            )
        );
    }

    /**
     * Sends message to person
     *
     * @param message - message to reply on
     * @param text - text to send
     * @param replyRequired - specifies whether reply is needed or bot simply have to send message
     * @param buttonsMode - where to attach buttons
     * @param buttonsNames - list of strings that should be put on buttons
     *
     *  @see ButtonsMode
     */
    private void sendMsg(
        Message message,
        String text,
        boolean replyRequired,
        ButtonsMode buttonsMode,
        List<String> buttonsNames
    ) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);

        sendMessage.setChatId(message.getChatId().toString());

        if (replyRequired) {
            sendMessage.setReplyToMessageId(message.getMessageId());
        }
        sendMessage.setText(text);
        try {
            switch (buttonsMode) {
                case CHAT: {
                    setChatButtons(sendMessage, buttonsNames);
                    break;
                } case MESSAGE: {
                    setMessageButtons(sendMessage, buttonsNames);
                    break;
                } default : {
                    throw new IllegalArgumentException("Something wrong");
                }
            }

            execute(sendMessage);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    /**
     * Entrance point when Bot gets an update (i.e. user sends a message)
     *
     * @param update - update to process
     */
    public void onUpdateReceived(Update update) {

        executorService.submit(() -> {
                if (update.hasMessage() && update.getMessage().hasText()) {
                    Message message = update.getMessage();

                    if (message == null) {
                        return;
                    }

                    if (!chatStateMachineSet.containsKey(message.getChatId())) {
                        chatStateMachineSet.put(
                            message.getChatId(),
                            new ChatStateMachine()
                        );
                    }

                    if (message.hasText()) {
                        switch (message.getText()) {
                            case START_COMMAND : {
                                sendMsg(
                                    message,
                                    "Привет! Я бот Чижик, буду летать за нужной тебе информацией! \n"
                                        + "Выбирай, что тебе хочется узнать, а я пока приготовлюсь  к полёту.",
                                    false
                                );
                                break;
                            } case HELP_COMMAND : {
                                sendMsg(
                                    message,
                                    "Чтобы я мог помочь тебе узнать нужную информацию - введи " + START_COMMAND + "\n"
                                        + "Чтобы вернуться в главное меню используй команду " + MENU_COMMAND,
                                    true
                                );
                                break;
                            } case RESET_COMMAND: {
                                sendMsg(
                                    message,
                                    "Твои запросы сброшены.",
                                    true
                                );
                                chatStateMachineSet
                                    .get(message.getChatId())
                                    .getStatisticsProvider()
                                    .clear();
                                Logger.clearLog(message.getChatId());
                                break;
                            } default: {
                                List<String> buttonsName = new ArrayList<>();
                                String result = chatStateMachineSet
                                    .get(message.getChatId())
                                    .update(message.getText(), message.getChatId(), buttonsName);

                                sendMsg(message, result, true, ButtonsMode.MESSAGE, buttonsName);
                            }
                        }
                    }
                } else if (update.hasCallbackQuery()) {

                    Message callbackMessage = update.getCallbackQuery().getMessage();
                    ChatStateMachine stateMachine = chatStateMachineSet.get(callbackMessage.getChatId());

                    switch (update.getCallbackQuery().getData()) {
                        case WEATHER_COMMAND: {
                            ChatState state = new WeatherChatState(stateMachine);
                            requestProcessing(stateMachine, callbackMessage, state);
                            break;
                        } case NEWS_COMMAND: {
                            ChatState state = new NewsChatState(stateMachine);
                            requestProcessing(stateMachine, callbackMessage, state);
                            break;
                        } default: {
                            List<String> buttonsName = new ArrayList<>();
                            String result = stateMachine.update(
                                update.getCallbackQuery().getData(),
                                callbackMessage.getChatId(),
                                buttonsName
                            );
                            sendMsg(callbackMessage, result, false, ButtonsMode.MESSAGE, buttonsName);
                        }
                    }
                }
            }
        );
    }

    /**
     * Direct intervention to StateMachine in order to change it's state by force
     * @param stateMachine - machine to change state in
     * @param callbackMessage - message to reply on
     * @param state - new state of machine
     */
    private void requestProcessing(ChatStateMachine stateMachine, Message callbackMessage, ChatState state) {
        stateMachine.setState(state);

        List<String> buttonsName = new ArrayList<>();
        String result = state.getInitialData(buttonsName);
        sendMsg(callbackMessage, result, false, ButtonsMode.MESSAGE, buttonsName);
    }

    /**
     * Attaches buttons to message
     * @param sendMessage - message to attach buttons to
     * @param buttonsNames - list of strings that should be displayed on buttons
     */
    private void setMessageButtons(SendMessage sendMessage, List<String> buttonsNames) {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        List<List<InlineKeyboardButton>> inlineButtons = new ArrayList<>();

        for (String buttonsName : buttonsNames) {
            inlineButtons.add(List.of(
                new InlineKeyboardButton(buttonsName).setCallbackData(buttonsName))
            );
        }

        inlineKeyboardMarkup.setKeyboard(inlineButtons);
    }

    /**
     * Attaches buttons to whole chat
     * @param sendMessage - message with that the buttons will be attached
     * @param buttonsNames - list of strings that should be displayed on buttons
     */
    private void setChatButtons(SendMessage sendMessage, List<String> buttonsNames) {

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();

        int itemsInRow = 0;
        KeyboardRow row = new KeyboardRow(); ;
        for (String buttonName : buttonsNames)  {
            if (itemsInRow == BUTTONS_IN_ROW) {
                keyboardRowList.add(row);
                row = new KeyboardRow();
                itemsInRow = 0;
            }
            row.add(buttonName);

            itemsInRow++;
        }

        keyboardRowList.add(row);

        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    public String getBotUsername() {
        return "Chizhik";
    }

    public String getBotToken() {
        return "857689855:AAGhd--tUolV7XhPMrm0qsJO7gMjiaArhls";
    }
}
