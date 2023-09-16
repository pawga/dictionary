package ru.zenit.dictionary.storage.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

/**
 * Created by pawga777
 */
@Table(name = "dictionary_value")
data class DictionaryValueDb(
    @Id val id: Long? = null,
    val code: String,
    val value: String,
    @Column("dictionary_id")
    val dictionaryId: Long
)
