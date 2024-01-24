package de.richargh.sandbox.aggrid

data class CollectionDto<Data>(
    val rows: Collection<Data>,
    val startRow: Int,
    val endRow: Int,
    val total: Int
)