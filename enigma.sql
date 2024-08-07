create database enigma;
use enigma;

create table usuario(
	id bigint auto_increment not null primary key,
    nome varchar(100) not null,
    level_usuario int not null default 0,
    xp bigint not null default 0,
    data_nasc DATE not null,
    eh_professor boolean default false not null,
    login varchar(50),
    senha varchar(50)
);

create table categorias (
	id int not null auto_increment,
    nome varchar(30) not null
);

create table curso (
	id int not null auto_increment, 
    titulo varchar(30) not null,
    id_instrutor int not null,
    
    foreign key (id_instrutor) references usuario(id)
);

create table cursos_categorias(
	id_curso int not null;
    id_categoria int not null;
    
    primary key (id_curso, id_categoria)
);

create table atividade(

	id int not null auto_increment,
    tipo ENUM('comentario', 'post', 'ver_aula', 'r_exercicio', 'p_aula', 'p_curso', 'p_exercicio'),
	data_atvd date not null,
    id_usuario int not null,
    foreign key (id_usuario) references usuario(id)
);

create table post (
	id int not null auto_increment,
    titulo varchar(50) not null,
	conteudo TEXT,
    aula longblob,
    id_autor int not null,
	foreign key (id_autor) references usuario(id)
);

create table comentario_post(
	id int not null auto_increment,
	id_post int not null,
    id_usuario int not null,
    
    eh_resposta boolean DEFAULT false,
    id_resposta int not null,
    
	foreign key (id_usuario) references usuario(id),
    foreign key (id_post) references post(id),
    foreign key (id_resposta) references resposta(id)
	
);

