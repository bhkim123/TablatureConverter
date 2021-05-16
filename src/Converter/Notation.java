package Converter;

public class Notation implements Comparable{
    String notation;
    int index;
    int duration;
    int stringNum;
    public Notation(String notation, int index, int stringNum){
        this.notation = notation;
        this.index = index;
        this.stringNum = stringNum;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getNotation(){
        return this.notation;
    }

    public int getIndex(){
        return this.index;
    }

    public int getStringNum(){return this.stringNum; }


    @Override
    public int compareTo(Object o) {
        return this.index - ((Notation)o).getIndex();
    }
}
