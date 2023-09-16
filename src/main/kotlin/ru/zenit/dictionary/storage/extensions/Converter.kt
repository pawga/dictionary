package ru.zenit.dictionary.storage.extensions

import kotlinx.coroutines.flow.toList
import ru.zenit.dictionary.common.model.*
import ru.zenit.dictionary.common.service.DictionaryValueStorageService
import ru.zenit.dictionary.storage.domain.DictionaryDb
import ru.zenit.dictionary.storage.domain.DictionaryValueDb

/**
 * Created by pawga777
 */
fun ShortDictionary.toResponse(): DictionaryDTO = DictionaryDTO(0, name, emptyList())
fun DictionaryDTO.toDb(id: Long? = null): DictionaryDb =
    DictionaryDb(
        id = id,
        name = name,
    )

suspend fun DictionaryDb.toResponse(service: DictionaryValueStorageService): DictionaryDTO =
    DictionaryDTO(
        id = this.id!!,
        name = name,
        values = loadValues(this.id, service) // emptyList()
    )

fun DictionaryValueDTO.toDb(): DictionaryValueDb {

    return DictionaryValueDb(
        if (id == 0L) null else id,
        code = dictionaryValue.code,
        value = dictionaryValue.value,
        dictionaryId = parentId
    )
}

fun DictionaryValueDb.toResponse(): DictionaryValueDTO = DictionaryValueDTO(
    id = this.id ?: 0L,
    parentId = this.dictionaryId,
    dictionaryValue = DictionaryValue(code, value),
)

private suspend fun loadValues(id: Long, service: DictionaryValueStorageService): List<DictionaryValueDTO>
{
    return service.findAllByDictionaryId(id).toList()
}