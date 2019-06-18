package br.com.arquivei.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApiInvoiceApplication

fun main(args: Array<String>) {
	runApplication<ApiInvoiceApplication>(*args)
}
