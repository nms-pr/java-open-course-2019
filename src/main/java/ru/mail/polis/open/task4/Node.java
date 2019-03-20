package ru.mail.polis.open.task4;

import java.util.Objects;

public class Node {

    private Expr expr;
    private Node left;
    private Node right;
    private Node parent;

    public Node(Expr expr, Node parent) {
        this.expr = expr;
        this.parent = parent;
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr = expr;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean equalsNodes(Node node, Node oNode) {
        if (node == null ^ oNode == null) {
            return false;
        }
        if (node != null && oNode != null) {
            return node.getExpr().evaluate() == oNode.getExpr().evaluate();
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return expr.evaluate() == node.getExpr().evaluate()
            && equalsNodes(parent, node.parent)
            && equalsNodes(left, node.left)
            && equalsNodes(right, node.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expr, left, right, parent);
    }
}