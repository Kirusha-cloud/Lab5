package Lab5.Commands;

import Lab5.ProgrammOrg.CommandBase;

/**
 * Класс, реализующий выполнение команды add.
 */

public class AddCommand implements Command {
    private CommandBase commandBase;

    public AddCommand(CommandBase commandBase) {
        this.commandBase = commandBase;
    }

    @Override
    public void execute(String[] args) {

        commandBase.add(args);
    }
}
