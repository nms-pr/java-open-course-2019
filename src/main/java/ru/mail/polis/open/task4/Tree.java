package ru.mail.polis.open.task4;

import java.util.Objects;

public class Tree {

    private Node head;

    public Tree(){}

    public Tree(Expr expr) {
        head = new Node(expr, null);
    }

    public Node getHead() {
        return head;
    }

    public void add(Expr expr) {
        if (head == null) {
            head = new Node(expr, null);
        } else {

            Node currentNode = head;

            while (currentNode != null) {

                if (currentNode.getLeft() == null) {
                    currentNode.setLeft(new Node(expr, currentNode));
                    break;
                }
                currentNode = currentNode.getLeft();

                if (currentNode.getRight() == null) {
                    currentNode.setRight(new Node(expr, currentNode));
                    break;
                }
                currentNode.getRight();
            }
        }
    }

    public void reset() {
        head = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tree tree = (Tree) o;
        return Objects.equals(head, tree.head);
    }

    @Override
    public int hashCode() {
        return Objects.hash(head);
    }
}

