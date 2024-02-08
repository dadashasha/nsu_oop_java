package ru.nsu.dovcharenko;

import java.util.Scanner;

class BullsAndCowsGame {
    private int[] computerGuessedNumber; // задуманное компьютером число
    private int[] userEnteredNumber; // введенное пользователем число
    private int numberLength; // длинна массива для чисел

    private void printCompNum(int[] computerGuessedNumber){ // потом убрать, нужно чтобы следить за работой кода
        for (int i = 0; i < numberLength; ++i){
            System.out.print(computerGuessedNumber[i] + " ");
        }
    }

    // ввод пользователем длины числа
    private void getCompNum() {
        Scanner scanner = new Scanner(System.in); // Scanner - класс в Java, System.in - стандартный поток ввода
        do {
            System.out.println("Введите длину числа от 1 до 10");
            while (!scanner.hasNextInt()){ //.hasNextInt() - метод, проверяющий следующий ввод на целочисленность
                System.out.println("Введите число");
                scanner.next(); // .next() - метод, к. считывает следующее слово (строку без пробеллов из потока ввода)
            }
            numberLength = scanner.nextInt(); // .nextInt() - считывание целого числа
        } while (numberLength <= 0 || numberLength > 10); // ввод производится, пока не будет вверная длина от 1 до 10

        setComputerGuessedNumber(createRandArray()); // если длина введена правильно, то создается рандомное число
    }

    public void setComputerGuessedNumber(int[] computerGuessedNumber){
        this.computerGuessedNumber = computerGuessedNumber;
    }

    // генерация числа
    private int[] createRandArray(){
        int[] tempArr = new int[numberLength];
        for (int i = 0; i < this.numberLength; ++i){
            tempArr[i] = (int) (Math.random() * 10); // заполнение случайными числами
        }

        // Проверка на повторение цифр
        boolean checkArr = false;
        while (!checkArr){
            if (tempArr.length == 1){
                break;
            }
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


    public void setUserEnteredNumber(int[] userEnteredNumber){
        this.userEnteredNumber = userEnteredNumber;
    }
    // ввод числа пользователя
    private int[] getUserEnteredNumber(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число длинной " + numberLength + " цифр");
        String userNumString = scanner.nextLine(); // читается вся строка, введенная пользователем
        userEnteredNumber = new int[numberLength];

        // проверяем, что введенная строка содержит только цифры и ее длина совпадает с ожидаемой длиной числа
        if (userNumString.matches("\\d{" + numberLength + "}")) { // регулярное выражение означает, что строка должна состоять из numberLength подряд
            // Преобразуем строку в массив цифр
            for (int i = 0; i < numberLength; ++i) {
                // метод, к. преобраз. символ в числ. знач., представляющ. этот символ, иначе -1
                userEnteredNumber[i] = Character.getNumericValue(userNumString.charAt(i)); // символ на позиции i в строке input
            }
        } else {
            // если введенная строка не соответствует ожидаемой длине или содержит нецифровые символы, выдаем сообщение об ошибке
            System.out.println("Ошибка! Введите число из " + numberLength + " цифр.");
            // рекурсивно вызываем этот метод снова, чтобы попросить пользователя ввести число снова
            userEnteredNumber = getUserEnteredNumber();
        }

        setUserEnteredNumber(userEnteredNumber);
        return userEnteredNumber;
    }


    // проверка числа пользователя
    private void checkUserNumber(int[] userEnteredNumber) {
        int bulls = 0;
        int cows = 0;
        boolean[] foundBulls = new boolean[numberLength];
        boolean[] foundCows = new boolean[numberLength];

        while (i < numberLength){
            if (userEnteredNumber[i] == computerGuessedNumber[i]){
                ++bulls;
                foundBulls[i] = true;
            }
            for (int j = i; j < numberLength; ++j){
                if (!foundBulls[j] && !foundCows[j] && userEnteredNumber[i] == computerGuessedNumber[j]) {
                    ++cows;
                    foundCows[j] = true;
                    break;
                }
            }
        }

        // выводим результат проверки
        if (bulls == numberLength) {
            System.out.println("Поздравляем! Вы угадали число!");
            System.exit(0); // завершаем программу после выигрыша
        } else {
            System.out.println("Результат: " + bulls + " бык(ов), " + cows + " коров(а).");
        }
    }

    public static void main(String[] args) {
        System.out.println("Привет! Давай сыграем в игру \"Быки и коровы\"? Компьютер уже загадал число, попробуй отгадать!");
        BullsAndCowsGame game = new BullsAndCowsGame();
        game.getCompNum();

        // Вывод загаданного компьютером числа
        System.out.print("Загаданное компьютером число: ");
        game.printCompNum(game.computerGuessedNumber);
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        boolean continueGame = true;
        do {
            int[] userEnteredNumber = game.getUserEnteredNumber();
            game.checkUserNumber(userEnteredNumber);

            System.out.println("Хотите сыграть еще раз? (Y/N)");
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("Y")) {
                continueGame = false;
            }
        } while (continueGame);

        System.out.println("Спасибо за игру!");
    }
}
