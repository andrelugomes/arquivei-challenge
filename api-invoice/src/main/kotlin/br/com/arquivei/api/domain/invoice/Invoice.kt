package br.com.arquivei.api.domain.invoice

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "invoice")
class Invoice(
    @Id
    @Column(name = "access_key")
    @JsonProperty(value = "access_key" )
    var accessKey: String,

    @Column
    var value: Double
)

