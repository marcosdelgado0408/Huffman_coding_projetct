package main;
import codes.*;

import javax.swing.*;
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

        codificacao.guardarFrequenciaABB();

        codificacao.criarTabelaCodificacao();



        // ------------- printar a ABB -----------------------
        JFrame frame = new JFrame("Visualizador de ABB");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        ArvoreBinariaBuscaView view = new ArvoreBinariaBuscaView(codificacao.getFilaPrioridade());
        frame.add(view);

        frame.setVisible(true);
        // --------------------------------------------------

    }
}
