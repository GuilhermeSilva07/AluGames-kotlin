package br.com.alura.alugames.principal

import br.com.alura.alugames.modelo.Periodo
import br.com.alura.alugames.modelo.planoAssinatura
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
    val periodo4 = Periodo(LocalDate.of(2023,8,2), LocalDate.of(2023,8,15))

    gamerGuilherme.alugaJogo(jogoResidentVillage, periodo1)
    gamerGuilherme.alugaJogo(Spider, periodo2)
    gamerGuilherme.alugaJogo(TheLast, periodo3)
    //println(gamerGuilherme.JogosAlugados)
    gamerGuilherme.alugaJogo(Spider, periodo4)

    val gamerCamila = ListaGamers.get(5)
    gamerCamila.plano = planoAssinatura("PRATA", 9.90, 3, 0.15)

    gamerCamila.alugaJogo(jogoResidentVillage, periodo1)
    gamerCamila.alugaJogo(Spider, periodo2)
    gamerCamila.alugaJogo(TheLast, periodo3)
    gamerCamila.alugaJogo(TheLast, periodo3)
    println(gamerCamila.JogosAlugados)

    gamerCamila.recomendar(7)
    gamerCamila.recomendar(10)
    gamerCamila.recomendar(10)
    println(gamerCamila)

    gamerCamila.alugaJogo(jogoResidentVillage, periodo1)
    println(gamerCamila.JogosAlugados)
}