package kakaostyle.documentapproval.document.svc;

import kakaostyle.documentapproval.document.domain.*;
import kakaostyle.documentapproval.document.dto.ApplyDto;
import kakaostyle.documentapproval.document.dto.ApproverDto;
import kakaostyle.documentapproval.document.dto.DocumentDto;
import kakaostyle.documentapproval.document.exception.*;
import kakaostyle.documentapproval.document.repo.ApproverRepository;
import kakaostyle.documentapproval.document.repo.DocumentRepository;
import kakaostyle.documentapproval.user.KakaoUser;
import kakaostyle.documentapproval.user.KakaoUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class DocumentServiceImplTest {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private KakaoUserRepository kakaoUserRepository;

    @Autowired
    private ApproverRepository approverRepository;

    @Autowired
    private DocumentService documentService;

    KakaoUser user;

    KakaoUser approver;

    @BeforeEach
    void initClass(){
        user = kakaoUserRepository.findByEmail("user1@kakao.com");
        approver = kakaoUserRepository.findByEmail("user2@kakao.com");
    }


    @Test
    @Rollback
    void getOutboxDocuments() throws NoSuchUserException, NoSuchApproversException {
        //given data
        Document document = Document.builder()
                .catagory(Catagory.BA)
                .content("getOutboxDocuments content")
                .documentStatus(DocumentStatus.APPLYING)
                .kakaoUser(user)
                .title("getOutboxDocuments title")
                .build();
        documentRepository.save(document);

        //test
        List<DocumentDto> outboxDocuments = documentService.getOutboxDocuments(user.getId());

        //result
        Assertions.assertEquals(document.getTitle(), outboxDocuments.get(0).getTitle());
        Assertions.assertEquals(document.getContent(), outboxDocuments.get(0).getContent());

    }

    @Test
    @Rollback
    void getInBoxDocuments() throws NoSuchUserException {

        //given data
        Document document = Document.builder()
                .catagory(Catagory.BA)
                .content("getInBoxDocuments content")
                .documentStatus(DocumentStatus.APPLYING)
                .kakaoUser(user)
                .title("getInBoxDocuments title")
                .build();
        documentRepository.save(document);

        Approver build = Approver.builder()
                .document(document)
                .isLast(false)
                .isOrder(true)
                .priority(1)
                .userId(approver.getId())
                .approveStatus(ApproveStatus.BEFOREAPPROVED)
                .build();

        approverRepository.save(build);

        //test
        List<DocumentDto> inBoxDocuments = documentService.getInBoxDocuments(build.getUserId());

        //result
        Assertions.assertEquals(document.getTitle(), inBoxDocuments.get(0).getTitle());
        Assertions.assertEquals(document.getContent(), inBoxDocuments.get(0).getContent());

    }

    @Test
    @Rollback
    void getArchiveDocuments() throws NoSuchUserException {
        //given data
        Document document = Document.builder()
                .catagory(Catagory.BA)
                .content("getArchiveDocuments content")
                .documentStatus(DocumentStatus.APPROVED)
                .kakaoUser(user)
                .title("getArchiveDocuments title")
                .build();
        documentRepository.save(document);

        Approver build = Approver.builder()
                .document(document)
                .isLast(false)
                .isOrder(false)
                .priority(1)
                .userId(approver.getId())
                .approveStatus(ApproveStatus.APPROVED)
                .build();

        approverRepository.save(build);

        //test
        List<DocumentDto> archiveDocuments = documentService.getArchiveDocuments(build.getUserId());

        //result
        Assertions.assertEquals(document.getTitle(), archiveDocuments.get(0).getTitle());
        Assertions.assertEquals(document.getContent(), archiveDocuments.get(0).getContent());
    }

    @Test
    void createDocument() throws NoSuchUserException, NoSuchApproversException, InvalidCatagoryException, InvalidApproverException {

        //given data
        List<ApproverDto> approverDtoList = new ArrayList<>();
        ApproverDto approverDto = ApproverDto.builder()
                .email(approver.getEmail())
                .priority(1)
                .userId(approver.getId())
                .build();
        approverDtoList.add(approverDto);

        ApplyDto applyDto = ApplyDto.builder()
                .title("createDocument title")
                .content("createDocument content")
                .catagory(Catagory.BA)
                .approver(approverDtoList)
                .build();

        //test
        Long document = documentService.createDocument(applyDto, user.getId());


        //result

        Approver resultApprover = approverRepository.findByUserId(this.approver.getId()).get();
        Assertions.assertEquals(resultApprover.getDocument().getId(),document);

    }

    @Test
    @Rollback
    void approveDocument() throws NoSuchDocumentException, NoSuchUserException, InvalidApproverException {
        //given data
        Document document = Document.builder()
                .catagory(Catagory.BA)
                .content("approveDocument content")
                .documentStatus(DocumentStatus.APPLYING)
                .kakaoUser(user)
                .title("approveDocument title")
                .build();
        Document save = documentRepository.save(document);

        Approver build = Approver.builder()
                .document(document)
                .isLast(true)
                .isOrder(true)
                .priority(1)
                .userId(approver.getId())
                .approveStatus(ApproveStatus.BEFOREAPPROVED)
                .build();

        approverRepository.save(build);

        ApproverDto approverDto = ApproverDto.builder()
                .userId(approver.getId())
                .comment("approveDocument comment")
                .approverId(build.getId())
                .build();

        //test
        String result = documentService.approveDocument(approverDto, approver.getId());


        //result

        Assertions.assertEquals("completed approval", result);

    }

    @Test
    @Rollback
    void rejectDocument() throws NoSuchDocumentException, NoSuchUserException, InvalidApproverException {
        //given data
        Document document = Document.builder()
                .catagory(Catagory.BA)
                .content("rejectDocument content")
                .documentStatus(DocumentStatus.APPLYING)
                .kakaoUser(user)
                .title("rejectDocument title")
                .build();
        Document save = documentRepository.save(document);

        Approver build = Approver.builder()
                .document(document)
                .isLast(true)
                .isOrder(true)
                .priority(1)
                .userId(approver.getId())
                .approveStatus(ApproveStatus.BEFOREAPPROVED)
                .build();

        approverRepository.save(build);

        ApproverDto approverDto = ApproverDto.builder()
                .userId(approver.getId())
                .comment("rejectDocument comment")
                .approverId(build.getId())
                .build();

        //test
        String result = documentService.rejectDocument(approverDto, approver.getId());


        //result
        Assertions.assertEquals("rejected approval", result);

    }
}