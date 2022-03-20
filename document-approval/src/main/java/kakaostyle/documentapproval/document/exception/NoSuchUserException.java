package kakaostyle.documentapproval.document.exception;


public class NoSuchUserException extends Exception{
    public NoSuchUserException() {
        super("존재하지 않는 사용자입니다.");
    }
}
