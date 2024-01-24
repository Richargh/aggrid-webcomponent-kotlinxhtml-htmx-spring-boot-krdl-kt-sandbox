package de.richargh.sandbox.aggrid

import com.fasterxml.jackson.databind.ObjectMapper
import de.richargh.sandbox.aggrid.components.myAgGrid
import kotlinx.html.*
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class AthleteGridController(
    private val mapper: ObjectMapper
) {
    @GetMapping("/")
    fun index() = html(BasePage.render {
        h1 { +"Athlete Ag Grid Demo" }

        div {
            attributes["hx-boost"] = "true"
            a(href = "/") { +"Athlete Grid   " }
            a(href = "/food") { +"   Food Grid" }
        }
        br { }

        myAgGrid {
            attributes["src"] = "/data"

            script(type = "application/json") {
                attributes["id"] = "gridData"
                unsafe {
                    raw(
                        mapper.writeValueAsString(
                            CollectionDto(
                                allAthletes.subList(0, 10),
                                0,
                                10,
                                allAthletes.size
                            )
                        )
                    )
                }
            }

            script(type = "application/json") {
                attributes["id"] = "gridOptions"
                unsafe {
                    raw(
                        mapper.writeValueAsString(
                            AgGridOptions(
                                columnDefs = listOf(
                                    AgColumDef(AthleteDto::athlete.name, minWidth = 220),
                                    AgColumDef(AthleteDto::country.name, minWidth = 200),
                                    AgColumDef(AthleteDto::year.name),
                                    AgColumDef(AthleteDto::sport.name, minWidth = 200),
                                    AgColumDef(AthleteDto::gold.name),
                                    AgColumDef(AthleteDto::silver.name),
                                    AgColumDef(AthleteDto::bronze.name),
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
    @GetMapping("/data")
    fun data(startRow: Int = 0, endRow: Int = 10) = CollectionDto(
        allAthletes.subList(startRow, endRow),
        startRow,
        endRow,
        allAthletes.size
    )
}

private val allAthletes = listOf(
    AthleteDto("A1", "United One", 2001, "Swimming", 8, 0, 0),
    AthleteDto("A2", "United Two", 2002, "Swimming", 6, 0, 2),
    AthleteDto("A3", "United Three", 2003, "Swimming", 4, 2, 2),
    AthleteDto("A4", "United Four", 2004, "Swimming", 4, 2, 2),
    AthleteDto("A5", "United Five", 2004, "Swimming", 4, 2, 2),

    AthleteDto("B1", "United One", 2001, "Swimming", 8, 0, 0),
    AthleteDto("B2", "United Two", 2002, "Swimming", 6, 0, 2),
    AthleteDto("B3", "United Three", 2003, "Swimming", 4, 2, 2),
    AthleteDto("B4", "United Four", 2004, "Swimming", 4, 2, 2),
    AthleteDto("B5", "United Five", 2004, "Swimming", 4, 2, 2),

    AthleteDto("C1", "United One", 2001, "Swimming", 8, 0, 0),
    AthleteDto("C2", "United Two", 2002, "Swimming", 6, 0, 2),
    AthleteDto("C3", "United Three", 2003, "Swimming", 4, 2, 2),
    AthleteDto("C4", "United Four", 2004, "Swimming", 4, 2, 2),
    AthleteDto("C5", "United Five", 2004, "Swimming", 4, 2, 2),

    AthleteDto("D1", "United One", 2001, "Swimming", 8, 0, 0),
    AthleteDto("D2", "United Two", 2002, "Swimming", 6, 0, 2),
    AthleteDto("D3", "United Three", 2003, "Swimming", 4, 2, 2),
    AthleteDto("D4", "United Four", 2004, "Swimming", 4, 2, 2),
    AthleteDto("D5", "United Five", 2004, "Swimming", 4, 2, 2),
)