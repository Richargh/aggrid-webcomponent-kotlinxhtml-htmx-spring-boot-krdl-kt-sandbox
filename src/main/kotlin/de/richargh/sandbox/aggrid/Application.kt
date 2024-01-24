package de.richargh.sandbox.aggrid

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.support.beans

@EnableAutoConfiguration
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args) {
		addInitializers(beans {
			bean<AthleteGridController>()
			bean<FoodGridController>()
			bean<WebConfig>()
		})
	}
}
