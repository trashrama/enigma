package com.projetosant.enigmafx;

import model.dao.DaoFactory;
import model.entities.Curso;
import model.entities.Usuario;
import utils.Validacao;

import java.util.Map;
import java.util.Scanner;
import java.util.InputMismatchException;


public class Main {

    private static void pula(){
        System.out.print("\n\n\n\n\n\n\n");
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
            u.setNome(login);
            while(!Validacao.semEspaco(login)){
                System.out.println("LOGIN INVÁLIDO!");
                System.out.println("3. LOGIN: ");
                login = s.nextLine();
                if (!Validacao.semEspaco(login)&&!login.equals("-")){
                    break;
                }
            }
        }

        System.out.print("4. SENHA: ");
        senha = s.nextLine();
        if (!senha.equals("-"))
        {
            u.setNome(login);
            while(!Validacao.semEspaco(senha)){
                System.out.println("SENHA INVÁLIDA!");
                System.out.println("4. SENHA: ");
                senha = s.nextLine();
                if (!Validacao.semEspaco(senha)&&!senha.equals("-")){
                    break;
                }
            }
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
    private static void deletaUsuario(){}
    private static void listaUsuarios(){}
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
    private static void menuCurso(){}
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
                case 333666: // opc secreta de adm

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
