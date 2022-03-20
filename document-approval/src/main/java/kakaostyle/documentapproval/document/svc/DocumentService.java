package kakaostyle.documentapproval.document.svc;


import kakaostyle.documentapproval.document.dto.ApplyDto;
import kakaostyle.documentapproval.document.dto.ApproverDto;
import kakaostyle.documentapproval.document.dto.DocumentDto;
import kakaostyle.documentapproval.document.exception.*;

import java.util.List;

public interface DocumentService {

    public List<DocumentDto> getOutboxDocuments(Long userId) throws NoSuchUserException;

    public List<DocumentDto> getInBoxDocuments(Long userId) throws NoSuchUserException;

    public List<DocumentDto> getArchiveDocuments(Long userId) throws NoSuchUserException;

    public Long createDocument(ApplyDto applyDto, Long userId) throws NoSuchApproversException, NoSuchUserException, InvalidCatagoryException, InvalidApproverException;

    public String approveDocument(ApproverDto approverDto, Long userId) throws NoSuchUserException, InvalidApproverException, NoSuchDocumentException;

    public String rejectDocument(ApproverDto approverDto, Long userId) throws NoSuchUserException, InvalidApproverException, NoSuchDocumentException;


}
