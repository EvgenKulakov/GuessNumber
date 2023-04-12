package game;

public class Messages {
    /* кнопки */
    public static final String BUTTON_NO_GAME = "Пожалуй, не стоит";
    public static final String BUTTON_LETS_GAME = "Давай сыграем!";
    public static final String BUTTON_PLAY_MORE = "Играть ещё!";
    public static final String BUTTON_FORTH = "Далее";
    public static final String BUTTON_MANUAL = "Инструкция";
    public static final String BUTTON_THANKS = "Спасибо";
    public static final String BUTTON_I_KNEW = "Я знал!";
    public static final String BUTTON_REBOOT = "ПЕРЕЗАГРУЗКА";

    /* titles */
    public static final String MAIN_WINDOW = "Угадай число";
    public static final String ERROR = "Ошибка";
    public static final String VICTORY = "Ты угадал! Это число %s";
    public static final String WRONG = "Неправильно";
    public static final String YOU_LOSE = "Ты проиграл!";
    public static final String YOU_ARE_WELCOME = "Пожалуйста!";
    public static final String NOT_FUNNY = "Не смеши";

    /* сообщения */
    public static final String EMPTY_STRING = "Введи ЧИСЛО, чтобы сделать ХОД!";
    public static final String CHANGE_NUMBER = "Ты уже пробовал число %s,\n введи другое если хочешь победить!";
    public static final String HINT = "<html>Подсказка:<br>Четвёртая цифра ПЯТЬ!</br><html>";
    public static final String SECOND_HINT = "Четвёртая цифра ПЯТЬ!";
    public static final String NUMBER_LESS = "Загаданное число МЕНЬШЕ!";
    public static final String NUMBER_GREATER = "Загаданное число БОЛЬШЕ!";
    public static final String CORRECT_ANSWER = "Правильный ответ был %s";
    public static final String NO_GAME = "  Нет игры - нет подсказки!";
    public static final String BYE = "До встречи в реальном мире!";
    public static final String YOU_DID_NOT_KNOW = "      Ничего ты не знал!";

    /* texts */
    public static final String MANUAL = new StringBuilder()
            .append("Компьютер загадал рандомное число от 0 до 999,\n")
            .append("у вас всего 10 попыток, чтобы его отгадать.\n")
            .append("Если у вас не получится, то компьютер загадает новое число\n")
            .append("и у вас снова будет 10 попыток...").toString();

    public static final String START_TEXT = new StringBuilder()
            .append("<html><center>Я загадаю любое число от 0 до 999.</center>")
            .append("<br>Отгадаешь число с 10 попыток, получишь подсказку! ")
            .append("Начнём?</br></html>").toString();

    public static final String ENTER_NUMBER = "Попытка №%s введи число:";

    /* ссылка на картинку */
    public static final String IMAGINE = "/mr_matrix.jpg";
}
