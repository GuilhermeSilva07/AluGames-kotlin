package br.com.alura.alugames.modelo

import formatoComDuasCasasDecimais
import java.lang.IllegalArgumentException
import java.time.LocalDate
import java.util.Scanner
import kotlin.random.Random

data class Gamer(var nome:String, var email:String): Recomendavel {
    var dataNascimento:String? = null
    var usuario:String? = null
        set(value) {
            field = value
            if (idInterno.isNullOrBlank()){
                criarIdInterno()
            }
        }
    var idInterno:String? = null
        private set
    var plano: Plano = PlanoAvulso("BRONZE")
    val jogosBuscados = mutableListOf<Jogo?>()
    val JogosAlugados = mutableListOf<Aluguel>()
    private val ListaNotas = mutableListOf<Int>()
    val JogosRecomendados = mutableListOf<Jogo>()

    override val media: Double
        get() = ListaNotas.average().formatoComDuasCasasDecimais()

    override fun recomendar(nota: Int) {
        if (nota < 1 || nota > 10) {
            println("Nota inválida. Insira uma nota entre 1 e 10")
        } else {
            ListaNotas.add(nota)
        }
    }

    fun recomendarJogo(Jogo: Jogo, nota: Int){
        Jogo.recomendar(nota)
        JogosRecomendados.add(Jogo)
    }

    constructor(nome: String, email: String, dataNascimento:String, usuario:String) :
            this(nome, email){
                this.dataNascimento = dataNascimento
                this.usuario = usuario
                criarIdInterno()
            }
    init {
        if (nome.isNullOrBlank()){
            throw IllegalArgumentException("Nome inválido")
        }
        this.email = validarEmail()
    }

    override fun toString(): String {
        return "Gamer:\n" +
                "Nome: $nome\n" +
                "Email: $email\n" +
                "Data Nascimento: $dataNascimento\n" +
                "Usuario: $usuario\n" +
                "IdInterno: $idInterno\n" +
                "Reputação: $media"
    }

    fun criarIdInterno(){
        val numero = Random.nextInt(10000)
        val tag = String.format("%04d", numero)

        idInterno = "$usuario#$tag"
    }

    fun validarEmail(): String{
        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        if (regex.matches(email)){
            return email
        }else {
            throw IllegalArgumentException("Email inválido")
        }
    }

    fun alugaJogo(jogo: Jogo, periodo: Periodo): Aluguel{
        val aluguel = Aluguel(this,jogo, periodo)
        JogosAlugados.add(aluguel)

        return aluguel
    }

    fun jogoDoMes(mes:Int): List<Jogo>{
        return JogosAlugados
            .filter { aluguel -> aluguel.periodo.dataInicial.monthValue == mes }
            .map { aluguel -> aluguel.jogo }
    }

    companion object {
        fun criarGamer(leitura: Scanner): Gamer {
            println("Boas vindas ao AluGames! Vamos fazer seu cadastro. Digite seu nome:")
            val nome = leitura.nextLine()
            println("Digite seu e-mail:")
            val email = leitura.nextLine()
            println("Deseja completar seu cadastro com usuário e data de nascimento? (S/N)")
            val opcao = leitura.nextLine()

            if (opcao.equals("s", true)) {
                println("Digite sua data de nascimento(DD/MM/AAAA):")
                val nascimento = leitura.nextLine()
                println("Digite seu nome de usuário:")
                val usuario = leitura.nextLine()

                return Gamer(nome, email, nascimento, usuario)
            } else {
                return Gamer (nome, email)
            }
        }
    }
}


