package ru.mail.polis.open.task6;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

/**
 * Есть книги
 * - У книги есть
 * -- Идентификатор
 * -- Название
 * -- Место на полке
 * -- Раздел к которому она относится (вид лит-ры)
 * -- Время выдачи и возврата
 * -- Информация о том кто взял (брал)
 * - Книг с одинаковыми идентификатором несколько
 */

public class Book {

    private int identifier;
    private String name;
    private String shelfSpace;
    private TypeOfLiterature typeOfLiterature;
    private List<InformationWhoTook> informationWhoTooks;


    public Book(int identifier, String name, String shelfSpace, TypeOfLiterature typeOfLiterature) {
        this.identifier = identifier;
        this.name = name;
        this.shelfSpace = shelfSpace;
        this.typeOfLiterature = typeOfLiterature;
        this.informationWhoTooks = new ArrayList<>();
    }

    //Вид лит-ры
    enum TypeOfLiterature {
        Fiction,
        Documentary,
        Memoirs,
        Scientific,
        Reference,
        Training,
        Technical;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return identifier == book.identifier &&
                name.equals(book.name) &&
                shelfSpace.equals(book.shelfSpace) &&
                typeOfLiterature == book.typeOfLiterature &&
                Objects.equals(informationWhoTooks, book.informationWhoTooks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, name, shelfSpace, typeOfLiterature, informationWhoTooks);
    }

    class InformationWhoTook {
        private Calendar timeOfIssue;
        private Calendar timeReturn;
        private Visitor visitor;

        public InformationWhoTook(Calendar timeOfIssue, Calendar timeReturn, Visitor visitor) {
            this.timeOfIssue = timeOfIssue;
            this.timeReturn = timeReturn;
            this.visitor = visitor;
        }
    }
}
