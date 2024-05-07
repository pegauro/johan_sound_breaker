CREATE TABLE tbl_instrumento(
	id_instrumento serial not null,
	nome varchar(255) not null,
	familia varchar(255) not null,
	marca varchar(255) not null,
	modelo varchar(255) not null,
	cor varchar(255) not null
);