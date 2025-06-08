data class Jogo(val titulo: String,val capa: String)  //Construtor
    {

    //Atributos do jogo

    /* Variaveis declaradas como "Var" permitem alteração nos valores inseridos" */

    var descricao:String? = null

    override fun toString(): String {
        return "Meu Jogo:\n" +
                "Titulo: $titulo \n" +
                "capa=$capa \n" +
                "descricao=$descricao \n"
    }


}