package app.Exceptions;

public class ResourceNotFoundException extends Exception{
    private long id;

    public ResourceNotFoundException(long id){
        super(String.format("Scooter with id: %d does not exist", id));
        this.id = id;
    }
    public ResourceNotFoundException(String status){
        super(String.format("status=%s is not a valid scooter status value",status));
    }
}

