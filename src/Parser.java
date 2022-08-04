import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    static final String STR = "Информационно-вычислительная система Департамента операций на открытом рынке Банка России ИВС «Банк России (Ценные бумаги)». Подготовка и проведение операций РЕПО на фондовом, внебиржевом рынке и ОРЦБ. Руководство пользователя 52774461.425710.013.И3 12.05.2022\n" +
            "\n" +
            "Информационно-вычислительная система Департамента операций на открытом рынке Банка России ИВС «Банк России (Ценные бумаги)». Подготовка и проведение операций РЕПО на фондовом, внебиржевом рынке и ОРЦБ. Руководство администратора. 52774461.425710.013.И5 13.05.2022";
    static final String WARNING_INFO = "Не удалось получить данные!";
    static final String DESIGNATION_PATTERN = "\\d+\\.\\d+\\.\\d+\\.[ЁёА-я]\\d";
    static final String DATE_PATTERN = "\\d\\d\\.\\d\\d.\\d{4}";
    static final String NAME_PATTERN = "^[ЁёА-я\\s\\W]+";
    static final String FILENAME_PATTERN = "[Р][у][ЁёА-я]+\\s[ЁёА-я]+";

    public static void main(String[] args) {
        splitter(STR).forEach(System.out::println);
    }

    /**
     * @param str - Строка, которую нужно распарсить
     * @return - List со структурой хранения распарсенных данных Documentation
     */
    private static List<Documentation> splitter(String str) {
        Documentation doc;
        /* здесь будем хранить обьекты с заполнеными полями */
        List<Documentation> docList = new ArrayList<>();
        /* разделяем поступившую строку по значению перенос строки и результат кладем в массив */
        String[] strArr = str.split("\n");
        /* проверяем на пустые строки */
        for (String s : strArr) {
            if (s.isBlank()) continue;
            /* создаем обьект для хранения распарсенных данных */
            doc = new Documentation();
            /* создаем шаблон по которому будем искать нужные данные */
            Matcher designationMatcher = Pattern.compile(DESIGNATION_PATTERN).matcher(s);
            /* ищем */
            if (designationMatcher.find()) {
                /* группируем и сохраняем в ранее созданном обьекте */
                doc.setDesignation(designationMatcher.group());
            } else {
                doc.setDesignation(WARNING_INFO);
            }

            Matcher dateMatcher = Pattern.compile(DATE_PATTERN).matcher(s);
            if (dateMatcher.find()) {
                doc.setDate(dateMatcher.group());
            } else {
                doc.setDate(WARNING_INFO);
            }

            Matcher matcherName = Pattern.compile(NAME_PATTERN).matcher(s);
            if (matcherName.find()) {
                doc.setName(matcherName.group());
            } else {
                doc.setName(WARNING_INFO);
            }

            Matcher fileNameMatcher = Pattern.compile(FILENAME_PATTERN).matcher(s);
            if (fileNameMatcher.find()) {
                doc.setFileName(fileNameMatcher.group() + " " + doc.getDesignation());
            } else {
                doc.setFileName(WARNING_INFO);
            }
            /* сохраняем заполненный обьект в структуре лист */
            docList.add(doc);
        }
        return docList;
    }
}

