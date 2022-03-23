package kakaostyle.documentapproval.document.dto;

import java.util.Comparator;

public class ApproverDtoComparator implements Comparator<ApproverDto> {
    @Override
    public int compare(ApproverDto o1, ApproverDto o2) {
        if(o1.getPriority() > o2.getPriority()) return 1;
        if(o1.getPriority() < o2.getPriority()) return -1;

        return 0;
    }
}
