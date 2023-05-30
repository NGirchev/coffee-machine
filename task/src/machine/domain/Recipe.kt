package machine.domain

import java.math.BigDecimal

/**
 *
 * @author ngirchev@gmail.com on 25.05.2023.
 */
interface Recipe {
    val water: Int
    val milk: Int
    val coffee: Int
    val price: BigDecimal
}

val CAPPUCCINO = object : Recipe {
    override val water: Int = 200
    override val milk: Int = 100
    override val coffee: Int = 12
    override val price: BigDecimal = BigDecimal(6)
}

val LATTE = object : Recipe {
    override val water: Int = 350
    override val milk: Int = 75
    override val coffee: Int = 20
    override val price: BigDecimal = BigDecimal(7)
}

val ESPRESSO = object : Recipe {
    override val water: Int = 250
    override val milk: Int = 0
    override val coffee: Int = 16
    override val price: BigDecimal = BigDecimal(4)
}

enum class CoffeeNames {
    STANDARD
}