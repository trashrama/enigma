create database enigma;
use enigma;

create trigger tg_add_post after insert on curso for each row 
	INSERT INTO atividade (tipo, data_atvd, id_usuario) VALUES ("p_curso", NOW(), NEW.id);

create trigger tg_add_comentario after insert on comentario for each row 
	INSERT INTO atividade (tipo, data_atvd, id_usuario) VALUES ("comentario", NOW(), NEW.id);


create table usuario(
	id bigint auto_increment not null primary key,
    nome varchar(100) not null,
    lvl_usuario int not null default 0,
    xp bigint not null default 0,
    data_nasc DATETIME not null,
    eh_prof boolean default false not null,
    login varchar(50) not null unique,
    senha varchar(50) not null
);

create table categorias_curso (
	id int not null auto_increment primary key,
    nome varchar(30) not null unique
);

create table curso (
	id int not null auto_increment primary key, 
    titulo varchar(30) not null,
    id_instrutor bigint not null,
    data_curso DATETIME not null,
    foreign key (id_instrutor) references usuario(id)
);

create table cursos_categorias(
	id_curso int not null,
    id_categoria int not null,
    
    primary key (id_curso, id_categoria)
);

create table atividade(

	id int not null auto_increment primary key,
    tipo ENUM('comentario', 'post', 'ver_aula', 'r_exercicio', 'p_aula', 'p_curso', 'p_exercicio'),
	data_atvd date not null,
    id_usuario bigint not null,
    foreign key (id_usuario) references usuario(id)
);

create table post (
	id int not null auto_increment primary key,
    titulo varchar(50) not null,
	conteudo TEXT,
    aula longblob,
    id_autor bigint not null,
    eh_aula boolean default false,
	foreign key (id_autor) references usuario(id)
);

create table comentario_post(
	id int not null auto_increment primary key,
	id_post int not null,
    id_usuario bigint not null,

    conteudo varchar(250) not null,
    
    eh_resposta boolean DEFAULT false,
    id_resposta int,
    
	foreign key (id_usuario) references usuario(id),
    foreign key (id_post) references post(id),
    foreign key (id_resposta) references comentario_post(id)
    

	
);


