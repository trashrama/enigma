package com.projetosant.enigmafx;

import model.dao.DaoFactory;
import model.entities.Curso;
import model.entities.Usuario;
import utils.Validacao;

import java.time.Instant;
import java.time.LocalDate;
import java.util.*;


public class Main {


    private static void printCategorias(){
        String[] categorias = {
                "PROGRAMAÇÃO",
                "DESIGN GRÁFICO",
                "MARKETING DIGITAL",
                "FINANÇAS PESSOAIS",
                "DESENVOLVIMENTO WEB",
                "INTELIGÊNCIA ARTIFICIAL",
                "ANÁLISE DE DADOS",
                "FOTOGRAFIA",
                "EDIÇÃO DE VÍDEO",
                "GESTÃO DE PROJETOS",
                "LIDERANÇA E GESTÃO",
                "IDIOMAS",
                "SAÚDE E BEM-ESTAR",
                "DESENVOLVIMENTO PESSOAL",
                "REDES E SEGURANÇA",
                "DESENVOLVIMENTO DE JOGOS",
                "UX/UI DESIGN",
                "NEGÓCIOS E EMPREENDEDORISMO",
                "MARKETING DE CONTEÚDO",
                "MÍDIAS SOCIAIS",
                "PRODUÇÃO MUSICAL",
                "ECONOMIA",
                "CIÊNCIA DE DADOS",
                "ARQUITETURA",
                "ENGENHARIA DE SOFTWARE",
                "DESENVOLVIMENTO MÓVEL",
                "ROBÓTICA",
                "ESCRITA CRIATIVA",
                "EDUCAÇÃO INFANTIL",
                "CULINÁRIA"
        };

        for (int i = 0; i < categorias.length; i++) {
            System.out.println("[" + (i + 1) + "] - " + categorias[i]);
        }
        System.out.println("[0] TERMINAR");
    }

    private static void pula(){
        System.out.print("\n\n");
    }

