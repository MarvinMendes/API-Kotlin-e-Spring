package com.springcomkotlin.demo.entities

import com.springcomkotlin.demo.entities.enums.Estado
import javax.persistence.*

@Entity
@Table(name = "tb_endereco")
class Endereco(@Id
               @GeneratedValue(strategy =  GenerationType.SEQUENCE)
               val id: Long) {

    @Enumerated
    lateinit var estado: Estado
    lateinit var cidade: String
    lateinit var rua: String
    lateinit var numero: String

}