package sh.miles.voidcr.codegen.parse

import sh.miles.voidcr.collection.SingleSet
import java.io.Reader

fun skipTillSig(reader: Reader, sigEnd: Int) {
    var peek: Int
    while (reader.read().also { peek = it } != sigEnd) {
        if (peek == -1) {
            throw IllegalArgumentException("reader terminates prior to reaching signal end $sigEnd")
        }
    }
}

fun <T, C> collectTillSig(
    reader: Reader,
    sigEnd: Int,
    mapper: (Int) -> T,
    collectorProvider: () -> C,
    collecting: (C, T) -> Unit
): C {
    return collectTillAnySig(reader, SingleSet(sigEnd), mapper, collectorProvider, collecting, false)
}

fun <T, C> collectTillAnySig(
    reader: Reader,
    sigEnds: Set<Int>,
    mapper: (Int) -> T,
    collectorProvider: () -> C,
    collecting: (C, T) -> Unit,
    includeTerminator: Boolean
): C {
    val collector = collectorProvider()
    var peek: Int
    while (!sigEnds.contains(reader.read().also { peek = it })) {
        if (peek == -1) {
            throw IllegalArgumentException("reader terminates prior to reaching any signal end $sigEnds")
        }

        collecting(collector, mapper(peek))
    }

    if (includeTerminator) {
        collecting(collector, mapper(peek))
    }

    return collector
}

