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

    val gamerCaroline = ListaGamers.get(3)
    val jogoResidentVillage = ListaJogoJson.get(10)
    val jogoSpider = ListaJogoJson.get(13)
    val jogoTheLastOfUs = ListaJogoJson.get(2)
    val jogoDandara = ListaJogoJson.get(5)
    val jogoAssassins = ListaJogoJson.get(4)
    val jogoCyber = ListaJogoJson.get(6)
    val jogoGod = ListaJogoJson.get(7)
    val jogoSkyrim = ListaJogoJson.get(18)


    val periodo1 = Periodo(LocalDate.now(), LocalDate.now().plusDays(7))
    val periodo2 = Periodo(LocalDate.now(), LocalDate.now().plusDays(3))
    val periodo3 = Periodo(LocalDate.now(), LocalDate.now().plusDays(10))
    val periodo4 = Periodo(LocalDate.of(2023,8,2), LocalDate.of(2023,8,15))

    gamerCaroline.alugaJogo(jogoResidentVillage, periodo1)
    gamerCaroline.alugaJogo(jogoSpider, periodo2)
    gamerCaroline.alugaJogo(jogoTheLastOfUs, periodo3)

    val gamerCamila = ListaGamers.get(5)
    gamerCamila.plano = planoAssinatura("PRATA", 9.90, 3, 0.15)

    gamerCamila.alugaJogo(jogoResidentVillage, periodo1)
    gamerCamila.alugaJogo(jogoSpider, periodo2)
    gamerCamila.alugaJogo(jogoTheLastOfUs, periodo3)
    gamerCamila.alugaJogo(jogoTheLastOfUs, periodo3)


    gamerCamila.recomendar(7)
    gamerCamila.recomendar(10)
    gamerCamila.recomendar(10)


    gamerCamila.alugaJogo(jogoResidentVillage, periodo1)


    gamerCamila.recomendarJogo(jogoResidentVillage, 7)
    gamerCamila.recomendarJogo(jogoTheLastOfUs, 10)

    gamerCaroline.recomendarJogo(jogoResidentVillage, 8)
    gamerCaroline.recomendarJogo(jogoTheLastOfUs, 9)

    println("Recomendaçoes Camila")
    println(gamerCamila.JogosRecomendados)
    println("Recomendaçoes caroline")
    println(gamerCaroline.JogosRecomendados)


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