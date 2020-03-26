package Lab5.Commands;

import Lab5.ProgrammOrg.CommandBase;
/**
 * Класс, реализующий выполнение команды remove_greater.
 */
public class RemoveGreaterCommand implements Command {
    private CommandBase commandBase;

    public RemoveGreaterCommand(CommandBase commandBase) {
        this.commandBase = commandBase;
    }

    @Override
    public void execute(String[] args) {
        commandBase.removeGreater(args);
    }
}
