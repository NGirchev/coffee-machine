package machine.component.command.main_menu.handlers

import machine.component.CupContainer
import machine.component.Grinder
import machine.component.MilkContainer
import machine.component.WaterContainer
import machine.component.command.CommandHandler
import machine.component.DialogMenu
import machine.component.command.main_menu.MainMenuCommand
import machine.domain.CoffeeNames
import machine.util.LookupService

/**
 *
 * @author ngirchev@gmail.com on 25.05.2023.
 */
class FillActionDialog : CommandHandler<MainMenuCommand> {

    override fun support(type: MainMenuCommand): Boolean {
        return type == MainMenuCommand.FILL
    }

    override fun action() {
        askWater()
        askMilk()
        askCoffee()
        askCupCount()
        LookupService.find(DialogMenu::class).start()
    }

    private fun askWater() {
        println("Write how many ml of water you want to add:")
        LookupService.find(WaterContainer::class).fill(readln().toInt())
    }

    private fun askMilk() {
        println("Write how many ml of milk you want to add:")
        LookupService.find(MilkContainer::class).fill(readln().toInt())
    }

    private fun askCoffee() {
        println("Write how many grams of coffee beans you want to add:")
        LookupService.find(Grinder::class).fill(CoffeeNames.STANDARD.name, readln().toInt())
    }

    private fun askCupCount() {
        println("Write how many disposable cups you want to add:")
        LookupService.find(CupContainer::class).replenish(readln().toInt())
    }
}