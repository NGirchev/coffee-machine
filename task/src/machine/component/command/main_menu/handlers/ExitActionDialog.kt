package machine.component.command.main_menu.handlers

import machine.component.command.CommandHandler
import machine.component.command.main_menu.MainMenuCommand

/**
 *
 * @author ngirchev@gmail.com on 25.05.2023.
 */
class ExitActionDialog : CommandHandler<MainMenuCommand> {

    override fun support(type: MainMenuCommand): Boolean {
        return type == MainMenuCommand.EXIT
    }

    override fun action() {
        return
    }
}