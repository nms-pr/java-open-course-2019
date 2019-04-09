package ru.mail.polis.open.task6;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

public class Person {

    public final @NotNull String name;
    public final @NotNull String surname;
    public final @NotNull LocalDate birthday;

    public Person(@NotNull String name, @NotNull String surname, @NotNull LocalDate birthday) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Person) {
            Person objPerson = (Person) obj;
            return this.birthday.equals(objPerson.birthday)
                    && this.name.equals(objPerson.name)
                    && this.surname.equals(objPerson.surname);
        }
        return false;
    }
}
