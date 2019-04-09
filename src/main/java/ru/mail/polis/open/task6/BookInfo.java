package ru.mail.polis.open.task6;

import org.jetbrains.annotations.NotNull;

public class BookInfo {

    public final @NotNull String name;
    public final @NotNull String author;
    public final @NotNull Section section;
    public final int id;

    public BookInfo(@NotNull String name, @NotNull String author, @NotNull Section section, int id) {
        this.name = name;
        this.author = author;
        this.section = section;
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BookInfo) {
            return ((BookInfo) obj).id == id;
        }
        return false;
    }
}
