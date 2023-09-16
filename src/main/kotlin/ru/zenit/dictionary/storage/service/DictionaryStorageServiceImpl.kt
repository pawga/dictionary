package ru.zenit.dictionary.storage.service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import ru.zenit.dictionary.common.model.DictionaryDTO
import ru.zenit.dictionary.common.service.DictionaryStorageService
import ru.zenit.dictionary.common.service.DictionaryValueStorageService
import ru.zenit.dictionary.storage.extensions.toDb
import ru.zenit.dictionary.storage.extensions.toResponse
import ru.zenit.dictionary.storage.repository.DictionaryDbRepository

/**
 * Created by pawga777
 */
@Service
class DictionaryStorageServiceImpl(
    private val repository: DictionaryDbRepository,
    private val valueService: DictionaryValueStorageService,
) : DictionaryStorageService {
    override suspend fun findByName(name: String): Flow<DictionaryDTO> = repository.findByName(name).map { it.toResponse(valueService) }
    override suspend fun findAll(): Flow<DictionaryDTO> = repository.findAll().map { it.toResponse(valueService) }
    override suspend fun get(id: Long): DictionaryDTO? = repository.findById(id)?.toResponse(valueService)
    override suspend fun create(obj: DictionaryDTO): DictionaryDTO? = repository.save(obj.toDb()).toResponse(valueService)

    override suspend fun delete(id: Long): Boolean {
        repository.findById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Dictionary with id $id was not found.")
        repository.deleteById(id)
        return true
    }

    override suspend fun update(id: Long, obj: DictionaryDTO): DictionaryDTO? {
        val foundOb = repository.findById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Dictionary with id $id was not found.")
        return repository.save(
            obj.toDb().copy(id = foundOb.id)
        ).toResponse(valueService)
    }
}