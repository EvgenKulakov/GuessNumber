package game;

public class Messages {
    // кнопки
    public static final String BUTTON_NO_GAME = "Пожалуй, не стоит";
    public static final String BUTTON_LETS_GAME = "Давай сыграем!";
    public static final String BUTTON_PLAY_MORE = "Играть ещё!";
    public static final String BUTTON_FORTH = "Далее";
    public static final String BUTTON_MANUAL = "Инструкция";
    public static final String BUTTON_CLUE = "Подсказка";
    public static final String BUTTON_THANKS = "Спасибо";

    // titles
    public static final String MAIN_WINDOW = "Угадай число";
    public static final String ERROR = "Ошибка";
    public static final String VICTORY = "Ты угадал! Это число %s";
    public static final String WRONG = "Неправильно";
    public static final String YOU_LOSE = "Ты проиграл!";
    public static final String YOU_ARE_WELCOME = "Пожалуйста!";

    // сообщения
    public static final String ERROR_NUMBER = "Нужно ввести число от 0 до 999";
    public static final String INCORRECT_CHAR = "Ты, наверно, ошибся. Нужно ввести цифры.";
    public static final String CHANGE_NUMBER = "Ты уже пробовал число %s,\n введи другое если хочешь победить!";
    public static final String HINT = "<html>Подсказка:<br>Четвёртая цифра ПЯТЬ!</br><html>";
    public static final String SECOND_HINT = "Четвёртая цифра ПЯТЬ!";
    public static final String NUMBER_LESS = "Загаданное число меньше!";
    public static final String NUMBER_GREATER = "Загаданное число больше!";
    public static final String CORRECT_ANSWER = "Правильный ответ был %s";
    public static final String NO_GAME = "Нет игры - нет подсказки!";
    public static final String BYE = "До встречи в реальном мире!";

    // texts
    public static final String MANUAL = """
            Компьютер загадал рандомное число от 0 до 999,
            у вас всего 10 попыток, чтобы его отгадать.
            Если у вас не получится, то компьютер загадает новое число
            и у вас снова будет 10 попыток...""";

    public static final String START_TEXT = "<html><center>Я загадаю любое число от 0 до 999.</center>" +
                                            "<br>Отгадаешь число с 10 попыток, получишь подсказку! " +
                                            "Начнём?</br></html>";

    public static final String ENTER_NUMBER = "<html><center>Попытка №%s введи число:</center></html>";

    public static final String IMAGINE = "/matrix man.png";
}
