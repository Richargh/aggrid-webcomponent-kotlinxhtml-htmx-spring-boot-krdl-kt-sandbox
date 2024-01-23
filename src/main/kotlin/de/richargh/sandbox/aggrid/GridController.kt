package de.richargh.sandbox.aggrid

import de.richargh.sandbox.aggrid.components.div
import de.richargh.sandbox.aggrid.components.myAgGrid
import kotlinx.html.h1
import kotlinx.html.id
import kotlinx.html.script
import kotlinx.html.style
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class GridController {
    @GetMapping("/")
    fun index() = html(BasePage.render {
        h1 { +"Ag Grid Demo" }

        myAgGrid {
            div(classes = "ag-theme-quartz") {
                style = "height: 100%"
                id = "myGrid"
            }
        }
        script(type = "text/javascript", src = "/app/components/my-ag-grid.mjs") {
            defer = true
        }
    })

    @ResponseBody
    @GetMapping("/data")
    fun data(startRow: Int = 0, endRow: Int = 10) = CollectionDto(
        allAthletes.subList(startRow, endRow),
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