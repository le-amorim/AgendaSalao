DROP DATABASE IF EXISTS SALAODB;
CREATE DATABASE SALAODB;
USE SALAODB;

CREATE TABLE CLIENTE(
IDCLIENTE INT PRIMARY KEY NOT NULL AUTO_INCREMENT
,NOME VARCHAR (255) NOT NULL
,SOBRENOME VARCHAR(255) NOT NULL
,TELEFONE  VARCHAR(12) NOT NULL
, OBSERVACAO VARCHAR (255) NOT NULL
);

CREATE TABLE PROFISSIONAL(
IDPROFISSIONAL INT PRIMARY KEY NOT NULL AUTO_INCREMENT
,NOME VARCHAR(255) NOT NULL
,ESPECIALIDADE VARCHAR(255) NOT NULL
);

CREATE TABLE AGENDAMENTO(
IDAGENDAMENTO INT PRIMARY KEY NOT NULL AUTO_INCREMENT
,IDCLIENTE INT NOT NULL
,IDPROFISSIONAL INT NOT NULL
,DATACOMHORA TIMESTAMP NOT NULL
,SERVICO VARCHAR(255) NOT NULL
,VALOR DOUBLE NOT NULL
, FOREIGN KEY(IDCLIENTE) REFERENCES CLIENTE (IDCLIENTE)
, FOREIGN KEY(IDPROFISSIONAL) REFERENCES PROFISSIONAL (IDPROFISSIONAL)
);

insert into profissional (IDPROFISSIONAL, NOME,ESPECIALIDADE) values (1,'Leandro', 'DEV');
insert into profissional (IDPROFISSIONAL, NOME,ESPECIALIDADE) values (2,'Gabriel', 'Cinciencias Computação');

insert into cliente (IDCLIENTE, NOME,SOBRENOME,TELEFONE,OBSERVACAO) values (1,'JUANETE','Cristina','48999999999', 'LEGAL');
insert into cliente (IDCLIENTE, NOME,SOBRENOME,TELEFONE,OBSERVACAO) values (2,'MARIA','meuZovo ','48888888888 ','CHATA');



select * from cliente;
select * from profissional;
