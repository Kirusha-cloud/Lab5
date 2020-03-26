package Lab5.ProgrammOrg;

import Lab5.Commands.Command;

import java.io.IOException;

/**
 * Класс - исполнитель существующих команд.
 */

public class Commander {
    private Command add;
    private Command addIfMax;
    private Command clear;
    private Command executeScript;
    private Command exit;
    private Command groupCountingByTransport;
    private Command help;
    private Command history;
    private Command info;
    private Command minByFurniture;
    private Command printFieldNOF;
    private Command removeById;
    private Command removeGreater;
    private Command save;
    private Command show;
    private Command updateId;

    public Commander(Command add, Command addIfMax, Command clear, Command executeScript, Command exit,
                     Command groupCountingByTransport, Command help, Command history, Command info,
                     Command minByFurniture, Command printFieldNOF, Command removeById, Command removeGreater,
                     Command save, Command show, Command updateId) {
        this.add = add;
        this.addIfMax = addIfMax;
        this.clear = clear;
        this.executeScript = executeScript;
        this.exit = exit;
        this.groupCountingByTransport = groupCountingByTransport;
        this.help = help;
        this.history = history;
        this.info = info;
        this.minByFurniture = minByFurniture;
        this.printFieldNOF = printFieldNOF;
        this.removeById = removeById;
        this.removeGreater = removeGreater;
        this.save = save;
        this.show = show;
        this.updateId = updateId;
    }

    void addCommand(String[] args) throws IOException {
        add.execute(args);
    }

    void addIfMaxCommand(String[] args) throws IOException {
        addIfMax.execute(args);
    }

    void clearCommand(String[] args) throws IOException {
        clear.execute(args);
    }

    void executeScriptCommand(String[] args) throws IOException {
        executeScript.execute(args);
    }

    void exitCommand(String[] args) throws IOException {
        exit.execute(args);
    }

    void groupCountingByTransportCommand(String[] args) throws IOException {
        groupCountingByTransport.execute(args);
    }

    void helpCommand(String[] args) throws IOException {
        help.execute(args);
    }

    void historyCommand(String[] args) throws IOException {
        history.execute(args);
    }

    void infoCommand(String[] args) throws IOException {
        info.execute(args);
    }

    void minByFurnitureCommand(String[] args) throws IOException {
        minByFurniture.execute(args);
    }

    void printFieldCommand(String[] args) throws IOException {
        printFieldNOF.execute(args);
    }

    void removeByIdCommand(String[] args) throws IOException {
        removeById.execute(args);
    }

    void removeGreaterCommand(String[] args) throws IOException {
        removeGreater.execute(args);
    }

    void saveCommand(String[] args) throws IOException {
        save.execute(args);
    }

    void showCommand(String[] args) throws IOException {
        show.execute(args);
    }

    void updateIdCommand(String[] args) throws IOException {
        updateId.execute(args);
    }


}
