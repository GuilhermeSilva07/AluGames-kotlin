package br.com.alura.alugames.dados

import br.com.alura.alugames.modelo.Jogo
import javax.persistence.EntityManager

class JogosDAO(val manager: EntityManager) {

    fun getJogos(): List<Jogo>{
            val query = manager.createQuery("FROM JogoEntity", JogoEntity::class.java)
            return query.resultList.map { entity -> Jogo(entity.titulo, entity.capa, entity.preco, entity.descricao, entity.id) }
    }

    fun adicionarJogo(Jogo: Jogo){
        val entity = JogoEntity(Jogo.titulo, Jogo.capa, Jogo.preco, Jogo.descricao)
        manager.transaction.begin()
        manager.persist(entity)
        manager.transaction.commit()
    }

}