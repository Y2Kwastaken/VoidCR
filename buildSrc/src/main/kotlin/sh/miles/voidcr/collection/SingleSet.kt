package sh.miles.voidcr.collection

import java.util.Spliterator

class SingleSet<E>(private val value: E) : Set<E> {
    override val size: Int = 1

    override fun contains(element: E): Boolean {
        return element == this.value
    }

    override fun containsAll(elements: Collection<E>): Boolean {
        if (elements.size != 1) return false
        return elements.first() == this.value
    }

    override fun isEmpty(): Boolean {
        return false
    }

    override fun iterator(): Iterator<E> {
        throw IllegalArgumentException("Can not iterate over a single set!")
    }

    override fun spliterator(): Spliterator<E> {
        return super.spliterator()
    }
}
