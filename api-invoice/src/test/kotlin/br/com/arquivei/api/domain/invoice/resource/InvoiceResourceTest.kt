package br.com.arquivei.api.domain.invoice.resource

import br.com.arquivei.api.config.handlers.Details
import br.com.arquivei.api.domain.invoice.Invoice
import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner



@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InvoiceResourceTest {

    @LocalServerPort
    var port: Int? = null

    @Autowired
    lateinit var rest: TestRestTemplate

    @Test
    fun `should return a page of invoices`() {

        val response = rest.getForEntity<RestResponsePage<Invoice>>("http://localhost:$port/invoices")

        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        Assertions.assertThat(response.body!!.content).hasSize(3)
    }

    @Test
    fun `should return an invoice by access key`() {

        val accessKey = "32180105570714000825550010031977311099185510"
        val response = rest.getForEntity<Invoice>("http://localhost:$port/invoices/$accessKey")

        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        Assertions.assertThat(response.body!!.accessKey).isEqualTo(accessKey)
        Assertions.assertThat(response.body!!.value).isEqualTo(107.84)
    }

    @Test
    fun `should return a NOT FOUNT status for wrong access key`() {

        val accessKey = "WRONGACCESSKEY"
        val response = rest.getForEntity<Details>("http://localhost:$port/invoices/$accessKey")

        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
        Assertions.assertThat(response.body!!.message).isEqualToIgnoringCase("Resource not found!")
    }

}
