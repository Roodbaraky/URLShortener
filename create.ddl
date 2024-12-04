
    create sequence T_URL_SEQ start with 1 increment by 50;

    create table T_URL (
        id bigint not null,
        rawURL varchar(255),
        shortURL varchar(255),
        primary key (id)
    );
