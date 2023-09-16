-- public.locale definition

-- Drop table

-- DROP TABLE public.locale;

create table if not exists public.dictionary
(
    id   serial       not null primary key,
    name varchar(255) not null,
    CONSTRAINT dictionary_unique_name UNIQUE (name)
);

create table if not exists public.dictionary_value
(
    id   serial       not null primary key,
    code varchar(80) not null,
    value varchar(255) not null,
    dictionary_id bigint not null references public.dictionary (id) on delete cascade on update cascade,
    CONSTRAINT dictionary_value_unique_name UNIQUE (id, code)
);

-- https://www.postgresqltutorial.com/postgresql-tutorial/postgresql-foreign-key/

ALTER TABLE public.dictionary_value
    ADD CONSTRAINT dictionary_value_unique_name UNIQUE (id, code);
ALTER TABLE public.dictionary_value
    DROP CONSTRAINT dictionary_value_unique_name;