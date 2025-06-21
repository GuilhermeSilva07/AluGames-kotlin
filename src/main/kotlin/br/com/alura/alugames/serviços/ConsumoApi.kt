package br.com.alura.alugames.serviços

import br.com.alura.alugames.modelo.Gamer
import br.com.alura.alugames.modelo.InfoGamerJson
import br.com.alura.alugames.modelo.InfoJogo
import br.com.alura.alugames.modelo.Jogo
import br.com.alura.alugames.utilitario.CriaGamer
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

     private fun consumeDados(endereco: String): String {
        val client: HttpClient = HttpClient.newHttpClient()

        val request = HttpRequest.newBuilder()
            .uri(URI.create(endereco))
            .build()

        val response = client
            .send(request, HttpResponse.BodyHandlers.ofString())

        val jsonString = response.body()

        // --- Tratativa para JSON vazio --- //
        if (jsonString.trim() == "[]") {
            return "Tente outro id, jogo invalido"
        }

        return jsonString // Retorna o JSON real se não for vazio e nenhuma exceção ocorreu
    }


    fun buscaJogo(id: String):InfoJogo {
        val endereco = "https://www.cheapshark.com/api/1.0/games?id=$id"
        val json = consumeDados(endereco)

        val gson = Gson()
        val meuInfoJogo = gson.fromJson(json, InfoJogo::class.java)
        return meuInfoJogo
    }

    fun buscaGamer(): List<Gamer> {
        val endereco = "https://raw.githubusercontent.com/jeniblodev/arquivosJson/main/gamers.json"
        val json = consumeDados(endereco)

        val gson = Gson()
        val meuGamerTipo = object : TypeToken<List<InfoGamerJson>>() {}.type
        val ListaGamer: List<InfoGamerJson> = gson.fromJson(json, meuGamerTipo)

        val listaGamerConvertida = ListaGamer.map { infoGamerJson -> infoGamerJson.CriaGamer() }

        return listaGamerConvertida
    }
}