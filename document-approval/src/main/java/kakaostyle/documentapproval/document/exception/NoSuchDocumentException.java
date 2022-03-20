package kakaostyle.documentapproval.document.exception;


public class NoSuchDocumentException extends Exception{
    public NoSuchDocumentException() {
        super("존재하지 않는 문서입니다.");
    }
}
