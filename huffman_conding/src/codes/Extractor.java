package codes;

import java.io.*;
import java.util.*;

public class Extractor {
    private Map<Character, StringBuilder> codificacao;
    private List<Character> bitsRecebidos;



    public Extractor(){
        codificacao = new HashMap<>();
        bitsRecebidos = new ArrayList<>();
    }



    public void gerarTabelaCodificacao(String caminhoEdt){
        try {
            File file = new File(caminhoEdt);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()){
                String linha = scanner.nextLine();

                char[] chars = linha.toCharArray();

                StringBuilder bits = new StringBuilder();

                /* como a primeira posição é a letra e o resto é a codificação, então faço um for para pegar o resto
                 dessa linha, sem pegar a letra */
                for(int i=1;i<chars.length;i++){
                    bits.append(chars[i]);
                }

                this.codificacao.put(chars [0],bits);

            }

            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.print("[");
        for (Map.Entry<Character, StringBuilder> pair : codificacao.entrySet()) {
            System.out.print(" <" + pair.getKey() + "," + pair.getValue().toString() + "> ");
        }
        System.out.println("]");
    }





    public void lerBinario(String caminhoEdz){
        try {
                FileInputStream fileInputStream  = new FileInputStream(caminhoEdz);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);


                byte[] bytes =  objectInputStream.readAllBytes();

                BitSet bitSet = BitSet.valueOf(bytes);

                System.out.println(bitSet);

                for(int i=0;i<bitSet.length();i++){
                    if(bitSet.get(i)){
                        bitsRecebidos.add('1');
                    }
                    else {
                        bitsRecebidos.add('0');
                    }
                }


                for (Character it:this.bitsRecebidos){
                    System.out.print(it);
                }
                System.out.println();

                fileInputStream.close();
                objectInputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }




    public void recuperandoArquivo(String caminhoTxt){
        try {

            FileWriter fileWriter = new FileWriter(caminhoTxt);

            StringBuilder key = new StringBuilder();

            for(char it: this.bitsRecebidos){ // percorrer cada BIT

                key.append(it);

                for (Map.Entry<Character, StringBuilder> pair : codificacao.entrySet()) { // percorrer a tabela de codificação
                    // caso a key estiver no range da tabela ascii
                        if(key.compareTo(pair.getValue()) == 0){

                            if((int)pair.getKey() == 256){ // caso achar o caractere do EOF
                                break;
                            }

                            if((int)pair.getKey() == 257){ // caso achar o caracter do '\n'
                                fileWriter.write('\n');
                                key = new StringBuilder();
                            }

                            else {
                                fileWriter.write(pair.getKey());
                                key = new StringBuilder();
                            }

                        }

                }

            }

            fileWriter.close();

        }
        catch (IOException ex) {
            ex.printStackTrace();
        }


    }





}













