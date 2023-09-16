package ru.zenit.dictionary.common.model

import jakarta.validation.constraints.Size

/**
 * Created by pawga777
 */
data class DictionaryDTO(
    val id: Long,
    @Size(max = 255) val name: String,
    val values: List<DictionaryValueDTO>,
)
