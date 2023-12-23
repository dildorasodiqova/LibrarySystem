package uz.pdp.librarysystem.exception;
public class ExceededLimitException extends RuntimeException{
    public ExceededLimitException(String message){
        super(message);
    }
}
