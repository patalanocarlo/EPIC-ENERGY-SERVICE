package BuildWeekU5.EPIC.ENERGY.SERVICE.exceptions;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(String message){
        super(message);
    }
}