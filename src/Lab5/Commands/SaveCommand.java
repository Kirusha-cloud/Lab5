package Lab5.Commands;

import Lab5.ProgrammOrg.CommandBase;

import java.io.IOException;
/**
 * Класс, реализующий выполнение команды save.
 */
public class SaveCommand implements Command {
    private CommandBase commandBase;

    public SaveCommand(CommandBase commandBase) {
        this.commandBase = commandBase;
    }

    @Override
    public void execute(String [] args) throws IOException {
        commandBase.save();
    }
}
