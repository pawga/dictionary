### Пример реализации реактивной неблокируеющей реализации сервисов REST API With Spring, Kotlin and Coroutines c нативной компиляцией результата и внешней БД PostgreS

### Основные допущения
* Применять JPA - нельзя. 
* Для работы с БД используется CoroutineCrudRepository.
* Для RestController используется "обычный" подход, а не декларативный. 
Под "декларативным" подходом понимаю реализация на coRouter, где в конфигурации и handlers 
описываются "end points". 
* Базу данных нужно "готовить" отдельно за пределами программы: создание таблиц, ключей и так далее. 
Скрипты для работы с БД лежат в папке sql_scripts проекта.
* Проект в конфигурации "многомодульный" при нативной сборке не работает. Поэтому элементы-слои "чистой
архитектуры" разнесены по соответствующим пакетам. Все остальные требования "чистой архитектуры"- в силе и реализованы.
Так как нет JPA и аннотаций "один - ко многим", "многие ко многим" то эта функциональность  
реализована соответствущими @Query.

Примеры разных запросов в описании CoroutineCrudRepository:
```
@Query("SELECT * FROM public.locale where id in (:codeList)")
    fun findByCodeList(@Param("codeList") codeList: List<Long>): Flow<LocaleDb>
    
    
 @Query(
        """
        select 
            ST.id,
            ST.description,
            ST.image,
            ST.name,
            ST.status,
            ST.locale_db_id
        from public.table1 as ST
        inner join public.table1s_and_types as TP
        on TP.table1_id = ST.id
        inner join public.table1_aaaa as BP
        on BP.table1_id = ST.id
        where 
            ST.status = 'WORK'
            and TP.table1_type_id in (:typeList)
            and ST.locale_db_id in (:localeList)
            and BP.bitrate BETWEEN :lowValue AND :highValue
    """
    )
    fun findtable1s(@Param("typeList") typeList: List<Long>,
                     @Param("localeList") localeList: List<Long>,
                     @Param("lowValue") lowValue: Int,
                     @Param("highValue") highValue: Int,
                     ): Flow<table1Db>
    

```


### Требования
- GraalVM installed and set as environment variable `JAVA_HOME` (sdkman easiest variant)
- native-image installed (use `gu`) and reachable
- cм docker-compose.yml для настроек

### Шаги
- Build using `./gradlew bootBuildImage --imageName=pawga777/r2-dictionary:1.0.0  `
- Run using `docker-compose up -d`

### Дополнительно
Можно докер-образ скачать с docker-hub. Имя образа pawga777/r2-dictionary:1.0.0.
Для работы с докер-образом необходимо создать свою БД, настройки прописать в docker-compose.yml.

### Доступные общие "end points"
Так как доступен OpenAPI, то посмотреть сервисы можно по ссылке http://localhost:8080/swagger-ui/index.html#/
Actuator также включен в сборку.
