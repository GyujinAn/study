package kakaostyle.documentapproval.document.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApproverDto{

    private Long userId;

    private String email;

    private Integer priority;

    private Long approverId;

    private String comment;

}