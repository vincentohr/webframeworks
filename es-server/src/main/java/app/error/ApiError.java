package app.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

public class ApiError {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private HttpStatus error;
    private String message;
    private String path;

    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus error, Throwable ex) {
        this();
        this.error = error;
        this.message = ex.getLocalizedMessage();
    }

    public ApiError(HttpStatus error, Throwable ex, String path) {
        this();
        this.error = error;
        this.message = ex.getLocalizedMessage();
        this.path = path;
    }

    public HttpStatus getError() {
        return error;
    }

    public void setError(HttpStatus error) {
        this.error = error;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
