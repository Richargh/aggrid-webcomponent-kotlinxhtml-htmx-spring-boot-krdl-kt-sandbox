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
                        title { +"AgGrid Sandbox" }
                        script(
                            type = "text/javascript",
                            src = "https://cdn.jsdelivr.net/npm/ag-grid-enterprise@31.0.2/dist/ag-grid-enterprise.min.js"
                        ) {
                            defer = true
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