package kakaostyle.documentapproval.document;

import kakaostyle.documentapproval.document.dto.ApplyDto;
import kakaostyle.documentapproval.document.dto.ApproverDto;
import kakaostyle.documentapproval.document.dto.DocumentDto;
import kakaostyle.documentapproval.document.exception.*;
import kakaostyle.documentapproval.document.svc.DocumentService;
import kakaostyle.documentapproval.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
public class DocumentController {

    final private DocumentService documentService;

    final private JwtProvider jwtProvider;


    @GetMapping("/outbox/documents")
    public List<DocumentDto> getOutboxDocuments(HttpServletRequest request, HttpServletResponse response){
        List<DocumentDto> outboxDocuments;
        Long userId = Long.valueOf(jwtProvider.getUserId(request.getHeader("X-AUTH-TOKEN")));

        try {
            outboxDocuments = documentService.getOutboxDocuments(userId);
        } catch (NoSuchUserException e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return null;
        }

        return outboxDocuments;
    }

    @GetMapping("/inbox/documents")
    public List<DocumentDto> getInboxDocuments(HttpServletRequest request, HttpServletResponse response){
        List<DocumentDto> inboxDocuments;
        Long userId = Long.valueOf(jwtProvider.getUserId(request.getHeader("X-AUTH-TOKEN")));

        try {
            inboxDocuments = documentService.getInBoxDocuments(userId);
        } catch (NoSuchUserException e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return null;
        }

        return inboxDocuments;
    }

    @GetMapping("/archive/documents")
    public List<DocumentDto> getArchiveDocuments(HttpServletRequest request, HttpServletResponse response){
        List<DocumentDto> archiveDocuments;
        Long userId = Long.valueOf(jwtProvider.getUserId(request.getHeader("X-AUTH-TOKEN")));

        try {
            archiveDocuments = documentService.getArchiveDocuments(userId);
        } catch (NoSuchUserException e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return null;
        }


        return archiveDocuments;
    }

    @PostMapping("/documents")
    public Long applyDocuments(HttpServletRequest request, HttpServletResponse response, @RequestBody ApplyDto applyDto){
        Long userId = Long.valueOf(jwtProvider.getUserId(request.getHeader("X-AUTH-TOKEN")));

        Long document = null;
        try {
            document = documentService.createDocument(applyDto, userId);
        } catch (NoSuchApproversException e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return null;
        } catch (NoSuchUserException e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return null;
        } catch (InvalidApproverException e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return null;
        } catch (InvalidCatagoryException e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return null;
        }

        return  document;
    }


    @PutMapping("/documents")
    public String approvalDocument(HttpServletRequest request, HttpServletResponse response, @RequestBody ApproverDto approverDto, @RequestParam String type){
        Long userId = Long.valueOf(jwtProvider.getUserId(request.getHeader("X-AUTH-TOKEN")));
        String result = "";
        try {
            if(type.equals("approve")){
                result = documentService.approveDocument(approverDto, userId);
            }else if(type.equals("reject")){
                result = documentService.rejectDocument(approverDto, userId);
            }else {
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                return "invaild type";
            }
        } catch (NoSuchUserException e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return e.getMessage();
        } catch (InvalidApproverException e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return e.getMessage();
        } catch (NoSuchDocumentException e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return e.getMessage();
        }


        return result;
    }


}
