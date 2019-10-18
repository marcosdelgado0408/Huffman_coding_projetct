package codes;

import java.io.*;
import java.util.*;

public class Extractor {
    private Map<Character, StringBuilder> codificacao;
    private List<Character> bitsRecebidos;
    private BitSet bitsetParaIprimir;



    public Extractor(){
        codificacao = new HashMap<>();
        bitsRecebidos = new ArrayList<>();
    }



    public void gerarTabelaCodificacao(String caminhoEdt){
        try {

            File file = new File(caminhoEdt);

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            int c = 0;
            while((c  = bufferedReader.read()) != -1){ // ler caractere por caractere
                char character = (char) c;

                StringBuilder bits = new StringBuilder();

                /* como a primeira posição é a letra e o resto é a codificação, então faço outro while para pegar o resto
                 dessa linha, sem pegar a letra */
                int d = 0;
                while ((d  = bufferedReader.read()) != '\n'){
                    bits.append((char)d);
                }

                this.codificacao.put(character,bits);
            }

            fileReader.close();
            bufferedReader.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }





    public void lerBinario(String caminhoEdz){
        try {
                FileInputStream fileInputStream  = new FileInputStream(caminhoEdz);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);


                byte[] bytes =  objectInputStream.readAllBytes();

                BitSet bitSet = BitSet.valueOf(bytes);

                //      IMPRIMIR BITSET RECEBIDO
                bitsetParaIprimir = bitSet;

                for(int i=0;i<bitSet.length();i++){
                    if(bitSet.get(i)){
                        bitsRecebidos.add('1');
                    }
                    else {
                        bitsRecebidos.add('0');
                    }
                }

                fileInputStream.close();
                objectInputStream.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }




    public void recuperandoArquivo(String caminhoTxt){
        try {

            FileWriter fileWriter = new FileWriter(caminhoTxt);

            StringBuilder key = new StringBuilder();

            for(char it: this.bitsRecebidos){ // percorrer cada BIT

                key.append(it);

                 // percorrer a tabela de codificação
                    // caso a key estiver no range da tabela ascii
                for (Map.Entry<Character, StringBuilder> pair : codificacao.entrySet()) {

                    if(key.compareTo(pair.getValue()) == 0){

                        if((int)pair.getKey() == 256){ // caso achar o caractere do EOF
                            break;
                        }

                        fileWriter.write(pair.getKey());
                        key = new StringBuilder();

                    }

                }
            }
            fileWriter.close();

        }
        catch (IOException ex) {
            ex.printStackTrace();
        }


    }


    public void printAllInfo(){
//                       IMPRIMIR TABELA DE CODIFICAÇÃO
        System.out.print("[");
        for (Map.Entry<Character, StringBuilder> pair : codificacao.entrySet()) {
            System.out.print(" <" + pair.getKey() + "," + pair.getValue().toString() + "> ");
        }
        System.out.println("]");


//                  IMPRIMIR BITSET
        System.out.println("BITSET RECEBIDO:");
        System.out.println(this.bitsetParaIprimir);



//                  IMPRIMIR BITS RECEBIDOS
                for (Character it:this.bitsRecebidos){
                    System.out.print(it);
                }
                System.out.println();


    }




}













