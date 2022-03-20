package kakaostyle.documentapproval.document.dto;


import kakaostyle.documentapproval.document.domain.Catagory;
import kakaostyle.documentapproval.document.domain.DocumentStatus;
import lombok.Builder;
import lombok.Getter;
import java.util.List;


@Getter
@Builder
public class ApplyDto {

    private String title;

    private String content;

    private List<ApproverDto> approver;

    private Catagory catagory;

}
