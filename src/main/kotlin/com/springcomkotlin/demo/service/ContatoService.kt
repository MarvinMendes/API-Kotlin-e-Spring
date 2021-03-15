package com.springcomkotlin.demo.service

import com.springcomkotlin.demo.entities.Contato

interface ContatoService {
    fun salvaContato(contato: Contato) : Contato
    fun deletaContato(id: Long) : String
    fun buscarTodos() : List<Contato?>
    fun buscarPorId(id: Long) : Contato
    fun atualizaContato(novoContato: Contato) : Contato
}