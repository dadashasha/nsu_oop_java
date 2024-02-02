package ru.nsu.dovcharenko;

import java.util.Scanner;

public class BullsAndCowsGame {
    private int[] computerGuessedNumber; // Задуманное компьютером число
    private int[] userEnteredNumber; // Введенное пользователем число
    private int numberLength; //Длинна массива для чисел

    private void printCompNum(int[] computerGuessedNumber){ // Потом убрать
        for (int i = 0; i < numberLength; ++i){
            System.out.print(computerGuessedNumber[i] + " ");
        }
    }

    private void getCompNum() {
        Scanner scanner = new Scanner(System.in); // Scanner - класс в Java, System.in - стандартный поток ввода
        do {
            System.out.println("Введите число от 1 до 10");
            while (!scanner.hasNextInt()){ //.hasNextInt() - метод, проверяющий следующий ввод на целочисленность
                System.out.println("Введите число");
                scanner.next(); // .next() - метод, к. считывает следующее слово (строку без пробеллов из потока ввода)
            }
            numberLength = scanner.nextInt(); // .nextInt() - считывание целого числа
        } while (numberLength <= 0 || numberLength > 10);

        setComputerGuessedNumber(createRandArray());
    }

    public void setComputerGuessedNumber(int[] computerGuessedNumber){
        this.computerGuessedNumber = computerGuessedNumber;
    }

    private int[] createRandArray(){
        int[] tempArr = new int[numberLength];
        for (int i = 0; i < this.numberLength; ++i){
            tempArr[i] = (int) (Math.random() * 10);
        }

        // Проверка на повторение цифр
        boolean checkArr = false;
        while (!checkArr){
            for (int i = 0; i < tempArr.length; ++i){
                for (int j = 0; j < tempArr.length; ++j){
                    if (i != j){
                        if (tempArr[i] == tempArr[j]){
                            tempArr[j] = (int) (Math.random() * 10);
                        }
                    }
                }
            }

            boolean checkElement = false;
            for (int i = 0; i < tempArr.length && !checkElement; ++i){
                for (int j = 0; j < tempArr.length && !checkElement; ++j){
                    if (i != j){
                        if (tempArr[i] == tempArr[j]){
                            checkElement = true;
                            checkArr = false;
                        }
                        else checkArr = true;
                    }
                }
            }
        }

        return tempArr;
    }

    public static void main(String[] args) {
        System.out.print("Привет! Давай сыграем в игру \"Быки и коровы\"? Компьютер уже загадал число, попробуй отгадать!");
        // Добавить описание правил игры
    }
}