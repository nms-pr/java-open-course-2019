package ru.mail.polis.open.project.statemachine.states;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import ru.mail.polis.open.project.Bot;
import ru.mail.polis.open.project.statemachine.ChatStateMachine;
import ru.mail.polis.open.project.utils.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

/**
 * State that provides bot with ability to get info about news
 * @see ChatState
 * @see ChatStateMachine
 */
public class NewsChatState implements ChatState {

    private static final String URL_BEFORE_CITY_NAME = "https://news.rambler.ru/";
    private static final String RSS = "rss/";
    private static final byte BUTTONS_LIMIT = 5;

    private ChatStateMachine stateMachine;

    public NewsChatState(ChatStateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public String update(String message, long chatId, List<String> mostFrequentRequests) {
        if (message.equals(Bot.MENU_COMMAND)) {
            stateMachine.setState(new MainMenuChatState(stateMachine));

            return null;
        }

        try {
            URL url = new URL(
                URL_BEFORE_CITY_NAME
                + RSS
                + message
            );

            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(url));
            StringBuilder info = new StringBuilder();
            byte currentNews = 0;

            for (SyndEntry entry : feed.getEntries()) {
                info.append(
                        (entry.getTitle() != null
                        ? "  Topic #" + ++currentNews + " : " + entry.getTitle()
                        : "title : NULL")
                    )
                    .append("\n\n")
                    .append(
                        entry.getDescription() != null
                        ? entry.getDescription().getValue()
                        : "description : NULL"
                    )
                    .append("\n\n---------------------------------------------------------\n\n");

                if (currentNews == BUTTONS_LIMIT) {
                    break;
                }
            }

            info.append("Source : ")
                .append(URL_BEFORE_CITY_NAME)
                .append(message);

            stateMachine.getStatisticsProvider().onRequest("News", message);
            Logger.addInfoAboutRequest(message, chatId, "News");

            mostFrequentRequests.addAll(getMostFrequentCities());

            return info.toString();
        } catch (FeedException | IOException e) {
            return "Город не найден!";
        }
    }

    @Override
    public String getInitialData(List<String> mostFrequentRequest) {
        mostFrequentRequest.addAll(getMostFrequentCities());
        return "Новости\nВведите город на английском";
    }

    private List<String> getMostFrequentCities() {
        return stateMachine.getStatisticsProvider().getMostFrequent(
            4,
            "News"
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NewsChatState that = (NewsChatState) o;
        return Objects.equals(stateMachine, that.stateMachine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stateMachine);
    }
}
