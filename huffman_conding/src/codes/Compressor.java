package codes;

import java.io.*;
import java.util.*;

public class Compressor {
    private Map<Character,Integer> frequencia;
    private HeapMix filaPrioridade;
    private Map<Character, StringBuilder> codificacao;



    public HeapMix getFilaPrioridade() { return filaPrioridade; }

    public Compressor(){
        frequencia = new HashMap<>();
        filaPrioridade = new HeapMix(); // toda vida que chega na capacidade máxima, ela dobra de tamnho
        codificacao = new HashMap<>();
    }


    public void printMap(){
        System.out.print("[");
        for (Map.Entry<Character,Integer> pair : frequencia.entrySet()) {
            System.out.print(" <" + pair.getKey() + "," + pair.getValue() + "> ");
        }
        System.out.println("]");

    }


    public void gerarFrequencia(String caminho) {
        File file = new File(caminho);

        if(file.exists()){

            Scanner ler_linha = null;
            try {
                ler_linha = new Scanner(file);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            frequencia.put((char)257,0); // o '\n' será representado pelo 257

            while (ler_linha.hasNextLine()){ // pegar cada linha do arquivo

                // cada vez que for lida uma linha vamos adicionar na frequencia do '\n'
                int freqQuebraLinha = frequencia.get((char)257);
                freqQuebraLinha++;
                frequencia.put((char)257,freqQuebraLinha);


                String linha = ler_linha.nextLine();

                char[] linha_char = linha.toCharArray();

                for(char i : linha_char){
                    if(frequencia.containsKey(i)){

                        int valor_frequencia = frequencia.get(i);

                        valor_frequencia++;

                        frequencia.put(i,valor_frequencia);

                    }
                    else {
                        //TODO pegar o \n
                        frequencia.put(i,1); // 1 pois quando entra pela primeira vez, ja conta 1
                    }
                }
            }

            ler_linha.close();

        }
        else {
            System.out.println("Arquivo não existe");
            return;
        }

    }


    public void guardarFrequenciaHeap(){
        for (Map.Entry<Character,Integer> pair : frequencia.entrySet()) {
            this.filaPrioridade.addNode(pair.getKey(),pair.getValue()); // key -> letter / value -> count
        }
        this.filaPrioridade.addNode(256,1); // Node EOF
    }


    public void verFilaPrioridade(){
        System.out.println(Arrays.toString(this.filaPrioridade.getNodes()));
        /*Está imprimindo uma parte de maneira errada, mas quando um é atendido a fila organiza os demais direito*/
    }


    public void guardarFrequenciaABB(){
        Node primeiroNode;
        Node segundoNode;
        Node newNode;


        while (filaPrioridade.getSize() > 0) {

            if(filaPrioridade.getSize() == 1){ // quando tiver so 1 -> a árvore binaria estará pronta
                break;
            }

            primeiroNode = filaPrioridade.peek();
            filaPrioridade.remove();

            segundoNode = filaPrioridade.peek();
            filaPrioridade.remove();


            int newCount = primeiroNode.getCount() + segundoNode.getCount();

            newNode = new Node(newCount,primeiroNode, segundoNode);

            this.filaPrioridade.addNode(newNode);

        }

    }



    void jogarTabela(Node node) {
        char[] path = new char[10000];
        printPathsRecur(node, path, 0, 'n');
    }

    void printPathsRecur(Node node, char[] path, int pathLen, char side) {
        if (node == null)
            return;

        /* append this node to the path array */
        if(side != 'n'){
            path[pathLen] = side;
            pathLen++;
        }

        /* it's a leaf, so print the path that led to here */
        if (node.getLeft() == null && node.getRight() == null){
            addMap(path, pathLen, (char)node.getLetter());
        }

        else {
            /* otherwise try both subtrees */
            printPathsRecur(node.getLeft(), path, pathLen, '0');
            printPathsRecur(node.getRight(), path, pathLen, '1');
        }
    }

    void addMap(char[] chars, int len, char letter) {

        StringBuilder path = new StringBuilder();

        int i;
        for (i = 0; i < len; i++){
            path.append(chars[i]); // jogando o caminho em uma StringBuilder
        }

        codificacao.put(letter, path); // adicionando no hashmap

    }




    public void criarTabelaCodificacao(String caminhoEdt){

        try {

           FileWriter fileWriter = new FileWriter(caminhoEdt);

           jogarTabela(filaPrioridade.getRoot());

            System.out.print("[");
            for (Map.Entry<Character, StringBuilder> pair : codificacao.entrySet()) {
                System.out.print(" <" + pair.getKey() + "," + pair.getValue().toString() + "> ");

                fileWriter.write(pair.getKey().toString() + pair.getValue() + "\n");
            }
            System.out.println("]");


            fileWriter.close();

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }




    public void CriarArquivoBinario(String caminhoTxt, String caminhoEdz ){

        try {

            File file = new File(caminhoTxt);

            if(file.exists()) {

                Scanner ler = new Scanner(file);
                File binaryFile = new File(caminhoEdz);

                FileOutputStream fileOutputStream = new FileOutputStream(binaryFile);
                ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);

                String bits = "";

                while (ler.hasNextLine()){
                    String linha = ler.nextLine();

                    char[] caracteresLinha = linha.toCharArray();

                    for(char it: caracteresLinha){
                       bits += this.codificacao.get(it);
                    }
                    bits += this.codificacao.get((char)257); // adicionano o '\n'
                }


               bits += this.codificacao.get((char)256); // concatenando o EOF aos bits de saida


                System.out.println(bits);

                BitSet bitSet = new BitSet(bits.length());

                char[] chars = bits.toCharArray();


                for(int i=0;i<bits.length();i++){
                    if(chars[i] == '1'){
                        bitSet.set(i, true);
                    }
                    else {
                        bitSet.set(i, false);
                    }
                }


                System.out.println("BitSet: " + bitSet);

                outputStream.write(bitSet.toByteArray());
                outputStream.flush();
                outputStream.close();


                ler.close();
                fileOutputStream.close();
                outputStream.close();
            }

            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }


    }







}





