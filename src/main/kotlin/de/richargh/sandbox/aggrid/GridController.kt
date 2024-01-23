package de.richargh.sandbox.aggrid

import kotlinx.html.*
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class GridController {
    @GetMapping("/")
    fun index() = html(BasePage.render {
        h1 { +"Ag Grid Demo" }
        div(classes = "ag-theme-quartz") {
            style = "height: 100%"
            id = "myGrid"
        }
        script(type = "text/javascript", src = "/app/js/main.js") {
            defer = true
        }
    })
}