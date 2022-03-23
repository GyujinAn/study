package kakaostyle.documentapproval.document.domain;


import kakaostyle.documentapproval.BaseTimeEntity;
import kakaostyle.documentapproval.user.KakaoUser;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Document extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @Enumerated(EnumType.STRING)
    private DocumentStatus documentStatus;

    @Enumerated(EnumType.STRING)
    private Catagory catagory;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private KakaoUser kakaoUser;

    public Document() {

    }

    @Builder
    public Document(String title, String content, DocumentStatus documentStatus, Catagory catagory, KakaoUser kakaoUser){
        this.title = title;
        this.content = content;
        this.documentStatus = documentStatus;
        this.catagory = catagory;
        this.kakaoUser = kakaoUser;

        this.setCreatedDate(LocalDateTime.now());
    }

    public void finishApproval(boolean isApproved){
        if(isApproved == true) documentStatus = DocumentStatus.APPROVED;
        if(isApproved == false) documentStatus = DocumentStatus.REJECTED;
    }

}

