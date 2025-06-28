package br.com.alura.alugames.principal

import br.com.alura.alugames.modelo.Periodo
import br.com.alura.alugames.serviços.ConsumoApi
import java.time.LocalDate

fun main (){

    val consumo = ConsumoApi()
    val ListaGamers = consumo.buscaGamer()
    val ListaJogoJson = consumo.buscaJogosJson()
    //val jogoApi = consumo.buscaJogo("144")

    //println(ListaGamers)
    //println(jogoApi)

    val gamerGuilherme = ListaGamers.get(2)
    val jogoResidentVillage = ListaJogoJson.get(10)

    println(gamerGuilherme)
    println(jogoResidentVillage)

    val periodo = Periodo(LocalDate.now(), LocalDate.now().plusDays(7))

    val aluguel = gamerGuilherme.alugaJogo(jogoResidentVillage, periodo)
    println(aluguel)
}