package ru.zenit.dictionary.common.service

import kotlinx.coroutines.flow.Flow
import ru.zenit.dictionary.common.model.DictionaryDTO

/**
 * Created by pawga777
 */
interface DictionaryStorageService : StorageService<DictionaryDTO, Long> {
    suspend fun findByName(name: String): Flow<DictionaryDTO>
}