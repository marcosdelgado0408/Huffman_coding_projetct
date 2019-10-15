package main;


// ./programa.jar compress arquivo.txt arquivo.edz arquivo.edt
// ./programa.jar extract arquivo.edz arquivo.edt arquivo.txt


public class Main {
    public static void main(String[] args) {

        Menu menu = new Menu();
        menu.coisar(args);

    }
}
