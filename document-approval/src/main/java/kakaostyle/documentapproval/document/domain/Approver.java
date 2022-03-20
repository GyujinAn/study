package kakaostyle.documentapproval.document.domain;

import kakaostyle.documentapproval.BaseTimeEntity;
import kakaostyle.documentapproval.document.domain.Document;
import kakaostyle.documentapproval.user.KakaoUser;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Approver extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "approver_id")
    private Long id;

    private String name;

    private String comment;

    private int priority;

    private boolean isOrder;

    private boolean isLast;

    @Enumerated(EnumType.STRING)
    private ApproveStatus approveStatus;

    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;

    private Long userId;

    public Approver() {

    }

    @Builder
    public Approver(String name, int priority, boolean isOrder, boolean isLast, ApproveStatus approveStatus, Document document, Long userId){
        this.name = name;
        this.priority = priority;
        this.isOrder = isOrder;
        this.isLast = isLast;
        this.approveStatus = approveStatus;
        this.document = document;
        this.userId = userId;
        this.setCreatedDate(LocalDateTime.now());
    }

    public void finishApproval(String comment, boolean b){
        this.comment = comment;
        isOrder = false;
        if(b){
            approveStatus = ApproveStatus.APPROVED;
        }else{
            approveStatus = ApproveStatus.DENIED;
        }

    }

    public void nextApprover(){
        isOrder = true;
    }

}
