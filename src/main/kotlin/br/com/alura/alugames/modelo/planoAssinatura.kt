package br.com.alura.alugames.modelo

class planoAssinatura(
    tipo : String,
    val mensalidade: Double,
    val JogosIncluidos: Int,
    val percentualDescontoReputucao: Double): Plano(tipo){

    override fun obterValor(aluguel: Aluguel): Double {
        val totalJogosNoMes = aluguel.gamer.jogoDoMes(aluguel.periodo.dataInicial.monthValue).size+1

        return if (totalJogosNoMes <= JogosIncluidos){
            0.0
        }else{
           var valorOriginal = super.obterValor(aluguel)

            if (aluguel.gamer.media >8){
                valorOriginal -= valorOriginal * percentualDescontoReputucao
            }
            valorOriginal
        }
    }
}
