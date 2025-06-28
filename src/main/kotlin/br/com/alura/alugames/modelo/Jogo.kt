package br.com.alura.alugames.modelo

data class Jogo(val titulo: String, val capa: String)  //Construtor
    {

    //Atributos do jogo

    /* Variaveis declaradas como "Var" permitem alteração nos valores inseridos" */

    var descricao:String? = null
    var preco = 0.0

    constructor(titulo: String, capa: String, preco: Double, descricao: String):
            this(titulo, capa){
                this.preco=preco
                this.descricao=descricao
            }
        data class Jogo(val titulo:String,
                        val capa:String) {
            var descricao: String? = null
            var preco = 0.0

            constructor(titulo: String, capa: String, preco: Double, descricao: String):
                    this(titulo, capa) {
                this.preco = preco
                this.descricao = descricao
            }

            override fun toString(): String {
                return "Meu Jogo: \n" +
                        "Título: $titulo \n" +
                        "Capa: $capa \n" +
                        "Preço: $preco" +
                        "Descricao: $descricao"
            }
        }




}