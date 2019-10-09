package codes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Codificacao {

    private Map<Character,Integer> frequencia;

    public Codificacao(){
        frequencia = new HashMap<>();

    }


    public void printMap(){
        HashMap<Character,Integer> percorrer = new HashMap<>();



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

            while (ler_linha.hasNextLine()){ // pegar todas as linhas do arquivo

                String linha = ler_linha.nextLine();

                char[] linha_char = linha.toCharArray();

                for(char i : linha_char){
                // TODO preciso percorrer todo o hasmap para verificar se ja existe a letra

                    if(frequencia.containsKey(i)){

                        int valor_frequencia = frequencia.get(i);

                        valor_frequencia++;

                        frequencia.put(i,valor_frequencia);

                        System.out.println("quantidade: " + frequencia.get(i));

                    }
                    else {
                        System.out.println("adding " + i);
                        frequencia.put(i,0);
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





}
