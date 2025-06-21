package br.com.alura.alugames.principal

import br.com.alura.alugames.serviços.ConsumoApi

fun main (){

    val consumo = ConsumoApi()
    val ListaGamers = consumo.buscaGamer()

    println(ListaGamers)
}