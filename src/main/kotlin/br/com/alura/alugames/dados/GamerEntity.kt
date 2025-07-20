package br.com.alura.alugames.dados

import javax.persistence.*

@Entity
@Table(name = "gamers")
class GamerEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    val nome: String = "Nome do gamer",
    val email: String = "email@gmail.com",
    val dataNascimento: String? = null,
    val usuario: String? = null){

}

