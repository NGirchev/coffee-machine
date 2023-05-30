package machine.util

import kotlin.reflect.KClass

/**
 *
 * @author ngirchev@gmail.com on 25.05.2023.
 */
class LookupService {
    companion object {
        private val beans: MutableMap<KClass<out KBean>, in KBean> = HashMap()

        fun <T : KBean> register(bean: T) {
            beans[bean.kClass] = bean
        }

        fun <T : KBean> find(kClass: KClass<T>): T {
            @Suppress("UNCHECKED_CAST")
            return beans[kClass] as T
        }
    }
}

interface KBean {
    val kClass: KClass<out KBean>
        get() = this::class
}