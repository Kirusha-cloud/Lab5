package Lab5.Commands;

import Lab5.ProgrammOrg.CommandBase;
/**
 * Класс, реализующий выполнение команды update.
 */
public class UpdateIdCommand implements Command {
    private CommandBase commandBase;

    public UpdateIdCommand(CommandBase commandBase) {
        this.commandBase = commandBase;
    }

    @Override
    public void execute(String[] args) {
        commandBase.update(args);
    }
}
