package egaz.go.tz.assets.exception;

import egaz.go.tz.assets.dto.AssetDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
public class ResourceExceptionHandler {
    @ExceptionHandler (value = DuplicateResourceException.class)
    public ResponseEntity<ApiException> handleResourceException(DuplicateResourceException resourceExceptionHandler) {
        HttpStatus badStatus = HttpStatus.BAD_REQUEST;
        ApiException apiException =  new ApiException(
                 resourceExceptionHandler.getMessage(),
                 resourceExceptionHandler.getCause());
                

        List<AssetDTO> ApiException;
        return new ResponseEntity<>(apiException,badStatus);
    }

}