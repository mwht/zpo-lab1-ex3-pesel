public class InvalidChecksumException extends Exception{
    public InvalidChecksumException()
    {
        super();
    }

    public InvalidChecksumException(String message)
    {
        super(message);
    }

    public InvalidChecksumException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public InvalidChecksumException(Throwable cause)
    {
        super(cause);
    }
}