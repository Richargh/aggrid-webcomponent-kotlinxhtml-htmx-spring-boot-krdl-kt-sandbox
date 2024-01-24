package de.richargh.sandbox.aggrid.components

import kotlinx.html.*

class MY_AG_GRID(initialAttributes: Map<String, String>, consumer: TagConsumer<*>) :
    HTMLTag(
        "my-ag-grid", consumer, initialAttributes,
        inlineTag = true,
        emptyTag = false
    ), HtmlInlineTag

@HtmlTagMarker
inline fun FlowContent.myAgGrid(classes: String? = null, crossinline block: MY_AG_GRID.() -> Unit = {}): Unit =
    MY_AG_GRID(attributesMapOf("class", classes), consumer).visit(block)
