package Exceptions;

public class UnvalidNumberOfLinesException extends Exception{
    public UnvalidNumberOfLinesException(){
        super();
    }
    public UnvalidNumberOfLinesException(String message){
        super(message);
    }
}
