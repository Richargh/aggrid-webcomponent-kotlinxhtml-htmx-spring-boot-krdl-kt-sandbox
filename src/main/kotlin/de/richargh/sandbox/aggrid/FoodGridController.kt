package de.richargh.sandbox.aggrid

import com.fasterxml.jackson.databind.ObjectMapper
import de.richargh.sandbox.aggrid.components.myAgGrid
import kotlinx.html.*
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class FoodGridController(
    private val mapper: ObjectMapper
) {
    @GetMapping("/food")
    fun index() = html(BasePage.render {
        h1 { +"Food Ag Grid Demo" }

        div {
            attributes["hx-boost"] = "true"
            a(href = "/") { +"Athlete Grid   " }
            a(href = "/food") { +"   Food Grid" }
        }
        br { }

        myAgGrid {
            attributes["src"] = "/food/data"
            script(type = "application/json") {
                attributes["id"] = "gridData"
                unsafe { raw(mapper.writeValueAsString(CollectionDto(allFoods.subList(0, 10), 0, 10, allFoods.size))) }
            }

            script(type = "application/json") {
                attributes["id"] = "gridOptions"
                unsafe {
                    raw(
                        mapper.writeValueAsString(
                            AgGridOptions(
                                columnDefs = listOf(
                                    AgColumDef(FoodDto::name.name, minWidth = 220),
                                    AgColumDef(FoodDto::price.name, minWidth = 220),
                                    AgColumDef(FoodDto::year.name, minWidth = 220)
                                ),
                                defaultColDef = AgDefaultColDef(
                                    flex = 1,
                                    minWidth = 100
                                ),

                                pagination = true,
                                paginationPageSizeSelector = listOf(5, 10, 20),
                                paginationPageSize = 5,
                                cacheBlockSize = 10,

                                rowModelType = "serverSide"
                            )
                        )
                    )
                }
            }
        }
        script(type = "module", src = "/app/components/my-ag-grid.js") {}
    })

    @ResponseBody
    @GetMapping("/food/data")
    fun data(startRow: Int = 0, endRow: Int = 10) = CollectionDto(
        allFoods.subList(startRow, endRow),
        startRow,
        endRow,
        allFoods.size
    )
}

private val allFoods = listOf(
    FoodDto("A1", 1.0, 2001),
    FoodDto("A2", 2.0, 2002),
    FoodDto("A3", 3.0, 2003),
    FoodDto("A4", 4.0, 2004),
    FoodDto("A5", 5.0, 2005),

    FoodDto("B1", 1.0, 2001),
    FoodDto("B2", 2.0, 2002),
    FoodDto("B3", 3.0, 2003),
    FoodDto("B4", 4.0, 2004),
    FoodDto("B5", 5.0, 2005),

    FoodDto("C1", 1.0, 2001),
    FoodDto("C2", 2.0, 2002),
    FoodDto("C3", 3.0, 2003),
    FoodDto("C4", 4.0, 2004),
    FoodDto("C5", 5.0, 2005),

    FoodDto("D1", 1.0, 2001),
    FoodDto("D2", 2.0, 2002),
    FoodDto("D3", 3.0, 2003),
    FoodDto("D4", 4.0, 2004),
    FoodDto("D5", 5.0, 2005),
)