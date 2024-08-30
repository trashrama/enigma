create database enigma;
use enigma;


create table usuario(
	id bigint auto_increment not null primary key,
    nome varchar(100) not null,
    lvl_usuario int not null default 0,
    xp bigint not null default 0,
    data_nasc DATETIME not null,
    eh_instrutor boolean default false not null,
    login varchar(50) not null unique,
    senha varchar(50) not null
);

create table categoria_c (
	id int not null auto_increment primary key,
    nome varchar(30) not null unique
);

create table curso (
	id int not null auto_increment primary key, 
    titulo varchar(30) not null,
    id_instrutor bigint,
    data_curso DATETIME not null,
    foreign key (id_instrutor) references usuario(id) ON DELETE SET NULL
);

create table curso_aluno (
	id_aluno int not null,
    id_curso int not null,
    
    primary key(id_aluno,id_curso),
    foreign key (id_aluno) references usuario(id),
	foreign key (id_curso) references curso(id)

	ON DELETE CASCADE
	ON UPDATE CASCADE
);

create table cursos_categorias(
	id_curso int not null auto_increment,
    id_categoria int not null,
    
    primary key (id_curso, id_categoria),
    foreign key(id_curso) references curso(id) ON DELETE CASCADE,
	foreign key(id_categoria) references categoria_c(id)
	ON DELETE CASCADE
	ON UPDATE CASCADE

);

create table atividade(

	id int not null auto_increment primary key,
    tipo ENUM('ver_aula', 'r_exercicio', 'p_aula', 'p_curso', 'p_exercicio'),
	data_atvd date not null,
    id_usuario bigint not null,
    foreign key (id_usuario) references usuario(id)
	ON DELETE CASCADE
	ON UPDATE CASCADE
);


create table post (
	id int not null auto_increment primary key,
    titulo varchar(50) not null,
	conteudo TEXT,
    aula longblob,
    id_curso int not null,
    eh_aula boolean default false,
	foreign key (id_curso) references curso(id) ON DELETE CASCADE
);

create table questao(
	id int not null auto_increment primary key,
    a varchar(30) not null,
	b varchar(30) not null,
    c varchar(30) not null,
    d varchar(30) not null,
	id_resp int not null	
    
);



create table exame(
	id_post int not null,
    id_questao int not null,
    posicao int not null,
    primary key (id_post, id_questao),
    foreign key (id_post) references post(id),
	foreign key (id_questao) references questao(id)
    
);

create table exame_aluno(
	id_aluno bigint not null,
    id_exame int not null,
    id_questao int not null,
	alternativa int not null,
    
	primary key (id_aluno, id_exame, id_questao, alternativa),
	foreign key (id_exame) references post(id),
	foreign key (id_aluno) references usuario(id),
    foreign key (id_questao) references questao(id)

);

DELIMITER //

CREATE TRIGGER tg_add_aula AFTER INSERT ON post 
FOR EACH ROW 
BEGIN
    IF NEW.eh_aula = false THEN
        INSERT INTO atividade (tipo, data_atvd, id_usuario) 
        VALUES ('p_exercicio', NOW(), NEW.id_autor);
    ELSE
        INSERT INTO atividade (tipo, data_atvd, id_usuario) 
        VALUES ('p_aula', NOW(), NEW.id_autor);
    END IF;
END //
DELIMITER ;

create trigger tg_add_curso after insert on curso for each row 
	INSERT INTO atividade (tipo, data_atvd, id_usuario) VALUES ("p_curso", NOW(), NEW.id_instrutor);
    



INSERT INTO categoria_c (nome) VALUES ('Programação');
INSERT INTO categoria_c (nome) VALUES ('Design Gráfico');
INSERT INTO categoria_c (nome) VALUES ('Marketing Digital');
INSERT INTO categoria_c (nome) VALUES ('Finanças Pessoais');
INSERT INTO categoria_c (nome) VALUES ('Desenvolvimento Web');
INSERT INTO categoria_c (nome) VALUES ('Inteligência Artificial');
INSERT INTO categoria_c (nome) VALUES ('Análise de Dados');
INSERT INTO categoria_c (nome) VALUES ('Fotografia');
INSERT INTO categoria_c (nome) VALUES ('Edição de Vídeo');
INSERT INTO categoria_c (nome) VALUES ('Gestão de Projetos');
INSERT INTO categoria_c (nome) VALUES ('Liderança e Gestão');
INSERT INTO categoria_c (nome) VALUES ('Idiomas');
INSERT INTO categoria_c (nome) VALUES ('Saúde e Bem-Estar');
INSERT INTO categoria_c (nome) VALUES ('Desenvolvimento Pessoal');
INSERT INTO categoria_c (nome) VALUES ('Redes e Segurança');
INSERT INTO categoria_c (nome) VALUES ('Desenvolvimento de Jogos');
INSERT INTO categoria_c (nome) VALUES ('UX/UI Design');
INSERT INTO categoria_c (nome) VALUES ('Negócios e Empreendedorismo');
INSERT INTO categoria_c (nome) VALUES ('Marketing de Conteúdo');
INSERT INTO categoria_c (nome) VALUES ('Mídias Sociais');
INSERT INTO categoria_c (nome) VALUES ('Produção Musical');
INSERT INTO categoria_c (nome) VALUES ('Economia');
INSERT INTO categoria_c (nome) VALUES ('Ciência de Dados');
INSERT INTO categoria_c (nome) VALUES ('Arquitetura');
INSERT INTO categoria_c (nome) VALUES ('Engenharia de Software');
INSERT INTO categoria_c (nome) VALUES ('Desenvolvimento Móvel');
INSERT INTO categoria_c (nome) VALUES ('Robótica');
INSERT INTO categoria_c (nome) VALUES ('Escrita Criativa');
INSERT INTO categoria_c (nome) VALUES ('Educação Infantil');
INSERT INTO categoria_c (nome) VALUES ('Culinária');

use enigma;
select * from curso;
select cc.id_categoria, c.nome from cursos_categorias cc inner join categoria_c c on cc.id_categoria = c.id where cc.id_curso = 1;

drop database enigma;