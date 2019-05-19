package ru.mail.polis.open.project;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

/**
 * Class that creates bot with proxy
 */
public class BotCreator {

    private static final String PROXY_HOST = "51.38.123.195";
    private static final int PROXY_PORT = 1080;

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
            botOptions.setProxyHost(PROXY_HOST);
            botOptions.setProxyPort(PROXY_PORT);
            botOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);

            telegramBotsApi.registerBot(Bot.createInstance(botOptions));

        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
