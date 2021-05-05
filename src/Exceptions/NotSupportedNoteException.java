package Exceptions;

public class NotSupportedNoteException extends Exception{
    public NotSupportedNoteException(){
        super();
    }
    public NotSupportedNoteException(String message){
        super(message);
    }
}
