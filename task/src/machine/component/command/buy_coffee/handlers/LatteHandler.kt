package machine.component.command.buy_coffee.handlers

import machine.component.Program
import machine.component.command.CommandHandler
import machine.component.command.buy_coffee.BuyCoffeeCommand

/**
 *
 * @author ngirchev@gmail.com on 29.05.2023.
 */
class LatteHandler(private val program: Program) : CommandHandler<BuyCoffeeCommand> {

    override fun support(type: BuyCoffeeCommand): Boolean {
        return type == BuyCoffeeCommand.LATTE
    }

    override fun action() {
        program.makeCoffee(machine.domain.LATTE)
    }
}