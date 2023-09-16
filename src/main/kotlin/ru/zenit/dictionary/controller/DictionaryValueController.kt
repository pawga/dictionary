package ru.zenit.dictionary.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import kotlinx.coroutines.flow.Flow
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import ru.zenit.dictionary.common.model.DictionaryValueDTO
import ru.zenit.dictionary.common.service.DictionaryValueStorageService

/**
 * Created by pawga777
 */
@RestController
@RequestMapping(
    value = ["/api/v1/values"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
@Tag(name = "Содержимое справочников", description = "Записи второго уровня")
class DictionaryValueController(
    private val dictionaryValueStorageService: DictionaryValueStorageService,
) {

    @Operation(summary = "Создать запись справочника")
    @PostMapping
    suspend fun create(@RequestBody dictionaryRequest: DictionaryValueDTO): DictionaryValueDTO =
        dictionaryValueStorageService.create(
            dictionaryRequest
        ) ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error during type creation.")

    @Operation(summary = "Найти запись справочника")
    @GetMapping("")
    suspend fun find(
        @RequestParam("code", required = true) code: String,
        @RequestParam("parent", required = true) parentId: Long,
    ): Flow<DictionaryValueDTO> {
        return dictionaryValueStorageService.findByCodeAndDictionaryId(code, parentId)
    }

    @Operation(summary = "Найти запись справочника по ID")
    @GetMapping("/{id}")
    suspend fun findById(
        @PathVariable id: Long
    ): DictionaryValueDTO? =
        dictionaryValueStorageService.get(id)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Dictionary with id $id was not found.")

    @Operation(summary = "Удалить запись справочника по ID")
    @DeleteMapping("/{id}")
    suspend fun deleteById(
        @PathVariable id: Long
    ) {
        dictionaryValueStorageService.delete(id)
    }

    @Operation(summary = "Обновить запись справочника")
    @PutMapping("")
    suspend fun update(
        @RequestBody request: DictionaryValueDTO
    ): DictionaryValueDTO =
        dictionaryValueStorageService.update(
            request.id,
            request
        ) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "StationType with id ${request.id} was not updated.")
}