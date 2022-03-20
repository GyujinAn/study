package kakaostyle.documentapproval.document.dto;

import kakaostyle.documentapproval.document.domain.ApproveStatus;
import kakaostyle.documentapproval.document.domain.DocumentStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Getter
@Builder
public class DocumentDto {

    private String title;

    private String content;

    private DocumentStatus documentStatus;

    private Long approverId;

    private List<String> comment;
}
