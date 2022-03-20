package kakaostyle.documentapproval.document.repo;

import kakaostyle.documentapproval.document.domain.ApproveStatus;
import kakaostyle.documentapproval.document.domain.Approver;
import kakaostyle.documentapproval.document.domain.Document;
import kakaostyle.documentapproval.user.KakaoUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApproverRepository extends JpaRepository<Approver, Long> {



    List<Approver> findAllByUserIdAndIsOrder(Long userId, boolean isOrder);

    List<Approver> findAllByUserIdAndApproveStatusNot(Long userId, ApproveStatus approveStatus);

    Optional<Approver> findByIdAndIsOrder(Long approverId, boolean isOrder);

    Optional<Approver> findByDocumentIdAndPriority(Long documentId, int priority);

    Optional<Approver> findByUserId(Long userId);
}
