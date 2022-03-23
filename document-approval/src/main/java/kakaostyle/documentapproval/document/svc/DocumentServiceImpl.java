package kakaostyle.documentapproval.document.svc;


import kakaostyle.documentapproval.document.domain.*;
import kakaostyle.documentapproval.document.dto.ApplyDto;
import kakaostyle.documentapproval.document.dto.ApproverDto;
import kakaostyle.documentapproval.document.dto.ApproverDtoComparator;
import kakaostyle.documentapproval.document.dto.DocumentDto;
import kakaostyle.documentapproval.document.exception.*;
import kakaostyle.documentapproval.document.repo.ApproverRepository;
import kakaostyle.documentapproval.document.repo.DocumentRepository;
import kakaostyle.documentapproval.user.KakaoUser;
import kakaostyle.documentapproval.user.KakaoUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DocumentServiceImpl implements DocumentService{

    final private DocumentRepository documentRepository;

    final private KakaoUserRepository kakaoUserRepository;

    final private ApproverRepository approverRepository;

    public List<DocumentDto> getOutboxDocuments(Long userId) throws NoSuchUserException {
        KakaoUser kakaoUser = kakaoUserRepository.findById(userId).orElseThrow(() -> new NoSuchUserException());
        List<Document> documents = documentRepository.findAllByKakaoUserAndDocumentStatus(kakaoUser, DocumentStatus.APPLYING);
        List<DocumentDto> list = new ArrayList<>();

        for(Document document : documents){
            DocumentDto documentDto = DocumentDto.builder()
                    .title(document.getTitle())
                    .content(document.getContent())
                    .documentStatus(document.getDocumentStatus())
                    .approverId(-1L)
                    .build();
            list.add(documentDto);
        }

        return list;
    }

    public List<DocumentDto> getInBoxDocuments(Long userId) throws NoSuchUserException {
        KakaoUser kakaoUser = kakaoUserRepository.findById(userId).orElseThrow(() -> new NoSuchUserException());

        List<Approver> approvers = approverRepository.findAllByUserIdAndIsOrder(userId, true);
        List<DocumentDto> documents = new ArrayList<>();
        for(Approver approver : approvers){
            Document document = approver.getDocument();
            DocumentDto documentDto = DocumentDto.builder()
                    .title(document.getTitle())
                    .content(document.getContent())
                    .approverId(approver.getId())
                    .documentStatus(document.getDocumentStatus())
                    .build();
            documents.add(documentDto);
        }

        return documents;
    }

    public List<DocumentDto> getArchiveDocuments(Long userId) throws NoSuchUserException {
        KakaoUser kakaoUser = kakaoUserRepository.findById(userId).orElseThrow(() -> new NoSuchUserException());
        List<DocumentDto> list = new ArrayList<>();

        List<Approver> approvers = approverRepository.findAllByUserIdAndApproveStatusNot(userId, ApproveStatus.BEFOREAPPROVED);

        for(Approver approver : approvers){
            Document document = approver.getDocument();
            if(document.getDocumentStatus() == DocumentStatus.APPLYING) continue;

            DocumentDto documentDto = DocumentDto.builder()
                    .title(document.getTitle())
                    .content(document.getContent())
                    .documentStatus(document.getDocumentStatus())
                    .approverId(-1L)
                    .build();
            list.add(documentDto);

        }

        return list;
    }




    public Long createDocument(ApplyDto applyDto, Long userId) throws NoSuchApproversException, NoSuchUserException, InvalidCatagoryException, InvalidApproverException {
        List<ApproverDto> approverDtos = applyDto.getApprover();
        if(approverDtos == null || approverDtos.size() == 0){
            throw new NoSuchApproversException();
        }
        if(!isValidApprover(approverDtos)){
            throw new InvalidApproverException();
        }
        if(applyDto.getCatagory() != Catagory.BA && applyDto.getCatagory() != Catagory.PE ){
            throw new InvalidCatagoryException();
        }


        KakaoUser kakaoUser = kakaoUserRepository.findById(userId).orElseThrow(() ->new NoSuchUserException());

        Document document = Document.builder()
                .title(applyDto.getTitle())
                .content(applyDto.getContent())
                .catagory(applyDto.getCatagory())
                .documentStatus(DocumentStatus.APPLYING)
                .kakaoUser(kakaoUser)
                .build();
        Long documentId = documentRepository.save(document).getId();

        boolean isOrder = true;
        boolean isLast = false;
        for(int i = 0; i<approverDtos.size(); i++){
            ApproverDto approverDto = approverDtos.get(i);
            KakaoUser byEmail = kakaoUserRepository.findByEmail(approverDto.getEmail());
            if(approverDto.getPriority() != 1) isOrder = false;
            if(i == approverDtos.size()-1) isLast = true;

            Approver approver = Approver.builder()
                    .name(byEmail.getName())
                    .priority(approverDto.getPriority())
                    .isOrder(isOrder)
                    .isLast(isLast)
                    .approveStatus(ApproveStatus.BEFOREAPPROVED)
                    .userId(byEmail.getId())
                    .document(document)
                    .build();

            approverRepository.save(approver);

        }


        return documentId;
    }

    public String approveDocument(ApproverDto approverDto, Long userId) throws NoSuchUserException, InvalidApproverException, NoSuchDocumentException {
        KakaoUser kakaoUser = kakaoUserRepository.findById(userId).orElseThrow(() ->new NoSuchUserException());
        Approver approver = approverRepository.findByIdAndIsOrder(approverDto.getApproverId(), true).orElseThrow(() -> new InvalidApproverException());
        if(approver.getUserId() != kakaoUser.getId()) throw new NoSuchUserException();

        Document document = approver.getDocument();
        approver.finishApproval(approverDto.getComment(), true);
        approverRepository.save(approver);
        if(approver.isLast()){
            document.finishApproval(true);
            documentRepository.save(document);
            return "completed approval";
        }
        Approver nextApprover = approverRepository.findByDocumentIdAndPriority(document.getId(), approver.getPriority() + 1).orElseThrow(() -> new NoSuchUserException());
        nextApprover.nextApprover();
        approverRepository.save(nextApprover);

        return "finished approve";
    }

    public String rejectDocument(ApproverDto approverDto, Long userId) throws NoSuchUserException, InvalidApproverException, NoSuchDocumentException {
        KakaoUser kakaoUser = kakaoUserRepository.findById(userId).orElseThrow(() ->new NoSuchUserException());
        Approver approver = approverRepository.findByIdAndIsOrder(approverDto.getApproverId(), true).orElseThrow(() -> new InvalidApproverException());
        if(approver.getUserId() != kakaoUser.getId()) throw new NoSuchUserException();


        approver.finishApproval(approverDto.getComment(), false);
        approverRepository.save(approver);

        Document document = approver.getDocument();
        document.finishApproval(false);
        documentRepository.save(document);
        return "rejected approval";

    }

    private boolean isValidApprover(List<ApproverDto> approverDtos) {

        Collections.sort(approverDtos, new ApproverDtoComparator());
        int order = 1;
        for(ApproverDto approverDto : approverDtos){
            if(approverDto.getPriority() == order){
                order++;
            }else{
                return false;
            }
        }


        return true;
    }

}
