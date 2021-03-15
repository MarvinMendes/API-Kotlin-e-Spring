package com.springcomkotlin.demo.repository

import com.springcomkotlin.demo.entities.Contato
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContatoRepository : JpaRepository<Contato, Long> {
}