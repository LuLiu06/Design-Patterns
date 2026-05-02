package fi.metropolia.assignment22;

import fi.metropolia.assignment22.command.Command;

/**
 * Invoker: triggers commands without knowing their concrete implementation details.
 */
public class CommandInvoker {

    public void execute(Command command) {
        if (command != null) {
            command.execute();
        }
    }
}
