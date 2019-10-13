package main;

import codes.*;
import javax.swing.*;

public class Menu {


    public void coisar(String[] args) {
        Compressor codificacao = new Compressor();
        Extractor decodificacao = new Extractor();

        if(args[1].equals("compress")){
            String caminhoTxt = args[2];
            String caminhoEdz = args[3];
            String caminhoEdt = args[4];

            codificacao.gerarFrequencia(caminhoTxt);
            codificacao.printMap();
            codificacao.guardarFrequenciaHeap();

            codificacao.verFilaPrioridade();

            codificacao.guardarFrequenciaABB();

            codificacao.criarTabelaCodificacao(caminhoEdt);

            codificacao.CriarArquivoBinario(caminhoTxt,caminhoEdz);



            // ------------- printar a ABB -----------------------
            JFrame frame = new JFrame("Visualizador de ABB");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            ArvoreBinariaBuscaView view = new ArvoreBinariaBuscaView(codificacao.getFilaPrioridade());
            frame.add(view);

            frame.setVisible(true);
            // --------------------------------------------------


        }

        if(args[1].equals("extract")){

            String caminhoEdz = args[2];
            String caminhoEdt = args[3];
            String caminhoTxt = args[4];

            decodificacao.gerarTabelaCodificacao(caminhoEdt);
            decodificacao.lerBinario(caminhoEdz);
            decodificacao.recuperandoArquivo(caminhoTxt);
        }


    }


}

