package kakaostyle.documentapproval.document.exception;


public class NoSuchApproversException extends Exception{
    public NoSuchApproversException() {
        super("존재하지 않는 승인자입니다.");
    }

}
