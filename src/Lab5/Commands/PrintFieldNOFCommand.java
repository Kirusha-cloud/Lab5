package Lab5.Commands;

import Lab5.ProgrammOrg.CommandBase;
/**
 * Класс, реализующий выполнение команды print_field_NOF. (Number of Rooms)
 */
public class PrintFieldNOFCommand implements Command {
    private CommandBase commandBase;

    public PrintFieldNOFCommand(CommandBase commandBase) {
        this.commandBase = commandBase;
    }

    @Override
    public void execute(String[] args) {
        commandBase.printFieldNOF();
    }
}
