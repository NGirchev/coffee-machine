package machine.component.command.main_menu.handlers

import machine.component.MoneyContainer
import machine.component.command.CommandHandler
import machine.component.DialogMenu
import machine.component.command.main_menu.MainMenuCommand
import machine.util.LookupService

/**
 *
 * @author ngirchev@gmail.com on 25.05.2023.
 */
class TakeActionDialog : CommandHandler<MainMenuCommand> {

    override fun support(type: MainMenuCommand): Boolean {
        return type == MainMenuCommand.TAKE
    }

    override fun action() {
        val amount = LookupService.find(MoneyContainer::class).amount.intValueExact()
        println("I gave you $amount")
        LookupService.find(MoneyContainer::class).takeAll()
        LookupService.find(DialogMenu::class).start()
    }
}