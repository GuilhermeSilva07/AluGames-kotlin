package br.com.alura.alugames.serviços

import br.com.alura.alugames.modelo.InfoJogo
import br.com.alura.alugames.modelo.Jogo
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class ConsumoApi {

    /**
     * Busca informações de um jogo na API CheapShark dado o seu ID.
     * Trata possíveis erros de sintaxe JSON usando runCatching.
     *
     * @param id O ID do jogo a ser buscado.
     * @return Um objeto Result que contém InfoJogo em caso de sucesso,
     * ou uma exceção (JsonSyntaxException ou outra) em caso de falha.
     */
    fun buscaJogo(id: String):InfoJogo {
        val endereco = "https://www.cheapshark.com/api/1.0/games?id=$id"

        val client: HttpClient = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create(endereco))
            .build()

        //Envia a requisição e obtém a resposta como string
        val response = client
            .send(request, HttpResponse.BodyHandlers.ofString())

        val json = response.body()


        if (json == "[]"){
            println("Digite outro ID, jogo não identificado")
        }else{
            val gson = Gson()
            val meuInfoJogo = gson.fromJson(json, InfoJogo::class.java)
            return meuInfoJogo
        }

        return TODO("Provide the return value")
    }
}