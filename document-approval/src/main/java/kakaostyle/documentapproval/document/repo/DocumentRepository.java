package kakaostyle.documentapproval.document.repo;


import kakaostyle.documentapproval.document.domain.Document;
import kakaostyle.documentapproval.document.domain.DocumentStatus;
import kakaostyle.documentapproval.user.KakaoUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    List<Document> findAllByKakaoUserAndDocumentStatus(KakaoUser kakaoUser, DocumentStatus documentStatus);

    Optional<Document> findAllByKakaoUser(KakaoUser kakaoUser);
}
