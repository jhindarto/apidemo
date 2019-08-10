package hindarto.johan.moduleapi.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(long id) {
        super( "User Id " + id + " Not Found");
    }
}
