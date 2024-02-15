package ru.nsu.dovcharenko;

import java.util.Stack;

public class Context {
    private Stack<Double> stack;

    public Context() {
        this.stack = new Stack<>();
    }

    public Stack<Double> getStack() {
        return stack;
    }
}
