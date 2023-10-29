CREATE TABLE IF NOT EXISTS hits
(
    id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    app         VARCHAR(255) NOT NULL,
    uri         VARCHAR(255) NOT NULL,
    ipAddress   VARCHAR(16) NOT NULL,
    created     TIMESTAMP    NOT NULL
);