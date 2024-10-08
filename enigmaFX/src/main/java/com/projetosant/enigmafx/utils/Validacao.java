package com.projetosant.enigmafx.utils;

public class Validacao {
    public static boolean semEspaco(String input){
        String regex = "^\\w+$";
        return input.matches(regex);
    }

    public static boolean nome(String input){
        String regex = "^[a-zA-Z]+"; // por enquanto so PEGA 1 NOME
        return input.matches(regex);

    }

    public static boolean data(String input){
        String regex = "\\b(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}\\b";
        return input.matches(regex);
    }

    public static boolean num(String input){
        return input.matches("^\\d+$");
    }
}
