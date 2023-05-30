package machine.component

import machine.domain.CoffeeNames
import machine.domain.Recipe
import machine.util.LookupService

class CoffeeMachineV1(
    private var waterContainer: WaterContainer,
    private var grinder: Grinder,
    private var milkContainer: MilkContainer,
    private var moneyContainer: MoneyContainer,
    private var cupContainer: CupContainer
) : Program {

    override fun makeCoffee(recipe: Recipe) {
        if (!waterContainer.hasEnough(recipe)){
            println("Sorry, not enough water!")
            LookupService.find(DialogMenu::class).start()
            return
        }
        if (!grinder.hasEnough(recipe)) {
            println("Sorry, not enough coffee!")
            LookupService.find(DialogMenu::class).start()
            return
        }
        if (!milkContainer.hasEnough(recipe)){
            println("Sorry, not enough milk!")
            LookupService.find(DialogMenu::class).start()
            return
        }
        println("I have enough resources, making you a coffee!")
        println()
        moneyContainer.transaction(recipe.price)

        val milling = grinder.takeVolume(CoffeeNames.STANDARD.name, recipe.coffee)
        val water = waterContainer.takeVolume(recipe.water)
        val milk = milkContainer.takeVolume(recipe.milk)
        mix(milling, water)
        addToCup()
        addMilk(milk)
        LookupService.find(DialogMenu::class).start()
    }

    private fun mix(millingCoffee: Int, water: Int) {
    }

    private fun addToCup() {
        cupContainer.takeOne()
    }

    private fun addMilk(milk: Int) {
    }
}