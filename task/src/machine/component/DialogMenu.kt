package machine.component

import jdk.jshell.spi.ExecutionControl.NotImplementedException
import machine.component.command.CommandHandler
import machine.component.command.main_menu.MainMenuCommand
import machine.util.KBean
import java.util.*

/**
 *
 * @author ngirchev@gmail.com on 25.05.2023.
 */
class DialogMenu(handlerList: List<CommandHandler<MainMenuCommand>>) : KBean {

    private val handlers: MutableMap<MainMenuCommand, CommandHandler<out MainMenuCommand>> =
        EnumMap(MainMenuCommand::class.java)

    init {
        for (command in MainMenuCommand.values()) {
            val commandHandler = handlerList.find { it.support(command) }
                ?: throw IllegalArgumentException("Should be presented all handlers for all commands. Not found for $command")
            commandHandler.let { this.handlers[command] = it }
        }
    }

    fun start() {
        val commands = MainMenuCommand.values().map { r -> r.name.lowercase() }.toList().toString()
        println("Write action (${commands.substring(1, commands.length - 1)}): ")
        val command = MainMenuCommand.valueOf(readln().uppercase(Locale.getDefault()))
        val handler = handlers[command] ?: throw NotImplementedException("$command is not supported")
        handler.action()
    }
}