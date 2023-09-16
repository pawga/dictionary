package ru.zenit.dictionary.common.model

import jakarta.validation.constraints.Size

/**
 * Created by pawga777
 */
data class Dictionary(
    @Size(max = 255) val name: String,
    val values: List<DictionaryValue>,
)
