package codes;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Compressor {
    private Map<Character,Integer> frequencia;
    private HeapMix filaPrioridade;
    private Map<Character, String> codificacao;
    private String bitsParaImprimir;
    private BitSet bitsetParaImprimir;


    public HeapMix getFilaPrioridade() { return filaPrioridade; }

    public Compressor(){
        frequencia = new HashMap<>();
        filaPrioridade = new HeapMix(); // toda vida que chega na capacidade máxima, ela dobra de tamnho
        codificacao = new HashMap<Character, String>();
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

            try {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                int c = 0;
                while((c  = bufferedReader.read()) != -1){ // ler caractere por caractere
                    char character = (char) c;

                    if(frequencia.containsKey(character)){

                        int valor_frequencia = frequencia.get(character);

                        valor_frequencia++;

                        frequencia.put(character,valor_frequencia);

                    }
                    else {
                        //TODO pegar o \n

                        frequencia.put(character,1); // 1 pois quando entra pela primeira vez, ja conta 1

                    }

                }

                fileReader.close();
                bufferedReader.close();

            }
            catch (IOException e) {
                e.printStackTrace();
            }

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




/* VOU DEIXAR ESSE CÓDIGO ABAIXO COMENTADO, POIS O jogarPaths() SUBSTITUI TODOS ESSES
    3 MÉTODOS, ALÉM DISSO DEU MUITO TRABALHO PARA FAZER E SÃO FUNCIONAIS.*/



//
//
//    private void jogarTabela(Node node) {
//        char[] path = new char[1000000000];
//        printPathsRecur(node, path, 0, 'n');
//    }
//
//    private void printPathsRecur(Node node, char[] path, int pathLen, char side) {
//        if (node == null)
//            return;
//
//        /* append this node to the path array */
//        if(side != 'n'){ // caso o lado não for nem 0 e nem 1 -> não vai adicionar o raiz em path[]
//            path[pathLen] = side;
//            pathLen++;
//        }
//
//        /* it's a leaf, so print the path that led to here */
//        if (node.getLeft() == null && node.getRight() == null){
//            addMap(path, pathLen, (char)node.getLetter());
//        }
//
//        else {
//            /* otherwise try both subtrees */
//            printPathsRecur(node.getLeft(), path, pathLen, '0');
//            printPathsRecur(node.getRight(), path, pathLen, '1');
//        }
//    }
//
//    private void addMap(char[] chars, int len, char letter) {
//
//        // adicionando o path de cada leaf no map da tabela de codificação
//
//        StringBuilder path = new StringBuilder();
//
//        int i;
//        for (i = 0; i < len; i++){
//            path.append(chars[i]); // jogando o caminho em uma StringBuilder
//        }
//
//        codificacao.put(letter, path); // adicionando no hashmap
//
//    }


    public void jogarPaths(Node root, String path) {

        if (root.getLeft() == null && root.getRight() == null) {

            codificacao.put((char)root.getLetter(), path); // adicionando no hashmap

            return;
        }

        jogarPaths(root.getLeft(), path + "0");
        jogarPaths(root.getRight(), path + "1");
    }





    public void criarTabelaCodificacao(String caminhoEdt){

        try {

           FileWriter fileWriter = new FileWriter(caminhoEdt);

            jogarPaths(filaPrioridade.getRoot(),"");

            for (Map.Entry<Character, String> pair : codificacao.entrySet()) {
                fileWriter.write(pair.getKey().toString() + pair.getValue() + "\n");
            }


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

                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                File binaryFile = new File(caminhoEdz);

                FileOutputStream fileOutputStream = new FileOutputStream(binaryFile);
                ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);

                String bits = "";

                int c = 0;
                while ((c  = bufferedReader.read()) != -1){ // lendo arquivo original

                    char character = (char)c;

                    bits += this.codificacao.get(character);

                }


                bits += this.codificacao.get((char)256); // concatenando o EOF aos bits de saida

                //  IMPRIMIR BITS GERADOS DO ARQUIVO ORIGINAL
                this.bitsParaImprimir = bits;

                BitSet bitSet = new BitSet(bits.length());

                char[] chars = bits.toCharArray();

                for(int i=0;i<bits.length();i++){ // transformando os bits para um Bitset
                    if(chars[i] == '1'){
                        bitSet.set(i, true);
                    }
                    else {
                        bitSet.set(i, false);
                    }
                }

                //  IMPRIMIR BITSET DOS BITS GERADOS
                this.bitsetParaImprimir = bitSet;

                outputStream.write(bitSet.toByteArray());
                outputStream.flush();
                outputStream.close();


                fileReader.close();
                bufferedReader.close();
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


    public void exibirTaxaCompressão(String caminhoEdz, String caminhoTxt){
        File txt = new File(caminhoTxt);
        File edz = new File(caminhoEdz);

        double tamanho_txt = txt.length();
        double tamanho_edz = edz.length();
        double taxa;

        if(tamanho_txt == 0){
            taxa = 100 * tamanho_edz;
        }
        else {
            taxa = ((tamanho_edz * 100) / tamanho_txt);
        }

        DecimalFormat df = new DecimalFormat("#,###.00");

        System.out.println("--------------------------------------------------------");
        System.out.println(caminhoTxt + ": " + tamanho_txt + " bytes");
        System.out.println(caminhoEdz + ": " + tamanho_edz + " bytes\n");


        System.out.println("O arquivo possui " + df.format(taxa) + "% do seu tamanho original\n" );
        System.out.println("--------------------------------------------------------");

    }

    public void printAllInfo(){
        System.out.println("MAP:");
        this.printMap();
        System.out.print("[");

        System.out.println("FILA DE PRIORIDADE:");
        this.verFilaPrioridade();


        //                   IMPRIMIR TABELA DE CODIFICAÇÃO
        System.out.println("TABELA DE CODIFICAÇÃO:");
        for (Map.Entry<Character, String> pair : codificacao.entrySet()) {
            System.out.print(" <" + pair.getKey() + "," + pair.getValue().toString() + "> ");
        }
        System.out.println("]");


        System.out.println("BITS ENVIADOS:");
        System.out.println(this.bitsParaImprimir);

        System.out.println("BITSET DOS BITS ENVIADOS:");
        System.out.println(this.bitsetParaImprimir);
    }




}





