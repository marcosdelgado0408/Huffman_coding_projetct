package codes;

public class Frequencia {

    private char letter;
    private int count;


    public Frequencia(char letter, int count){
        this.letter = letter;
        this.count = count;
    }

    public char getLetter() { return letter; }

    public int getCount() { return count; }

    public void setLetter(char letter) { this.letter = letter; }

    public void setCount(int count) { this.count = count; }

    @Override
    public String toString(){
        return this.letter + "->" + this.count;
    }

    //restante das propriedades e métodos

}
