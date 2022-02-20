CREATE TABLE veiculo
(
    id        INT      NOT NULL AUTO_INCREMENT,
    veiculo   TEXT     NOT NULL,
    marca     TEXT     NOT NULL,
    ano       INT      NOT NULL,
    descricao TEXT     NOT NULL,
    vendido   BOOLEAN  NOT NULL,
    cor       TEXT     NOT NULL,
    created   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);