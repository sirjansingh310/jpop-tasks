package exception;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(int bookId){
        super("No book found with id " + bookId);
    }
}
