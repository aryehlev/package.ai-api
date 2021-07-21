package com.packageai.packageai


import com.packageai.packageai.models.DataHandler
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.runApplication
import org.springframework.context.event.EventListener


@SpringBootApplication
class PackageaiApplication

fun main(args: Array<String>) {


	runApplication<PackageaiApplication>(*args)

}

