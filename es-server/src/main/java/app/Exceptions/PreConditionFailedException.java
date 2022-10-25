package app.Exceptions;

public class PreConditionFailedException extends Exception{
    private long parameterId;
    private long id;

    public PreConditionFailedException(long parameterId, long id){
        super(String.format("Scooter-id: %d does not match path parameter %d",
                id, parameterId));
        this.parameterId = parameterId;
        this.id = id;
    }

}
