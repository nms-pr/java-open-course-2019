package ru.mail.polis.open.project.statemachine.states;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.mail.polis.open.project.statemachine.ChatStateMachine;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WeatherChatStateTest {

    private static WeatherChatState weatherChatState;
    private static ChatStateMachine chatStateMachine;

    @BeforeAll
    static void createInstance() {
        chatStateMachine = new ChatStateMachine();
        weatherChatState = new WeatherChatState(chatStateMachine);
    }

    @Test
    void testWorkingUpdate() throws IOException {

        assertEquals(
            weatherChatState.update(
                "gshshsth",
                345345L,
                new ArrayList<>()
            ),
            "Город не найден!"
        );

        assertThrows(
            NullPointerException.class,
            () -> weatherChatState.update(
                null,
                564356L,
                new ArrayList<>())
        );

        assertNull(weatherChatState.update("/menu", 453453L, new ArrayList<>()));
        assertEquals(
            new MainMenuChatState(chatStateMachine),
            chatStateMachine.getState()
        );

        URL url = new URL(
            "http://api.openweathermap.org/dat"
            + "a/2.5/weather?q=Luga&units=metric&appid=6fff53a641b9b9a799cfd6b079f5cd4e"
        );

        Scanner in = new Scanner((InputStream) url.getContent());
        StringBuilder result = new StringBuilder();
        while (in.hasNext()) {
            result.append(in.nextLine());
        }

        JSONObject object = new JSONObject(result.toString());
        JSONObject main = object.getJSONObject("main");
        JSONArray getArray = object.getJSONArray("weather");

        String resultMsg = "В городе: " + object.getString("name") + "\n"
            + "Температура: " + main.getDouble("temp") + "C" + "\n"
            + "Влажность: " + main.getDouble("humidity") + "%" + "\n"
            + "Осадки: " + getArray.getJSONObject(0).get("main") + "\n"
            + "http://openweathermap.org/img/w/" + getArray.getJSONObject(0).get("icon") + ".png";

        assertEquals(
            resultMsg,
            weatherChatState.update(
                "Luga",
                5356L,
                new ArrayList<>()
            )
        );
    }

    @Test
    void testWorkingGetInitialData() {
        assertEquals(
            weatherChatState.getInitialData(new ArrayList<>()),
            "Погода\nВведите город на английском"
        );
    }
}
