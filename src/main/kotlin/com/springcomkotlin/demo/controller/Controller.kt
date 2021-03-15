package com.springcomkotlin.demo.controller

import com.springcomkotlin.demo.entities.Contato
import com.springcomkotlin.demo.repository.ContatoRepository
import com.springcomkotlin.demo.service.ContatoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.persistence.EntityNotFoundException

@RestController
@RequestMapping("/contatos")
class Controller {

    @Autowired
    lateinit var servico: ContatoService

    @PostMapping
    fun criaContato(@RequestBody contato: Contato) : Contato {
        return servico.salvaContato(contato)
    }

    @GetMapping
    fun obterTodosOsContatos() : List<Contato?> {
        return servico.buscarTodos()
    }

    @PutMapping
    fun atualizaContato(@RequestBody novoContato: Contato): Contato {
        return servico.atualizaContato(novoContato)
    }

    @DeleteMapping("/{id}")
    fun deletaContato(@PathVariable id: Long) : String {
        return servico.deletaContato(id)
    }

    @GetMapping("/{id}")
    fun obterContatoPorId(@PathVariable id: Long) : Contato {
        return servico.buscarPorId(id)
    }




}