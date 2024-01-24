package de.richargh.sandbox.aggrid

data class AgGridOptions(
    val columnDefs: List<AgColumDef>,
    val defaultColDef: AgDefaultColDef,
    val pagination: Boolean,
    val paginationPageSizeSelector: List<Int>,
    val paginationPageSize: Int,
    val cacheBlockSize: Int,
    /** 'serverSide' **/
    val rowModelType: String
)

data class AgColumDef(
    val field: String, val minWidth: Int? = null
)

data class AgDefaultColDef(
    val flex: Int, val minWidth: Int
)