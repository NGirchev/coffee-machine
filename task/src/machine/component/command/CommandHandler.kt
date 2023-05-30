package machine.component.command

import machine.util.KBean

/**
 *
 * @author ngirchev@gmail.com on 29.05.2023.
 */
interface CommandHandler<T : Command> : KBean {

    fun support(type: T): Boolean
    fun action()
}

interface Command