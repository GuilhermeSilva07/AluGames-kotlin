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
    val Spider = ListaJogoJson.get(13)
    val TheLast = ListaJogoJson.get(2)

//    println(gamerGuilherme)
//    println(jogoResidentVillage)

    val periodo1 = Periodo(LocalDate.now(), LocalDate.now().plusDays(7))
    val periodo2 = Periodo(LocalDate.now(), LocalDate.now().plusDays(3))
    val periodo3 = Periodo(LocalDate.now(), LocalDate.now().plusDays(10))

    gamerGuilherme.alugaJogo(jogoResidentVillage, periodo1)
    gamerGuilherme.alugaJogo(Spider, periodo2)
    gamerGuilherme.alugaJogo(TheLast, periodo3)
    println(gamerGuilherme.JogosAlugados)
}