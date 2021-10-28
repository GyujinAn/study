package section7;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * @author gj.an@okestro.com
 * @since 2021/07/09
 */


@MappedSuperclass
public abstract class BaseEntity {
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifedBy;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifedBy() {
        return lastModifedBy;
    }

    public void setLastModifedBy(String lastModifedBy) {
        this.lastModifedBy = lastModifedBy;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    private LocalDateTime lastModifiedDate;

}
