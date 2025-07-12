package br.com.alura.alugames.modelo

import java.math.BigDecimal

class planoAssinatura(
    tipo: String,
    val mensalidade: Double,
    val JogosIncluidos: Int,
    val percentualDescontoReputucao: Double
): Plano(tipo){

    override fun obterValor(aluguel: Aluguel): BigDecimal {
        val totalJogosNoMes = aluguel.gamer.jogoDoMes(aluguel.periodo.dataInicial.monthValue).size+1

        return if (totalJogosNoMes <= JogosIncluidos){
            BigDecimal("0.0")
        }else{
           var valorOriginal = super.obterValor(aluguel)

            if (aluguel.gamer.media >8){
                valorOriginal -= valorOriginal.multiply(percentualDescontoReputucao)
            }
            valorOriginal
        }
    }
}
