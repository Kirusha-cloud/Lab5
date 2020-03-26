package Lab5.Parser;

import com.google.gson.Gson;

import com.google.gson.GsonBuilder;

/**
 * Интерфейс, устанавливающий файл, из которого заполняется коллекция.
 * Создаётся экземпляр класса Gson для парсинга файла формата json в коллекцию.
 */
public interface Sources {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String path_out = System.getenv().get("INPUT");
}
