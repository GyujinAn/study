package kakaostyle.documentapproval.document.exception;


public class InvalidCatagoryException extends Exception{
    public InvalidCatagoryException() {
        super("유효하지 않은 분류입니다.");
    }
}
