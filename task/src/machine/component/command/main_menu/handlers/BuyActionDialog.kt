package machine.component.command.main_menu.handlers

import jdk.jshell.spi.ExecutionControl
import machine.component.command.CommandHandler
import machine.component.command.buy_coffee.BuyCoffeeCommand
import machine.component.command.main_menu.MainMenuCommand
import java.lang.IllegalArgumentException
import java.util.*

/**
 *
 * @author ngirchev@gmail.com on 25.05.2023.
 */
class BuyActionDialog(handlerList: List<CommandHandler<BuyCoffeeCommand>>) : CommandHandler<MainMenuCommand> {

    private val handlers: MutableMap<BuyCoffeeCommand, CommandHandler<out BuyCoffeeCommand>> =
        EnumMap(BuyCoffeeCommand::class.java)

    init {
        for (command in BuyCoffeeCommand.values()) {
            val commandHandler = handlerList.find { it.support(command) }
                ?: throw IllegalArgumentException("Should be presented all handlers for all commands. Not found for $command")
            commandHandler.let { this.handlers[command] = it }
        }
    }

    override fun support(type: MainMenuCommand): Boolean {
        return type == MainMenuCommand.BUY
    }

    override fun action() {
        val commands = BuyCoffeeCommand.values().map { r -> r.toString() }.toList().toString()
        println()
        println("What do you want to buy? ${commands.substring(1, commands.length - 1)}:")
        val command = BuyCoffeeCommand.parse(readln())
        val handler = handlers[command] ?: throw ExecutionControl.NotImplementedException("$command is not supported")
        handler.action()
    }
}