package Lab5.Commands;

import Lab5.ProgrammOrg.CommandBase;
/**
 * Класс, реализующий выполнение команды remove_by_id.
 */
public class RemoveByIdCommand implements Command {
    private CommandBase commandBase;

    public RemoveByIdCommand(CommandBase commandBase) {
        this.commandBase = commandBase;
    }

    @Override
    public void execute(String[] args) {
        Integer a = Integer.parseInt(args[1]);
        commandBase.removeById(args);
    }
}
