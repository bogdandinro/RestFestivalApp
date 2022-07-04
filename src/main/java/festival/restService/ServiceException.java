package festival.restService;

public class ServiceException extends RuntimeException {
    public ServiceException(Exception e) {
        super(e);
    }
}
