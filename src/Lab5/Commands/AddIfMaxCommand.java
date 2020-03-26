package Lab5.Commands;

import Lab5.ProgrammOrg.CommandBase;
/**
 * Класс, реализующий выполнение команды add_if_max.
 */
public class AddIfMaxCommand implements Command {
    private CommandBase commandBase;

    public AddIfMaxCommand(CommandBase commandBase) {
        this.commandBase = commandBase;
    }

    @Override
    public void execute(String[] args) {
        commandBase.addIfMax(args);
    }
}
