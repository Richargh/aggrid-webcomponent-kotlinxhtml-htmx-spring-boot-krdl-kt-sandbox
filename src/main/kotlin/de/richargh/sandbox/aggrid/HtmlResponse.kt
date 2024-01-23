package de.richargh.sandbox.aggrid

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

fun html(body: String, status: HttpStatus = HttpStatus.OK): ResponseEntity<String> =
    ResponseEntity.status(status).contentType(MediaType.TEXT_HTML).body(body)
