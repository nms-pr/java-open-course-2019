package ru.mail.polis.open.project.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserSearchStatisticsProviderTest {

    private static UserSearchStatisticsProvider statisticsProvider;

    @BeforeAll
    static void createInstance() {
        statisticsProvider = new UserSearchStatisticsProvider();
    }

    @Test
    void testWorkingOnWeatherSearch() {
        statisticsProvider.onRequest("Weather", "Moscow");
        assertEquals(statisticsProvider.getRequestCounts("Weather").size(), 1);
        statisticsProvider.onRequest("Weather", "Luga");
        assertEquals(statisticsProvider.getRequestCounts("Weather").size(), 2);
        statisticsProvider.onRequest("Weather", "Surgut");
        assertEquals(statisticsProvider.getRequestCounts("Weather").size(), 3);
        statisticsProvider.onRequest("Weather", "Simpheropol");
        assertEquals(statisticsProvider.getRequestCounts("Weather").size(), 4);
        statisticsProvider.onRequest("Weather", "Moscow");
        assertEquals(statisticsProvider.getRequestCounts("Weather").size(), 4);
        statisticsProvider.onRequest("Weather", "Moscow");
        assertEquals(statisticsProvider.getRequestCounts("Weather").size(), 4);
        statisticsProvider.onRequest("Weather", "Bryansk");
        assertEquals(statisticsProvider.getRequestCounts("Weather").size(), 5);
        statisticsProvider.onRequest("Weather", "Pskov");
        assertEquals(statisticsProvider.getRequestCounts("Weather").size(), 6);
        statisticsProvider.onRequest("Weather", "Moscow");
        assertEquals(statisticsProvider.getRequestCounts("Weather").size(), 6);
        statisticsProvider.onRequest("Weather", "Luga");
        assertEquals(statisticsProvider.getRequestCounts("Weather").size(), 6);

        assertEquals(statisticsProvider.getRequestCounts("Weather").get("Moscow"), 4);
        assertEquals(statisticsProvider.getRequestCounts("Weather").get("Luga"), 2);
        assertEquals(statisticsProvider.getRequestCounts("Weather").get("Surgut"), 1);
        assertEquals(statisticsProvider.getRequestCounts("Weather").get("Simpheropol"), 1);
        assertEquals(statisticsProvider.getRequestCounts("Weather").get("Bryansk"), 1);
        assertEquals(statisticsProvider.getRequestCounts("Weather").get("Pskov"), 1);
        assertEquals(statisticsProvider.getRequestCounts("Weather").size(), 6);
    }

    @Test
    void testWorkingOnNewsSearch() {
        statisticsProvider.onRequest("News", "Moscow");
        assertEquals(statisticsProvider.getRequestCounts("News").size(), 1);
        statisticsProvider.onRequest("News", "Luga");
        assertEquals(statisticsProvider.getRequestCounts("News").size(), 2);
        statisticsProvider.onRequest("News", "Surgut");
        assertEquals(statisticsProvider.getRequestCounts("News").size(), 3);
        statisticsProvider.onRequest("News", "Simpheropol");
        assertEquals(statisticsProvider.getRequestCounts("News").size(), 4);
        statisticsProvider.onRequest("News", "Moscow");
        assertEquals(statisticsProvider.getRequestCounts("News").size(), 4);
        statisticsProvider.onRequest("News", "Moscow");
        assertEquals(statisticsProvider.getRequestCounts("News").size(), 4);
        statisticsProvider.onRequest("News", "Bryansk");
        assertEquals(statisticsProvider.getRequestCounts("News").size(), 5);
        statisticsProvider.onRequest("News", "Pskov");
        assertEquals(statisticsProvider.getRequestCounts("News").size(), 6);
        statisticsProvider.onRequest("News", "Moscow");
        assertEquals(statisticsProvider.getRequestCounts("News").size(), 6);
        statisticsProvider.onRequest("News", "Luga");
        assertEquals(statisticsProvider.getRequestCounts("News").size(), 6);

        assertEquals(statisticsProvider.getRequestCounts("News").get("Moscow"), 4);
        assertEquals(statisticsProvider.getRequestCounts("News").get("Luga"), 2);
        assertEquals(statisticsProvider.getRequestCounts("News").get("Surgut"), 1);
        assertEquals(statisticsProvider.getRequestCounts("News").get("Simpheropol"), 1);
        assertEquals(statisticsProvider.getRequestCounts("News").get("Bryansk"), 1);
        assertEquals(statisticsProvider.getRequestCounts("News").get("Pskov"), 1);
        assertEquals(statisticsProvider.getRequestCounts("News").size(), 6);
    }

    @Test
    void testWorkingGetMostFrequent() {
        statisticsProvider.onRequest("News", "Moscow");
        statisticsProvider.onRequest("News", "Moscow");
        statisticsProvider.onRequest("News", "Moscow");
        statisticsProvider.onRequest("News", "Moscow");
        statisticsProvider.onRequest("News", "Moscow");
        statisticsProvider.onRequest("News", "Moscow");
        statisticsProvider.onRequest("News", "Luga");
        statisticsProvider.onRequest("News", "Luga");
        statisticsProvider.onRequest("News", "Luga");
        statisticsProvider.onRequest("News", "Luga");
        statisticsProvider.onRequest("News", "Surgut");
        statisticsProvider.onRequest("News", "Simpheropol");
        statisticsProvider.onRequest("News", "Simpheropol");
        statisticsProvider.onRequest("News", "Simpheropol");
        statisticsProvider.onRequest("News", "Simpheropol");
        statisticsProvider.onRequest("News", "Simpheropol");
        statisticsProvider.onRequest("News", "Simpheropol");
        statisticsProvider.onRequest("News", "Moscow");
        statisticsProvider.onRequest("News", "Moscow");
        statisticsProvider.onRequest("News", "Bryansk");
        statisticsProvider.onRequest("News", "Bryansk");
        statisticsProvider.onRequest("News", "Pskov");
        statisticsProvider.onRequest("News", "Pskov");
        statisticsProvider.onRequest("News", "Pskov");
        statisticsProvider.onRequest("News", "Pskov");
        statisticsProvider.onRequest("News", "Moscow");
        statisticsProvider.onRequest("News", "Luga");
        statisticsProvider.onRequest("News", "Luga");
        statisticsProvider.onRequest("News", "Luga");

        assertEquals(
            statisticsProvider.getMostFrequent(
                2,
                "News"
            ).size(),
            2
        );

        assertTrue(
            statisticsProvider.getMostFrequent(
                3,
                "News"
            ).contains("Luga")
        );
        assertTrue(
            statisticsProvider.getMostFrequent(
                3,
                "News"
            ).contains("Moscow")
        );
        assertTrue(
            statisticsProvider.getMostFrequent(
                3,
                "News"
            ).contains("Simpheropol")
        );
        assertFalse(
            statisticsProvider.getMostFrequent(
                3,
                "News"
            ).contains("Pskov")
        );
        assertFalse(
            statisticsProvider.getMostFrequent(
                3,
                "News"
            ).contains("Bryansk")
        );
        assertFalse(
            statisticsProvider.getMostFrequent(
                3,
                "News"
            ).contains("Surgut")
        );

        statisticsProvider.onRequest("Weather", "Moscow");
        statisticsProvider.onRequest("Weather", "Moscow");
        statisticsProvider.onRequest("Weather", "Moscow");
        statisticsProvider.onRequest("Weather", "Moscow");
        statisticsProvider.onRequest("Weather", "Moscow");
        statisticsProvider.onRequest("Weather", "Moscow");
        statisticsProvider.onRequest("Weather", "Luga");
        statisticsProvider.onRequest("Weather", "Luga");
        statisticsProvider.onRequest("Weather", "Luga");
        statisticsProvider.onRequest("Weather", "Luga");
        statisticsProvider.onRequest("Weather", "Surgut");
        statisticsProvider.onRequest("Weather", "Simpheropol");
        statisticsProvider.onRequest("Weather", "Simpheropol");
        statisticsProvider.onRequest("Weather", "Simpheropol");
        statisticsProvider.onRequest("Weather", "Simpheropol");
        statisticsProvider.onRequest("Weather", "Simpheropol");
        statisticsProvider.onRequest("Weather", "Simpheropol");
        statisticsProvider.onRequest("Weather", "Moscow");
        statisticsProvider.onRequest("Weather", "Moscow");
        statisticsProvider.onRequest("Weather", "Bryansk");
        statisticsProvider.onRequest("Weather", "Bryansk");
        statisticsProvider.onRequest("Weather", "Pskov");
        statisticsProvider.onRequest("Weather", "Pskov");
        statisticsProvider.onRequest("Weather", "Pskov");
        statisticsProvider.onRequest("Weather", "Pskov");
        statisticsProvider.onRequest("Weather", "Moscow");
        statisticsProvider.onRequest("Weather", "Luga");
        statisticsProvider.onRequest("Weather", "Luga");
        statisticsProvider.onRequest("Weather", "Luga");

        assertEquals(
            statisticsProvider.getMostFrequent(
                2,
                "Weather"
            ).size(),
            2
        );

        assertTrue(
            statisticsProvider.getMostFrequent(
                3,
                "Weather"
            ).contains("Luga")
        );
        assertTrue(
            statisticsProvider.getMostFrequent(
                3,
                "Weather"
            ).contains("Moscow")
        );
        assertTrue(
            statisticsProvider.getMostFrequent(
                3,
                "Weather"
            ).contains("Simpheropol")
        );
        assertFalse(
            statisticsProvider.getMostFrequent(
                3,
                "Weather"
            ).contains("Pskov")
        );
        assertFalse(
            statisticsProvider.getMostFrequent(
                3,
                "Weather"
            ).contains("Bryansk")
        );
        assertFalse(
            statisticsProvider.getMostFrequent(
                3,
                "Weather"
            ).contains("Surgut")
        );
    }


    @AfterEach
    void reset() {
        statisticsProvider.clear();
        Logger.clearAllLogs();
    }
}
