package machine.component.command.main_menu.handlers

import machine.component.*
import machine.component.command.CommandHandler
import machine.component.DialogMenu
import machine.component.command.main_menu.MainMenuCommand
import machine.util.LookupService

/**
 *
 * @author ngirchev@gmail.com on 29.05.2023.
 */
class RemainingDialog : CommandHandler<MainMenuCommand> {

    override fun support(type: MainMenuCommand): Boolean {
        return type == MainMenuCommand.REMAINING
    }

    override fun action() {
        println()
        println("The coffee machine has:")
        val water = LookupService.find(WaterContainer::class).volume
        println("$water ml of water")
        val milk = LookupService.find(MilkContainer::class).volume
        println("$milk ml of milk")
        val coffee = LookupService.find(Grinder::class).coffeeAmount.values.sum()
        println("$coffee g of coffee beans")
        val count = LookupService.find(CupContainer::class).count
        println("$count disposable cups")
        val amount = LookupService.find(MoneyContainer::class).amount
        println("\$${amount.intValueExact()} of money")
        println()
        LookupService.find(DialogMenu::class).start()
    }
}