package ru.zenit.dictionary.common.service

import kotlinx.coroutines.flow.Flow
import ru.zenit.dictionary.common.model.DictionaryValueDTO

/**
 * Created by pawga777
 */
interface DictionaryValueStorageService : StorageService<DictionaryValueDTO, Long> {
    fun findAllByDictionaryId(id: Long): Flow<DictionaryValueDTO>
    fun findByCodeAndDictionaryId(code: String, dictionaryId: Long): Flow<DictionaryValueDTO>
}