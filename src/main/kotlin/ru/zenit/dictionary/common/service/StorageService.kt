package ru.zenit.dictionary.common.service

import kotlinx.coroutines.flow.Flow

/**
 * M - тип модели
 * K - тип "ключа"
 */
interface StorageService<M, K> {
    suspend fun findAll(): Flow<M>
    suspend fun create(obj: M): M?
    suspend fun get(id: K): M?
    suspend fun update(id: K, obj: M): M?
    suspend fun delete(id: K): Boolean
}
