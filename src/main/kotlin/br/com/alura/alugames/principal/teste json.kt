package br.com.alura.alugames.principal

import br.com.alura.alugames.serviços.ConsumoApi

fun main (){

    val consumo = ConsumoApi()
    val ListaGamers = consumo.buscaGamer()
    val jogoApi = consumo.buscaJogo("144")

    println(ListaGamers)
    println(jogoApi)
}