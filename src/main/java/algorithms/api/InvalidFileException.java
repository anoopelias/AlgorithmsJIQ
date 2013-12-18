package algorithms.api;

public class InvalidFileException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public InvalidFileException(Throwable e) {
        super(e);
    }

}
