CREATE TABLE _USER
(
    USER_ID           UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    FIRST_NAME        VARCHAR(50),
    LAST_NAME         VARCHAR(50),
    EMAIL             VARCHAR(50) UNIQUE NOT NULL,
    PASSWORD          VARCHAR(255)       NOT NULL,
    ROLE              VARCHAR(25)        NOT NULL,
    REGISTRATION_DATE DATE,
    UPDATE_AT         DATE,
    ACTIVE            BOOLEAN
);
