package Lab5.ProgrammOrg;

import com.google.gson.JsonSyntaxException;

import java.io.IOException;

import java.util.Scanner;

/**
 * Класс, обрабатывающий ввод команд с клавиатуры.
 */
public class ProgrammRunner {
    private Scanner in = new Scanner(System.in);
    private Commander commander;

    public ProgrammRunner(Commander commander) {
        this.commander = commander;
    }

    /**
     * Метод, запускающий программу.
     */
    public void startProgram() throws IOException, JsonSyntaxException {
        try {
            System.out.println("Введите команду: ");
            while (in.hasNext()) {
                String[] com = in.nextLine().split(" ");
                switch (com[0]) {
                    case "add":
                        commander.addCommand(com);
                        System.out.println("Введите команду: ");
                        break;
                    case "add_if_max":
                        commander.addIfMaxCommand(com);
                        System.out.println("Введите кманду: ");
                        break;
                    case "clear":
                        commander.clearCommand(com);
                        System.out.println("Введите команду: ");
                        break;
                    case "execute_script":
                        commander.executeScriptCommand(com);
                        System.out.println("Введите команду: ");
                        break;
                    case "exit":
                        commander.exitCommand(com);
                        break;
                    case "group_counting_by_transport":
                        commander.groupCountingByTransportCommand(com);
                        System.out.println("Введите команду: ");
                        break;
                    case "help":
                        commander.helpCommand(com);
                        System.out.println("Введите команду: ");
                        break;
                    case "history":
                        commander.historyCommand(com);
                        System.out.println("Введите команду: ");
                        break;
                    case "info":
                        commander.infoCommand(com);
                        System.out.println("Введите команду: ");
                        break;
                    case "min_by_furniture":
                        commander.minByFurnitureCommand(com);
                        System.out.println("Введите команду: ");
                        break;
                    case "print_field_descending_number_of_rooms":
                        System.out.println("Введите команду: ");
                        commander.printFieldCommand(com);
                        break;
                    case "remove_by_id":
                        commander.removeByIdCommand(com);
                        System.out.println("Введите команду: ");
                        break;
                    case "remove_greater":
                        commander.removeGreaterCommand(com);
                        System.out.println("Введите команду: ");
                        break;
                    case "save":
                        commander.saveCommand(com);
                        System.out.println("Введите команду: ");
                        break;
                    case "show":
                        commander.showCommand(com);
                        System.out.println("Введите команду: ");
                        break;
                    case "update_id":
                        commander.updateIdCommand(com);
                        System.out.println("Введите команду: ");
                        break;
                    default:
                        System.out.println("Вы ввели неверную команду! Для того, чтобы увидеть список команд введите \"help\".");
                }

            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Аргумент команды не введён, введите команду заново! Чтобы увидеть доступные команды " +
                    "введите \"help\" ");
            startProgram();
        }
    }
}
