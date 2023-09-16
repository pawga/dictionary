package ru.zenit.dictionary.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import kotlinx.coroutines.flow.Flow
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import ru.zenit.dictionary.common.model.DictionaryDTO
import ru.zenit.dictionary.common.model.ShortDictionary
import ru.zenit.dictionary.common.service.DictionaryStorageService
import ru.zenit.dictionary.storage.extensions.toResponse

/**
 * Created by pawga777
 */
@RestController
@RequestMapping(
    value = ["/api/v1/dictionary"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
@Tag(name = "Справочники", description = "Записи верхнего уровня")
class DictionaryController(
    private val dictionaryStorageService: DictionaryStorageService,
) {

    @Operation(summary = "Создать запись справочника")
    @PostMapping
    suspend fun create(@RequestBody dictionaryRequest: ShortDictionary): DictionaryDTO =
        dictionaryStorageService.create(
            dictionaryRequest.toResponse()
        ) ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error during type creation.")

    @Operation(summary = "Найти запись справочника по имени или показать все записи")
    @GetMapping("/name")
    suspend fun find(
        @RequestParam("name", required = false) name: String?
    ): Flow<DictionaryDTO> {
        return name?.let { dictionaryStorageService.findByName(name) }
            ?: dictionaryStorageService.findAll()
    }

    @Operation(summary = "Найти справочник по ID")
    @GetMapping("/{id}")
    suspend fun findById(
        @PathVariable id: Long
    ): DictionaryDTO? =
        dictionaryStorageService.get(id)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Dictionary with id $id was not found.")

    @Operation(summary = "Удалить тип по ID")
    @DeleteMapping("/{id}")
    suspend fun deleteById(
        @PathVariable id: Long
    ) {
        dictionaryStorageService.delete(id)
    }

    @Operation(summary = "Обновить справочник")
    @PutMapping("/{id}")
    suspend fun update(
        @PathVariable id: Long,
        @RequestBody request: ShortDictionary
    ): DictionaryDTO =
        dictionaryStorageService.update(
            id,
            request.toResponse()
        ) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "StationType with id $id was not updated.")
}