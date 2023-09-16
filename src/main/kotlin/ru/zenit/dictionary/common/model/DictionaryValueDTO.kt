package ru.zenit.dictionary.common.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by pawga777
 */
data class DictionaryValueDTO(
    val id: Long,
    val parentId: Long,
    @JsonProperty("dictionary_value")
    val dictionaryValue: DictionaryValue
)
