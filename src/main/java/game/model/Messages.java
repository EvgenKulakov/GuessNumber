package game.model;

public class Messages {

    /* titles */
    public static final String MAIN_WINDOW = "Угадай число";
    public static final String ERROR = "Ошибка";
    public static final String VICTORY = "Ты угадал! Это число %s";
    public static final String WRONG = "Неправильно";
    public static final String YOU_LOSE = "Ты проиграл!";
    public static final String YOU_WELCOME = "Пожалуйста!";
    public static final String NOT_FUNNY = "Не смеши";

    /* сообщения */
    public static final String EMPTY_STRING = "Введи ЧИСЛО, чтобы сделать ХОД!";
    public static final String CHANGE_NUMBER = "Ты уже пробовал число %s,\n введи другое если хочешь победить!";
    public static final String HINT = "Подсказка:\nЧетвёртая цифра ПЯТЬ!";
    public static final String NUMBER_LESS = "Загаданное число МЕНЬШЕ!";
    public static final String NUMBER_GREATER = "Загаданное число БОЛЬШЕ!";
    public static final String ANSWER = "Правильный ответ был %s";
    public static final String NO_CLUE = "Нет игры - нет подсказки!";
    public static final String BYE = "До встречи в реальном мире!";
    public static final String NOT_KNOW = "Ничего ты не знал!";

    /* texts */
    public static final String MANUAL = new StringBuilder()
            .append("Компьютер загадал рандомное число от 0 до 999,\n")
            .append("у вас всего 10 попыток, чтобы его отгадать.\n")
            .append("Если у вас не получится, то компьютер загадает новое число\n")
            .append("и у вас снова будет 10 попыток...").toString();

    public static final String START_TEXT = new StringBuilder()
            .append("Я загадаю любое число от 0 до 999.\n")
            .append("Отгадаешь число с 10 попыток, получишь подсказку! ")
            .append("Начнём?").toString();

    public static final String ENTER = "Попытка №%s введи число";
}
