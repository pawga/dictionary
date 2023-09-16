package ru.zenit.dictionary.storage.service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.stereotype.Service
import ru.zenit.dictionary.common.model.DictionaryValueDTO
import ru.zenit.dictionary.common.service.DictionaryValueStorageService
import ru.zenit.dictionary.storage.extensions.toDb
import ru.zenit.dictionary.storage.extensions.toResponse
import ru.zenit.dictionary.storage.repository.DictionaryValueDbRepository

/**
 * Created by pawga777
 */
@Service
class DictionaryValueStorageServiceImpl(private val repository: DictionaryValueDbRepository) : DictionaryValueStorageService {
    override fun findAllByDictionaryId(id: Long): Flow<DictionaryValueDTO> = repository.findAllByDictionaryId(id).map { it.toResponse() }
    override fun findByCodeAndDictionaryId(code: String, dictionaryId: Long): Flow<DictionaryValueDTO> = repository.findByCodeAndDictionaryId(code, dictionaryId).map { it.toResponse() }
    override suspend fun findAll(): Flow<DictionaryValueDTO> = repository.findAll().map { it.toResponse() }
    override suspend fun create(obj: DictionaryValueDTO): DictionaryValueDTO? = repository.save(obj.toDb()).toResponse()
    override suspend fun get(id: Long): DictionaryValueDTO? = repository.findById(id)?.toResponse()

    override suspend fun update(id: Long, obj: DictionaryValueDTO): DictionaryValueDTO? {
        val origin = repository.findById(id) ?: return null
        val updateOb = obj.toDb().copy(id = origin.id, dictionaryId = origin.dictionaryId)
        return repository.save(updateOb).toResponse()
    }

    override suspend fun delete(id: Long): Boolean {
        repository.deleteById(id)
        return true
    }
}