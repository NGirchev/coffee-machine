package machine.component.command.buy_coffee

import machine.component.command.Command

/**
 *
 * @author ngirchev@gmail.com on 28.05.2023.
 */
enum class BuyCoffeeCommand(private val n: Int? = null, val isCoffeeType: Boolean? = true) : Command {
    ESPRESSO(1), LATTE(2), CAPPUCCINO(3),
    BACK(isCoffeeType = false);

    override fun toString(): String {
        return if (isCoffeeType == true) "$n - ${name.lowercase()}" else name.lowercase() + " - to main menu"
    }

    companion object {
        fun parse(arg: String) : BuyCoffeeCommand {
            return values().first {
                arg.equals(
                    if (it.isCoffeeType == true) it.n.toString() else it.name,
                    ignoreCase = true
                )
            }
        }
    }
}