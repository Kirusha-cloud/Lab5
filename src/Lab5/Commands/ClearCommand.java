package Lab5.Commands;

import Lab5.ProgrammOrg.CommandBase;
/**
 * Класс, реализующий выполнение команды clear.
 */
public class ClearCommand implements Command {
    private CommandBase commandBase;

    public ClearCommand(CommandBase commandBase) {
        this.commandBase = commandBase;
    }

    @Override
    public void execute(String[] args) {
        commandBase.clear();
    }
}
