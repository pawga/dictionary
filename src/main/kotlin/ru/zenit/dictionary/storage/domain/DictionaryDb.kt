package ru.zenit.dictionary.storage.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

/**
 * Created by pawga777
 */
@Table(name = "dictionary")
data class DictionaryDb(
    @Id val id: Long? = null,
    val name: String
)