    private static void listaCursos(){
        pula();
        for (Curso c: DaoFactory.createCursoDao().listar()){

            System.out.println(c.toString());
            System.out.println("--------");

        }


    }
    private static void cadastraCurso(){
        Scanner s = new Scanner(System.in);
        Scanner i = new Scanner(System.in);
        String titulo;
        int id_instrutor;
        Curso c = new Curso();
        int cont = 0;
        int opc;

        System.out.print("1. TITULO DO CURSO: ");
        titulo = s.nextLine();
        c.setTitulo(titulo);

        System.out.print("2. ID DO INSTRUTOR: ");
        id_instrutor = s.nextInt();


        // depois fazer uma verificao pra ver se o usuario retornado tem o ehInstrutor true
        while (DaoFactory.createUsuarioDao().pesquisarPorID(id_instrutor) == null){
            System.out.println("INSTRUTOR NÃO ENCONTRADO");
            System.out.print("2. ID DO INSTRUTOR: ");
            id_instrutor = s.nextInt();
        }
        c.setId_instrutor(id_instrutor);

        do {
            printCategorias();
            System.out.println("DIGITE AS CATEGORIAS DO SEU CURSO: ");
            opc = i.nextInt();


            if (opc == 0){
                break;
            }

            c.addCategoria(opc, "null");
            cont++;
        }while(cont > 0 && opc > 0 && opc <= 30);

        c.setData_curso(java.sql.Date.valueOf(LocalDate.now()));
        DaoFactory.createCursoDao().inserir(c);



    }
    private static void consultaCurso(){
        Scanner in = new Scanner(System.in);
        System.out.print("DIGITE O ID DO CURSO QUE VOCÊ DESEJA CONSULTAR: ");
        Curso c = DaoFactory.createCursoDao().pesquisarPorID(in.nextInt());

        if (c != null){
            System.out.println(c.toString());
        }else{
            System.out.print("ESTE CURSO NÃO EXISTE NO BANCO DE DADOS.");
        }
    }
    private static void consultaCursoInstrutor(){
        Scanner in = new Scanner(System.in);
        System.out.print("DIGITE O ID DO INSTRUTOR QUE VOCÊ DESEJA CONSULTAR OS CURSOS: ");

        for(Curso c: DaoFactory.createCursoDao().pesquisarPorInstrutorID(in.nextInt())){
            System.out.println(c.toString());
        }
    }
    private static void atualizaCurso(){

        pula();
        Scanner s = new Scanner(System.in);
        Scanner i = new Scanner(System.in);
        Curso c;

        String titulo, id_instrutor;
        int id;

        do{
            try{
                System.out.print("DIGITE O ID DO CURSO A SER ALTERADO: "); // se ele der mais tempo fazer uma funcao pra verificar logo se o ed existe no banco de dados
                id = i.nextInt();
                break;
            }catch (InputMismatchException e){
                System.out.println("ID INVÁLIDO. ");
                i.next(); //descartar a entrada invalida
            }
        }while(true);

        c = DaoFactory.createCursoDao().pesquisarPorID(id);

        System.out.println("DIGITE \"-\" NOS CAMPOS QUE NÃO QUISER ALTERAR.");

        System.out.print("1. TITULO: ");
        titulo = s.nextLine();
        if (!titulo.equals("-"))
        {
            c.setTitulo(titulo);
            while(!Validacao.nome(titulo)){
                System.out.println("TITULO INVÁLIDO!");
                System.out.print("1. TITULO: ");
                titulo = s.nextLine();
                if (!titulo.equals("-")){
                    c.setTitulo(titulo);
                }
            }
        }

        System.out.print("2. ID DO INSTRUTOR: ");
        id_instrutor = s.nextLine();
        if (!id_instrutor.equals("-"))
        {
            c.setId_instrutor(Integer.parseInt(id_instrutor));
            while(!Validacao.num(id_instrutor)){
                System.out.println("ID DO INSTRUTOR INVÁLIDO!");
                System.out.print("2. ID DO INSTRUTOR: ");
                id_instrutor = s.nextLine();
                if (!id_instrutor.equals("-")){
                    c.setId_instrutor(Integer.parseInt(id_instrutor));
                }
            }
        }

        c.setData_curso(java.sql.Date.valueOf(LocalDate.now()));
        DaoFactory.createCursoDao().atualizar(c);
    }
    private static void deletaCurso(){
        Scanner in = new Scanner(System.in);
        System.out.print("DIGITE O ID DO CURSO QUE VOCÊ APAGAR: ");
        DaoFactory.createCursoDao().deletarPorID(in.nextInt());

    }
    private static void cadastraUsuario(){
        Scanner s = new Scanner(System.in);
        String nome, dataNascimento, login, senha;
        Usuario u = new Usuario();


        System.out.print("1. NOME: ");
        nome = s.nextLine();
        while(!Validacao.nome(nome)){
            System.out.println("NOME INVÁLIDO!");
            System.out.print("1. NOME: ");
            nome = s.nextLine();
        }
        u.setNome(nome);

        System.out.print("2. DATA DE NASCIMENTO: ");
        dataNascimento = s.nextLine();
        while(!Validacao.data(dataNascimento)){
            System.out.println("DATA INVÁLIDA!");
            System.out.println("2. DATA DE NASCIMENTO: ");
            dataNascimento = s.nextLine();
        }
        u.setData_nasc(dataNascimento);

        System.out.print("3. LOGIN: ");
        login = s.next();
        while(!Validacao.semEspaco(login)){
            System.out.println("LOGIN INVÁLIDO!");
            System.out.println("3. LOGIN: ");
            login = s.next();
        }
        u.setLogin(login);


        System.out.print("4. SENHA: ");
        senha = s.next();
        while(!Validacao.semEspaco(senha)){
            System.out.println("SENHA INVÁLIDA!");
            System.out.println("4. SENHA: ");
            senha = s.next();
        }
        u.setSenha(senha);
        DaoFactory.createUsuarioDao().inserir(u);

    }
    private static void consultaUsuario(){
        Scanner in = new Scanner(System.in);
        System.out.print("DIGITE O ID DO USUÁRIO QUE VOCÊ DESEJA CONSULTAR: ");

        Usuario u = DaoFactory.createUsuarioDao().pesquisarPorID(in.nextInt());

        if (u != null){
            System.out.println(u.toString());
        }else{
            System.out.print("ESTE USUÁRIO NÃO EXISTE NO BANCO DE DADOS.");
        }


    }
    private static void alteraUsuario(){
        pula();
        Scanner s = new Scanner(System.in);
        Scanner i = new Scanner(System.in);
        Usuario u;

        String nome, dataNascimento, login, senha;
        int id;

        do{
            try{
                System.out.print("DIGITE O ID DO USUÁRIO A SER ALTERADO: "); // se ele der mais tempo fazer uma funcao pra verificar logo se o ed existe no banco de dados
                id = i.nextInt();
                break;
            }catch (InputMismatchException e){
                System.out.println("ID INVÁLIDO. ");
                i.next(); //descartar a entrada invalida
            }
        }while(true);

        u = DaoFactory.createUsuarioDao().pesquisarPorID(id);

        System.out.println("DIGITE \"-\" NOS CAMPOS QUE NÃO QUISER ALTERAR.");

        System.out.print("1. NOME: ");
        nome = s.nextLine();
        if (!nome.equals("-"))
        {
            u.setNome(nome);
            while(!Validacao.nome(nome)){
                System.out.println("NOME INVÁLIDO!");
                System.out.print("1. NOME: ");
                nome = s.nextLine();
                if (Validacao.nome(nome) && !nome.equals("-")){
                    u.setNome(nome);
                }
            }
        }



        System.out.print("2. DATA DE NASCIMENTO: ");
        dataNascimento = s.nextLine();
        if (!dataNascimento.equals("-"))
        {
            u.setData_nasc(dataNascimento);
            while(!Validacao.data(dataNascimento)){
                System.out.println("DATA INVÁLIDA!");
                System.out.println("2. DATA DE NASCIMENTO: ");
                dataNascimento = s.nextLine();
                if (Validacao.data(dataNascimento) && !dataNascimento.equals("-")){
                    u.setData_nasc(nome);
                }
            }
        }




        System.out.print("3. LOGIN: ");
        login = s.nextLine();
        if (!login.equals("-"))
        {
            while(!Validacao.semEspaco(login)){
                u.setLogin(login);
                System.out.println("LOGIN INVÁLIDO!");
                System.out.println("3. LOGIN: ");
                login = s.nextLine();
                if (!Validacao.semEspaco(login) && !login.equals("-")){
                    System.out.println("madan boys interludio");
                    u.setLogin(login);

                }
            }
        }

        System.out.print("4. SENHA: ");
        senha = s.nextLine();
        if (!senha.equals("-"))
        {
            u.setSenha(senha);
            while(!Validacao.semEspaco(senha)){
                u.setSenha(senha);
                System.out.println("SENHA INVÁLIDA!");
                System.out.println("4. SENHA: ");
                senha = s.nextLine();
                if (!Validacao.semEspaco(senha)&&!senha.equals("-")){
                    u.setSenha(senha);
                }
            }
        }

        System.out.println(u.getLogin());
        DaoFactory.createUsuarioDao().atualizar(u, u.getId());
    }
    private static void deletaUsuario(){
        Scanner in = new Scanner(System.in);
        System.out.print("DIGITE O ID DO USUÁRIO QUE VOCÊ DESEJA DELETAR: ");
        DaoFactory.createUsuarioDao().deletarPorID(in.nextInt());

    }
    private static void listaUsuarios(){
        pula();
        for (Usuario u: DaoFactory.createUsuarioDao().listar()){

            System.out.println(u.toString());
            System.out.println("--------");

        }


    }
    private static void menuUsuario(){
        Scanner in = new Scanner(System.in);
        int op;

        do {
            pula();
            System.out.println("[1] CRIAR USUÁRIO");
            System.out.println("[2] CONSULTAR USUÁRIO");
            System.out.println("[3] ALTERAR USUÁRIO");
            System.out.println("[4] DELETAR USUÁRIO");
            System.out.println("[5] LISTAR TODOS OS USUÁRIOS");
            System.out.println("[0] VOLTAR");
            System.out.print("- Digite a opção desejada: ");
            op = in.nextInt();

            switch (op) {
                case 1:
                    cadastraUsuario();
                    break;
                case 2:
                    consultaUsuario();
                    break;
                case 3:
                    alteraUsuario();
                    break;
                case 4:
                    deletaUsuario();
                    break;
                case 5:
                    listaUsuarios();
                    break;
                case 0:
                    break;
                default:
                    break;
            }


        }while(op != 0);
    }
    private static void menuCurso(){
        Scanner in = new Scanner(System.in);
        int op;

        do {
            pula();
            System.out.println("[1] CRIAR CURSO");
            System.out.println("[2] CONSULTAR CURSO");
            System.out.println("[3] CONSULTAR CURSO POR INSTRUTOR");
            System.out.println("[4] ALTERAR CURSO");
            System.out.println("[5] DELETAR CURSO");
            System.out.println("[6] LISTAR TODOS OS CURSOS");
            System.out.println("[0] VOLTAR");
            System.out.print("- Digite a opção desejada: ");
            op = in.nextInt();

            switch (op) {
                case 1:
                    cadastraCurso();
                    break;
                case 2:
                    consultaCurso();
                    break;
                case 3:
                    consultaCursoInstrutor();
                    break;
                case 4:
                    atualizaCurso();
                    break;
                case 5:
                    deletaCurso();
                    break;
                case 6:
                    listaCursos();
                    break;
                case 0:
                    break;
                default:
                    break;
            }


        }while(op != 0);
    }
    private static void menu() {
        Scanner in = new Scanner(System.in);
        int op;
        do {
            pula();
            System.out.println("010101.BEM.VINDO.AO.ENIGMA.010101");
            System.out.println("CRUD ~");
            System.out.println("[1] - USUARIO");
            System.out.println("[2] - CURSO");
            System.out.println("[0] - SAIR");
            System.out.print("- Digite a opção desejada: ");
            op = in.nextInt();

            switch (op) {
                case 1:
                    menuUsuario();
                    break;
                case 2:
                    menuCurso();
                    break;
                case 0:
                    System.out.println("1111111.OBRIGADO.POR.USAR.1111111");
                    break;
                default:
                    break;
            }



        } while (op != 0);

        in.close();

    }
    public static void main(String[] args) {
        menu();
    }
}
