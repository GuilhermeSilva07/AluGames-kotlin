package br.com.alura.alugames.dados

import javax.persistence.Entity
import javax.persistence.EntityManager

/*
Classe abstrata genérica para acesso a dados.
<TModel>: Tipo do modelo (ex: Jogo).
<TEntity>: Tipo da entidade do banco (ex: JogoEntity).
*/
abstract class DAO <TModel, TEntity>(protected val manager: EntityManager, protected val entityType: Class<TEntity>) {

    // --- Métodos de Conversão (Implementados nas subclasses) ---
  abstract fun toEntity(objeto: TModel): TEntity  //Converte o modelo para a entidade do banco.
  abstract fun toModel(entity: TEntity): TModel  //Converte a entidade do banco para o modelo.


    // --- Métodos CRUD Básicos ---

  open fun getLista(): List<TModel>{  // Obtém todos os itens do banco como uma lista de modelos.
      val query = manager.createQuery("FROM ${entityType.simpleName}", entityType)
      return query.resultList.map { entity -> toModel(entity)}
  }

  open fun adicionar(objeto: TModel){ // Adiciona um novo objeto modelo ao banco.
      val entity = toEntity(objeto)
      manager.transaction.begin()
      manager.persist(entity)
      manager.transaction.commit()
  }

    open fun recuperarPeloId(id: Int): TModel{ // Recupera um modelo pelo ID.
        val query = manager.createQuery("FROM ${entityType.simpleName} WHERE id=:id", entityType)
        query.setParameter("id", id)
        val entity = query.singleResult

        return toModel(entity)
    }

    open fun apagar(id: Int) { // Apaga um item do banco pelo ID.
        val query = manager.createQuery("FROM ${entityType.simpleName} WHERE id=:id", entityType)
        query.setParameter("id", id)
        val entity = query.singleResult

        manager.transaction.begin()
        manager.remove(entity)
        manager.transaction.commit()
    }
}