package ru.zenit.dictionary.common.model

import jakarta.validation.constraints.Size

/**
 * Created by pawga777
 */
data class DictionaryValue(
    @Size(max = 80) val code: String,
    @Size(max = 255) val value: String,
)
