package main;
import codes.*;

import java.util.Arrays;
import java.util.Scanner;

// ./programa.jar compress arquivo.txt arquivo.edz arquivo.e
// ./programa.jar extract arquivo.edz arquivo.edt arquivo.t


public class Main {
    public static void main(String[] args) {
        Codificacao codificacao = new Codificacao();

        String caminho = args[2];

        codificacao.gerarFrequencia(caminho);
        codificacao.printMap();
        codificacao.guardarFrequenciaHeap();

        codificacao.verFilaPrioridade();




    }
}
