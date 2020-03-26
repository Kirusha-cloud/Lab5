package Lab5.Commands;

import Lab5.ProgrammOrg.CommandBase;
/**
 * Класс, реализующий выполнение команды execute_script.
 */
public class ExecuteScriptCommand implements Command {
    private CommandBase commandBase;

    public ExecuteScriptCommand(CommandBase commandBase) {
        this.commandBase = commandBase;
    }

    @Override
    public void execute(String[] args) {
        commandBase.executeScript(args[1], commandBase.getPaths());
    }
}
