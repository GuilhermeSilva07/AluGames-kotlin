package br.com.alura.alugames.principal

import br.com.alura.alugames.modelo.Periodo
import br.com.alura.alugames.modelo.planoAssinatura
import br.com.alura.alugames.serviços.ConsumoApi
import com.google.gson.GsonBuilder
import java.io.File
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
//    println(gamerCamila.JogosAlugados)

    gamerCamila.recomendar(7)
    gamerCamila.recomendar(10)
    gamerCamila.recomendar(10)
//    println(gamerCamila)

    gamerCamila.alugaJogo(jogoResidentVillage, periodo1)
//    println(gamerCamila.JogosAlugados)

//    gamerCamila.recomendarJogo(jogoResidentVillage, 7)
//    gamerCamila.recomendarJogo(TheLast, 10)
//
//    gamerGuilherme.recomendarJogo(jogoResidentVillage, 8)
//    gamerGuilherme.recomendarJogo(TheLast, 9)
//
//    println("Recomendaçoes Camila")
//    println(gamerCamila.JogosRecomendados)
//    println("Recomendaçoes Guilherme")
//    println(gamerGuilherme.JogosRecomendados)

    val gamerCaroline = ListaGamers.get(3)
    val jogoSpider = ListaJogoJson.get(13)
    val jogoTheLastOfUs = ListaJogoJson.get(2)
    val jogoDandara = ListaJogoJson.get(5)
    val jogoAssassins = ListaJogoJson.get(4)
    val jogoCyber = ListaJogoJson.get(6)
    val jogoGod = ListaJogoJson.get(7)
    val jogoSkyrim = ListaJogoJson.get(18)

    gamerCamila.recomendarJogo(jogoResidentVillage, 7)
    gamerCamila.recomendarJogo(jogoTheLastOfUs, 10)
    gamerCamila.recomendarJogo(jogoAssassins, 8)
    gamerCamila.recomendarJogo(jogoCyber, 7)
    gamerCamila.recomendarJogo(jogoGod, 10)
    gamerCamila.recomendarJogo(jogoDandara, 8)
    gamerCamila.recomendarJogo(jogoSkyrim, 8)
    gamerCamila.recomendarJogo(jogoSpider, 6)

    val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    val serializacao = gson.toJson(gamerCamila.JogosRecomendados)
    println(serializacao)

    val arquivo = File("JogosRecomendados-${gamerCamila.nome}.json")
    arquivo.writeText(serializacao)
    println(arquivo.absolutePath)

}