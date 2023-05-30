package machine.component

import machine.domain.Recipe
import machine.util.KBean
import java.math.BigDecimal

interface Part : KBean

interface Program : Part {
    fun makeCoffee(recipe: Recipe)
}

const val ONE_COFFEE_CONTAINER: Int = 1

class Grinder : Part {
    val coffeeAmount: MutableMap<String, Int> = HashMap()

    fun fill(coffeeName: String, amount: Int) {
        if (!this.coffeeAmount.containsKey(coffeeName)
            && this.coffeeAmount.size == ONE_COFFEE_CONTAINER) throw IllegalArgumentException("Too much coffee kinds")
        val currentAmount = this.coffeeAmount[coffeeName] ?: 0
        this.coffeeAmount[coffeeName] = currentAmount + amount
    }

    fun hasEnough(recipe: Recipe): Boolean {
        return this.coffeeAmount.values.sum() >= recipe.coffee
    }

    fun takeVolume(coffeeName: String, amount: Int): Int {
        val coffeeAmount = this.coffeeAmount[coffeeName] ?: 0
        if (amount > coffeeAmount) {
            val rest = coffeeAmount
            this.coffeeAmount[coffeeName] = 0
            return rest
        }
        this.coffeeAmount[coffeeName] = coffeeAmount - amount
        return amount
    }
}

interface LiquidContainer : Part {
    var volume: Int
    fun fill(volume: Int) {
        this.volume += volume
    }

    fun takeVolume(volume: Int): Int {
        if (volume > this.volume) {
            val rest = this.volume
            this.volume = 0
            return rest
        }
        this.volume = this.volume - volume
        return volume
    }
}

class WaterContainer(override var volume: Int = 0) : LiquidContainer {
    fun hasEnough(recipe: Recipe): Boolean {
        return volume >= recipe.water
    }
}

class MilkContainer(override var volume: Int = 0) : LiquidContainer {
    fun hasEnough(recipe: Recipe): Boolean {
        return volume >= recipe.milk
    }
}

class MoneyContainer(var amount: BigDecimal = BigDecimal.ZERO) : Part {
    fun transaction(deal: BigDecimal) {
        this.amount += deal
    }

    fun takeAll() {
        amount = BigDecimal(0)
    }
}

class CupContainer(var count: Int = 0) : Part {
    fun replenish(count: Int) {
        this.count += count
    }

    fun takeOne() {
        this.count--
    }
}