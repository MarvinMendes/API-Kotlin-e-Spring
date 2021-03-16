package com.springcomkotlin.demo.exceptions

import javax.persistence.PersistenceException

class ContatoException(mensagem: String) : PersistenceException(mensagem) {

}