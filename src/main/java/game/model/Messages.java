package game.model;
/**
 * сообщения и главный текст
 */
public class Messages {

    public static final String EMPTY_STRING = "Введи ЧИСЛО, чтобы сделать ХОД!";
    public static final String CHANGE_NUMBER = "Ты уже пробовал число %s,\n введи другое если хочешь победить!";
    public static final String HINT = "Подсказка:\nЧетвёртая цифра ПЯТЬ!";
    public static final String HINT_FINAL = "Четвёртая цифра ПЯТЬ!";
    public static final String LOW = "Загаданное число МЕНЬШЕ!";
    public static final String HIGH = "Загаданное число БОЛЬШЕ!";
    public static final String ANSWER = "Правильный ответ был %s";
    public static final String NO_CLUE = "Нет игры - нет подсказки!";
    public static final String BYE = "До встречи в реальном мире!";
    public static final String NOT_KNOW = "Ничего ты не знал!";

    public static final String MANUAL = new StringBuilder()
            .append("Компьютер загадал рандомное число от 0 до 999,\n")
            .append("у вас всего 10 попыток, чтобы его отгадать.\n")
            .append("Если у вас не получится, то компьютер загадает новое число\n")
            .append("и у вас снова будет 10 попыток...").toString();

    public static final String START_TEXT = new StringBuilder()
            .append("Я загадаю любое число от 0 до 999.\n")
            .append("Отгадаешь число с 10 попыток, получишь подсказку! ")
            .append("Начнём?").toString();

    public static final String REBOOT = new StringBuilder()
            .append("Будет сброшен прогресс игры\n")
            .append("и компьютер загадает новое число.\n")
            .append("Перезагрузить?").toString();

    public static final String ENTER = "Попытка №%s введи число";
}
