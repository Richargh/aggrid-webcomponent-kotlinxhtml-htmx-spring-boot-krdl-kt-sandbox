package de.richargh.sandbox.aggrid

import kotlinx.html.*
import kotlinx.html.stream.appendHTML

class BasePage {
    companion object {
        fun render(content: MAIN.() -> Unit): String {
            return buildString {
                appendHTML(xhtmlCompatible = true).html {
                    head {
                        meta { charset = "utf-8" }
                        meta { name = "viewport"; this.content = "width=device-width, initial-scale=1" }
                        link(rel = "stylesheet", href = "/ag-grid-enterprise/ag-grid.min.css")
                        link(rel = "stylesheet", href = "/ag-grid-enterprise/ag-theme-quartz.min.css")
                        link(rel = "stylesheet", href = "/ag-theme-custom.css")
                        title { +"AgGrid Sandbox" }
                        script(type = "importmap") {
                            unsafe {
                                raw(
                                    """
                                {
                                    "imports": {
                                        "ag-grid-enterprise": "/ag-grid-enterprise/ag-grid-enterprise.esm.min.js"
                                    }
                                }
                            """.trimIndent()
                                )
                            }
                        }
                    }
                    body {
                        main {
                            content()
                        }
                    }
                }
            }
        }
    }
}