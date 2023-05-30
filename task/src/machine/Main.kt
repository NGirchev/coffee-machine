package machine

import machine.component.*
import machine.component.DialogMenu
import machine.component.command.buy_coffee.handlers.BackDialog
import machine.component.command.buy_coffee.handlers.CappuccinoHandler
import machine.component.command.buy_coffee.handlers.EspressoHandler
import machine.component.command.buy_coffee.handlers.LatteHandler
import machine.component.command.main_menu.handlers.*
import machine.domain.CoffeeNames
import machine.util.LookupService
import java.math.BigDecimal

fun main() {
    initializeBeans()
    LookupService.find(DialogMenu::class).start()
}

fun initializeBeans() {
    val stdWaterContainer = WaterContainer(400)
    val stdGrinder = Grinder()
    stdGrinder.fill(CoffeeNames.STANDARD.name, 120)
    val stdMilkContainer = MilkContainer(540)
    val stdMoneyContainer = MoneyContainer(BigDecimal(550))
    val stdCupContainer = CupContainer(9)

    LookupService.register(stdWaterContainer)
    LookupService.register(stdGrinder)
    LookupService.register(stdMilkContainer)

    LookupService.register(stdMoneyContainer)
    LookupService.register(stdCupContainer)

    val coffeeMachineV1 = CoffeeMachineV1(
        stdWaterContainer,
        stdGrinder,
        stdMilkContainer,
        stdMoneyContainer,
        stdCupContainer
    )
    LookupService.register(coffeeMachineV1)

    val backDialog = BackDialog()
    val cappuccinoHandler = CappuccinoHandler(coffeeMachineV1)
    val latteHandler = LatteHandler(coffeeMachineV1)
    val espressoHandler = EspressoHandler(coffeeMachineV1)

    LookupService.register(backDialog)
    LookupService.register(cappuccinoHandler)
    LookupService.register(latteHandler)
    LookupService.register(espressoHandler)

    val buyActionDialog = BuyActionDialog(
        listOf(backDialog, cappuccinoHandler, latteHandler, espressoHandler)
    )
    val fillActionDialog = FillActionDialog()
    val takeActionDialog = TakeActionDialog()
    val remainingDialog = RemainingDialog()
    val exitActionDialog = ExitActionDialog()

    LookupService.register(buyActionDialog)
    LookupService.register(fillActionDialog)
    LookupService.register(takeActionDialog)
    LookupService.register(remainingDialog)
    LookupService.register(exitActionDialog)

    LookupService.register(
        DialogMenu(
            listOf(buyActionDialog, fillActionDialog, takeActionDialog, remainingDialog, exitActionDialog)
        )
    )
}