package ru.zenit.dictionary.storage.repository

import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import ru.zenit.dictionary.storage.domain.DictionaryValueDb

/**
 * Created by pawga777
 */
interface DictionaryValueDbRepository: CoroutineCrudRepository<DictionaryValueDb, Long> {
    fun findAllByDictionaryId(id: Long): Flow<DictionaryValueDb>
    fun findByCodeAndDictionaryId(code: String, dictionaryId: Long): Flow<DictionaryValueDb>
}