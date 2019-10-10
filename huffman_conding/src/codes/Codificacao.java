package codes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Codificacao {
    private Map<Character,Integer> frequencia;
    private Heap filaPrioridade;



    public Codificacao(){
        frequencia = new HashMap<>();
        filaPrioridade = new Heap(); // TODO ainda não sei a capacidade que a heap deve ter
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

            while (ler_linha.hasNextLine()){ // pegar todas as linhas do arquivo

                String linha = ler_linha.nextLine();

                char[] linha_char = linha.toCharArray();

                for(char i : linha_char){
                    if(frequencia.containsKey(i)){

                        int valor_frequencia = frequencia.get(i);

                        valor_frequencia++;

                        frequencia.put(i,valor_frequencia);

                    }
                    else {
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

    }

    public void verFilaPrioridade(){
        System.out.println(Arrays.toString(this.filaPrioridade.getNodes()));
        /*Está imprimindo uma parte de maneira errada, mas quando um é atendido a fila organiza os demais direito*/
    }





}
