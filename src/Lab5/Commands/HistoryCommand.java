package Lab5.Commands;

import Lab5.ProgrammOrg.CommandBase;
/**
 * Класс, реализующий выполнение команды history.
 */
public class HistoryCommand implements Command {
    private CommandBase commandBase;

    public HistoryCommand(CommandBase commandBase) {
        this.commandBase = commandBase;
    }

    @Override
    public void execute(String[] args) {
        commandBase.history();
    }
}
