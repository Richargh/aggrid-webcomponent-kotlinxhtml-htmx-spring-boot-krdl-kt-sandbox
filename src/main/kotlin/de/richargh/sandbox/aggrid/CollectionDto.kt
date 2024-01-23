package de.richargh.sandbox.aggrid

data class CollectionDto<Data>(
    val rows: Collection<Data>,
    val total: Int
)