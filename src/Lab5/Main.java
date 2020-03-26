package Lab5;

import Lab5.Commands.*;

import Lab5.FlatUnderClasses.Flat;

import Lab5.ProgrammOrg.CommandBase;

import Lab5.ProgrammOrg.Commander;

import Lab5.ProgrammOrg.ProgrammRunner;

import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.InputStreamReader;

import java.lang.reflect.Type;

import java.util.PriorityQueue;

import static Lab5.Parser.Sources.path_out;

/**
 * Глвный класс, который осуществляет запуск программы.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        try {
            /**
             * Заполняем коллекцию значениями из файла.
             * Создаём объект класса @CommandBase, @Commander, @ProgrammRunnner
             */
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(path_out));
            Type foundQueueType = new TypeToken<PriorityQueue<Flat>>() {
            }.getType();
            PriorityQueue<Flat> flatsQueue = new Gson().fromJson(inputStreamReader, foundQueueType);
            CommandBase commandBase = new CommandBase(flatsQueue);
            Commander commander = new Commander(new AddCommand(commandBase), new AddIfMaxCommand(commandBase),
                    new ClearCommand(commandBase), new ExecuteScriptCommand(commandBase), new ExitCommand(commandBase),
                    new GroupCountingByTransportCommand(commandBase), new HelpCommand(commandBase),
                    new HistoryCommand(commandBase), new InfoCommand(commandBase), new MinByFurnitureCommand(commandBase),
                    new PrintFieldNOFCommand(commandBase), new RemoveByIdCommand(commandBase),
                    new RemoveGreaterCommand(commandBase), new SaveCommand(commandBase), new ShowCommand(commandBase),
                    new UpdateIdCommand(commandBase));
            ProgrammRunner programmRunner = new ProgrammRunner(commander);
            programmRunner.startProgram();
        } catch (com.google.gson.JsonSyntaxException e) {
            System.out.println("Неверно заполненный файл! Программа не может быть выполнена!");
        }
        catch (NullPointerException e){
            System.out.println("Неверно указана переменная окружения! Программа не может быть выполнена!");
        }
        catch (FileNotFoundException e){
            System.out.println("У файла, хранящего коллекцию не хватает прав на чтение. Добавьте право на чтение файлу " +
                    "и перезапустите программу.");
        }
    }
}
