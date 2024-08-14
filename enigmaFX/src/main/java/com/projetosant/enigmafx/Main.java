package com.projetosant.enigmafx;

import model.dao.DaoFactory;
import model.entities.Curso;
import model.entities.Usuario;
import utils.Validacao;

import java.util.Map;
import java.util.Scanner;


public class Main {

    private static void pula(){
        System.out.print("\n\n\n\n\n");
    }

    private static void cadastraUsuario(){
        Scanner s = new Scanner(System.in);
        String nome, dataNascimento, login, senha;
        Usuario u = new Usuario();


        System.out.print("1. NOME: ");
        nome = s.next();
        while(!Validacao.nome(nome)){
            System.out.println("NOME INVÁLIDO!");
            System.out.print("1. NOME: ");
            nome = s.next();
        }
        u.setNome(nome);

        System.out.print("2. DATA DE NASCIMENTO: ");
        dataNascimento = s.next();
        while(!Validacao.data(dataNascimento)){
            System.out.println("DATA INVÁLIDA!");
            System.out.println("2. DATA DE NASCIMENTO: ");
            dataNascimento = s.next();
        }
        u.setData_nasc(dataNascimento);

        System.out.print("3. LOGIN: ");
        login = s.next();
        while(!Validacao.semEspaco(login)){
            System.out.println("LOGIN INVÁLIDA!");
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
    private static void alteraUsuario(){}
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
