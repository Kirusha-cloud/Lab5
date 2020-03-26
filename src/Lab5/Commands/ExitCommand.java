package Lab5.Commands;

import Lab5.ProgrammOrg.CommandBase;
/**
 * Класс, реализующий выполнение команды exit.
 */
public class ExitCommand implements Command {
    private CommandBase commandBase;

    public ExitCommand(CommandBase commandBase) {
        this.commandBase = commandBase;
    }

    @Override
    public void execute(String[] args) {
        commandBase.exit();
    }
}
