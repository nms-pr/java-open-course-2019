package ru.mail.polis.open.invertedIndex;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ConfigFileProviderTest {

    private File testFile;
    private static final String TEST_FILE_NAME = "testFile";

    // создание тестового конфигурационного файла
    @BeforeEach
    void createTestFile() {

        testFile = new File(TEST_FILE_NAME);
        try {
            if (!testFile.createNewFile()) {
                System.out.println("Error creating test file.");
            }
        } catch (IOException e) {
            fail("Error creating test file.");
        }

        try (FileWriter writer = new FileWriter(testFile)){
            writer.write("host: localhost\n");
            writer.write("database: postgres\n");
            writer.write("port: 5432\n");
            writer.write("login: login\n");
            writer.write("password: password\n");
            writer.write("depthOfSearch: 5\n");
            writer.write("numberOfStreams: 4\n");
            writer.flush();
        } catch (IOException e) {
            fail("Error filling test file.");
        }

    }

    @Test
    void configFileProviderTest() {
        try {
            ConfigFileProvider.readFile(TEST_FILE_NAME);
        } catch (IOException e) {
            fail("Error reading test file.");
        }
        assertEquals("localhost", ConfigFileProvider.getHost());
        assertEquals("postgres", ConfigFileProvider.getDatabase());
        assertEquals("5432", ConfigFileProvider.getPort());
        assertEquals("login", ConfigFileProvider.getLogin());
        assertEquals("password", ConfigFileProvider.getPassword());
        assertEquals(5, ConfigFileProvider.getDepthOfSearch());
        assertEquals(4, ConfigFileProvider.getNumberOfStreams());
    }


    // удаление тестового конфигурационного файла
    @AfterEach
    void deleteTestFile() {

        if (!testFile.delete()) {
            fail("Error deleting test file.");
        }
    }

}
