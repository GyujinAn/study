package kakaostyle.documentapproval.document.exception;

public class InvalidApproverException extends Exception{
    public InvalidApproverException() {
        super("유효하지 않는 승인자입니다.");
    }
}
