package Lab5.Commands;

import Lab5.ProgrammOrg.CommandBase;
/**
 * Класс, реализующий выполнение команды min_by_furniture.
 */
public class MinByFurnitureCommand implements Command {
    private CommandBase commandBase;

    public MinByFurnitureCommand(CommandBase commandBase) {
        this.commandBase = commandBase;
    }

    @Override
    public void execute(String[] args) {
        commandBase.minByFurniture();
    }
}
