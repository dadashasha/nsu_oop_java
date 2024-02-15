package ru.nsu.dovcharenko;
//НУжно будет импортировать классы в этот интерфейс
public interface Command {
    void execute(Context context) throws CommandExecutionException; //нужно определить классы
}
