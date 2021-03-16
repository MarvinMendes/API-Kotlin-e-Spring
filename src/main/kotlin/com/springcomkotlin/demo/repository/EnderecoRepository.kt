package com.springcomkotlin.demo.repository

import com.springcomkotlin.demo.entities.Endereco
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EnderecoRepository : JpaRepository<Endereco, Long> {
}