package ru.zenit.dictionary

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@OpenAPIDefinition(info = Info(title = "API 'Сервис работы со справочниками'", version = "1.0", description = "API 'Сервис работы со справочниками'"))
class DictionaryApplication

fun main(args: Array<String>) {
    runApplication<DictionaryApplication>(*args)
}
