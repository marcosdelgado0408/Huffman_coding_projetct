package main;

import codes.*;
import javax.swing.*;

public class Menu {

    public void coisar(String[] args) {

        if(args.length < 4){
            System.out.println("O programa necessita de mais argumentos");
            System.out.println("Por exemplo: compress arquivo.txt arquivo.edz arquivo.edt");
            System.out.println("ou: extract arquivo.edz arquivo.edt arquivo.txt");
            return;
        }

        Compressor codificacao = new Compressor();
        Extractor decodificacao = new Extractor();


        if(args[0].equals("compress")){
            String caminhoTxt = args[1];
            String caminhoEdz = args[2];
            String caminhoEdt = args[3];

            codificacao.gerarFrequencia(caminhoTxt);
            codificacao.printMap();
            codificacao.guardarFrequenciaHeap();

            codificacao.verFilaPrioridade();

            codificacao.guardarFrequenciaABB();

            codificacao.criarTabelaCodificacao(caminhoEdt);

            codificacao.CriarArquivoBinario(caminhoTxt,caminhoEdz);

            if(args.length > 4){
                if(args[4].equals("--show-abb") || args[4].equals("-s")) { // caso adicionar o argumento -s vai printar a Arvore Binária
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


            }



        else if(args[0].equals("extract")){

            String caminhoEdz = args[1];
            String caminhoEdt = args[2];
            String caminhoTxt = args[3];

            decodificacao.gerarTabelaCodificacao(caminhoEdt);
            decodificacao.lerBinario(caminhoEdz);
            decodificacao.recuperandoArquivo(caminhoTxt);
        }

        else {
            System.out.println("Esse método de compressão não existe\nTente novamente");
        }


    }




}

