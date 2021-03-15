package com.springcomkotlin.demo.entities

import org.hibernate.validator.constraints.Length
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "tb_contatos")
class Contato (
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        val id: Long,
        @NotNull
        @Length(min = 3, max = 50)
        var nome: String,
        @Email
        @NotNull
        var email: String
        )
{
}