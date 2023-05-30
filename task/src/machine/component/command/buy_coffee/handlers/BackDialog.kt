package machine.component.command.buy_coffee.handlers

import machine.component.command.CommandHandler
import machine.component.DialogMenu
import machine.component.command.buy_coffee.BuyCoffeeCommand
import machine.util.LookupService

/**
 *
 * @author ngirchev@gmail.com on 29.05.2023.
 */
class BackDialog : CommandHandler<BuyCoffeeCommand> {

    override fun support(type: BuyCoffeeCommand): Boolean {
        return type == BuyCoffeeCommand.BACK
    }

    override fun action() {
        LookupService.find(DialogMenu::class).start()
    }
}