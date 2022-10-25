package app.Exceptions;

public class ResourceNotFoundException extends Exception{
    private long id;

    public ResourceNotFoundException(long id){
        super(String.format("Scooter with id: %d does not exist", id));
        this.id = id;
    }
}
