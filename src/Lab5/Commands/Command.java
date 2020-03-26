package Lab5.Commands;

import java.io.IOException;
/**
 * Интерфейс, содержащий метод execute, который в дальнейшем будет переопределён каждой командой.
 */
public interface Command {
    void execute(String[] args) throws IOException;
}
