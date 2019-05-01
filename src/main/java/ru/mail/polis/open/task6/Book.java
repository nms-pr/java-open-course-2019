package ru.mail.polis.open.task6;

import java.util.ArrayList;
import java.util.Arrays;

import static ru.mail.polis.open.task6.Person.LINE_SEPARATOR;

public class Book {

    private final int id;
    private final String name;
    private final ArrayList<String> partitions;

    public Book(int id, String name, ArrayList<String> partitions) {
        this.id = id;
        this.name = name;
        this.partitions = new ArrayList<>(partitions);
    }

    public Book(int id, String name) {
        this.id = id;
        this.name = name;
        this.partitions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public ArrayList<String> getPartitions() {
        String[] ret = new String[partitions.size()];
        for (int i = 0; i < partitions.size(); i++) {
            ret[i] = partitions.get(i);
        }
        return new ArrayList<>(Arrays.asList(ret));
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Id: ")
                .append(id)
                .append("; Name: ")
                .append(name)
                .append(LINE_SEPARATOR);
        if (partitions.size() != 0) {
            res.append("Partitions: ");
            for (String partition: partitions) {
                res.append(partition).append("; ");
            }
            res.delete(res.length() - 2, res.length());
        } else {
            res.append("This book is not marked with partitions.");
        }
        return res.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Book)) {
            return false;
        }
        Book other = (Book) obj;
        return (other.toString().equals(toString()));
    }
}
