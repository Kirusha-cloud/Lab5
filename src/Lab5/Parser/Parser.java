package Lab5.Parser;

import Lab5.FlatUnderClasses.Flat;

import java.util.PriorityQueue;

/**
 * Класс, который реализует парсинг коллекции в строку формата json.
 */
public class Parser implements Sources {

    public static char[] parsToJson(PriorityQueue<Flat> flats) {
        String json = gson.toJson(flats);
        return json.toCharArray();
    }
}
