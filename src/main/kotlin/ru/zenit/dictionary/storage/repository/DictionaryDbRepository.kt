package ru.zenit.dictionary.storage.repository

import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import ru.zenit.dictionary.storage.domain.DictionaryDb

/**
 * Created by pawga777
 */
interface DictionaryDbRepository : CoroutineCrudRepository<DictionaryDb, Long> {
    fun findByName(name: String): Flow<DictionaryDb>
}