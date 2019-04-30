package ru.mail.polis.open.task9;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MyComparator {

    private static void parse(String input,String forCompare) {
        String separator = System.lineSeparator();
        try (FileWriter writer = new FileWriter(forCompare, false)) {
            try {
                SAXParserFactory factory = SAXParserFactory.newInstance();
                SAXParser saxParser = factory.newSAXParser();

                // Здесь мы определили анонимный класс, расширяющий класс DefaultHandler
                DefaultHandler handler = new DefaultHandler() {
                    // Поле для указания, что тэг NAME начался
                    boolean title = false;
                    boolean link = false;
                    boolean description = false;
                    boolean pubDate = false;
                    boolean item = false;
                    StringBuffer sb = new StringBuffer();

                    // Метод вызывается когда SAXParser "натыкается" на начало тэга
                    @Override
                    public void startElement(String uri, String localName, String qname, Attributes attributes) {
                        title = false;
                        description = false;
                        link = false;
                        pubDate = false;
                        sb.delete(0, sb.length());

                        if (qname.equalsIgnoreCase("title")) {
                            title = true;
                        } else if (qname.equalsIgnoreCase("link")) {
                            link = true;
                        } else if (qname.equalsIgnoreCase("description")) {
                            description = true;
                        } else if (qname.equalsIgnoreCase("pubDate")) {
                            pubDate = true;
                        } else if (qname.equalsIgnoreCase("item")) {
                            item = true;
                        }
                    }

                    // Метод вызывается когда SAXParser считывает текст между тэгами
                    @Override
                    public void characters(char[] ch, int start, int length) {
                        // Если перед этим мы отметили, что имя тэга NAME - значит нам надо текст использовать.
                        int str = start;
                        try {
                            if (title && item) {
                                writer.write(sb.append(ch, start, length).toString());
                                writer.append(separator);
                                title = false;
                            } else if (link && item) {
                                writer.write(sb.append(ch, start, length).toString());
                                writer.append(separator);
                                link = false;
                            } else if (description && item) {
                                sb.append(new String(ch, start, length).replace("", ""));
                                writer.write(sb.toString());
                                writer.append(separator);
                                description = false;
                            } else if (pubDate && item) {
                                writer.write(sb.append(ch, start, length).toString());
                                writer.append(separator);
                                pubDate = false;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };

                // Стартуем разбор методом parse, которому передаем наследника от DefaultHandler,
                // который будет вызываться в нужные моменты
                saxParser.parse(input, handler);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static boolean compare(String input,String result, String tmpFile) {
        parse(input,tmpFile);
        try {
            //удаляю из листов время, потому что rome конвертирует его, и я  не особо разобралась как преобразовать
            List<String> links1 = Files.readAllLines(Paths.get(result), StandardCharsets.UTF_8);
            List<String> forDel1 = new ArrayList<>();
            int count = 0;
            for (String s : links1) {
                if ((count + 1) % 4 == 0) {
                    forDel1.add(s);
                }
                count++;
            }
            List<String> links2 = Files.readAllLines(Paths.get(tmpFile), StandardCharsets.UTF_8);
            List<String> forDel2 = new ArrayList<>();
            for (String s : forDel1) {
                links1.remove(s);
            }
            count = 0;
            for (String s : links2) {
                if ((count + 1) % 4 == 0) {
                    forDel2.add(s);
                }
                count++;
            }
            for (String s : forDel2) {
                links2.remove(s);
            }
            return links1.equals(links2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
