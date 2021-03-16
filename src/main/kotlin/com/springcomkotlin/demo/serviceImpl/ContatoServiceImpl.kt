package com.springcomkotlin.demo.serviceImpl

import com.springcomkotlin.demo.entities.Contato
import com.springcomkotlin.demo.entities.Endereco
import com.springcomkotlin.demo.exceptions.ContatoException
import com.springcomkotlin.demo.repository.ContatoRepository
import com.springcomkotlin.demo.repository.EnderecoRepository
import com.springcomkotlin.demo.service.ContatoService
import org.hibernate.persister.walking.spi.EntityIdentifierDefinition
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class ContatoServiceImpl : ContatoService {

    @Autowired
    lateinit var repositorio: ContatoRepository
    @Autowired
    lateinit var enderecoRepositorio: EnderecoRepository

    override fun salvaContato(contato: Contato): Contato {
        if (validaContato(contato)) {
           enderecoRepositorio.save(contato.endereco)
           return repositorio.save(contato)
        }
        throw ContatoException("O email ${contato.email}, já está cadastrado em nossa base de dados")
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

    //atualiza contato recebendo o Objeto no corpo da requisição
    override fun atualizaContato(novoContato: Contato): Contato {
        var contato =  repositorio.findById(novoContato.id).orElseThrow{ EntityNotFoundException() }
        contato.apply {
            this.nome = novoContato.nome
            this.email = novoContato.email
            atualizaEndereco(novoContato.endereco)
        }
        return repositorio.save(contato)
    }

    /*
    ao criar um contato este método define por regra que o e-mail deve ser único
    */
    private fun validaContato(contato: Contato) : Boolean {
        var todosContatos = buscarTodos()
        var filtro = todosContatos.filter { it -> it?.email.equals(contato.email) }
        return filtro.isEmpty()
    }


    /*
    sempre que for atualizar um contato deve-se passar o Objeto completo
    por isso este método foi criado para atualizar o Objeto Endereço
     */
    private fun atualizaEndereco(novoEndereco: Endereco) {
        var endereco = enderecoRepositorio.findById(novoEndereco.id).orElseThrow { ContatoException(mensagem = "Ops! Endereço não encontrado.") }
        endereco.apply {
            this.estado = novoEndereco.estado
            this.cidade = novoEndereco.cidade
            this.rua = novoEndereco.rua
            this.numero = novoEndereco.numero
        }
        enderecoRepositorio.save(endereco)
    }
}