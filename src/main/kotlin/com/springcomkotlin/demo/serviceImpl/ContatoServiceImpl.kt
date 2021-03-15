package com.springcomkotlin.demo.serviceImpl

import com.springcomkotlin.demo.entities.Contato
import com.springcomkotlin.demo.repository.ContatoRepository
import com.springcomkotlin.demo.service.ContatoService
import org.hibernate.persister.walking.spi.EntityIdentifierDefinition
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class ContatoServiceImpl : ContatoService {

    @Autowired
    lateinit var repositorio: ContatoRepository

    override fun salvaContato(contato: Contato): Contato {
        return repositorio.save(contato)
    }

    override fun deletaContato(id: Long): String {
        var contato = repositorio.findById(id).orElseThrow { EntityNotFoundException() }
        repositorio.delete(contato)
        return "O contato foi excluído com sucesso"
    }

    override fun buscarTodos(): List<Contato?> {
        return repositorio.findAll()
    }

    override fun buscarPorId(id: Long): Contato {
        return repositorio.findById(id).orElseThrow{ EntityNotFoundException() }
    }

    override fun atualizaContato(novoContato: Contato): Contato {
        var contato =  repositorio.findById(novoContato.id).orElseThrow{ EntityNotFoundException() }
        contato.apply {
            nome = novoContato.nome
            email = novoContato.email
        }
        salvaContato(contato)
        return contato
    }
}