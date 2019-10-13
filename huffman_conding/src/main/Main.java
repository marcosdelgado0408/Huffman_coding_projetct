package main;
import codes.*;

import javax.swing.*;

// ./programa.jar compress arquivo.txt arquivo.edz arquivo.e
// ./programa.jar extract arquivo.edz arquivo.edt arquivo.t


public class Main {
    public static void main(String[] args) {
        Compressor codificacao = new Compressor();
        Extractor decodificacao = new Extractor();

        String caminhoTxt = args[2];
        String caminhoEdz = args[3];

        codificacao.gerarFrequencia(caminhoTxt);
        codificacao.printMap();
        codificacao.guardarFrequenciaHeap();

        codificacao.verFilaPrioridade();

        codificacao.guardarFrequenciaABB();

        codificacao.criarTabelaCodificacao();

        codificacao.CriarArquivoBinario(caminhoTxt,caminhoEdz);


        decodificacao.gerarTabelaCodificacao("tabela_codificacao.edt");
        decodificacao.lerBinario("arquivo.edz");
        decodificacao.recuperandoArquivo();




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
