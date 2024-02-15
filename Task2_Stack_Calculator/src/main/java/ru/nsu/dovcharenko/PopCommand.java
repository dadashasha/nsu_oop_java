package ru.nsu.dovcharenko;
import java.util.Stack;
//Снять число со стека
public class PopCommand implements Command {
    @Override
    public void execute(Context context) throws CommandExecutionException {
        Stack<Double> stack = context.getStack();
        if (stack.isEmpty()) {
            throw new CommandExecutionException("Stack is empty, cannot perform POP operation");
        }
        stack.pop();
    }
}
