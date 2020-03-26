package Lab5.Commands;

import Lab5.ProgrammOrg.CommandBase;
/**
 * Класс, реализующий выполнение команды group_counting_by_transport.
 */
public class GroupCountingByTransportCommand implements Command {
    private CommandBase commandBase;

    public GroupCountingByTransportCommand(CommandBase commandBase) {
        this.commandBase = commandBase;
    }

    @Override
    public void execute(String[] args) {
        commandBase.groupCountingByTransport();
    }
}
