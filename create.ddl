
    create table T_URL (
        rawURL varchar(255) not null unique,
        shortURL varchar(255) not null unique,
        primary key (rawURL, shortURL)
    );
