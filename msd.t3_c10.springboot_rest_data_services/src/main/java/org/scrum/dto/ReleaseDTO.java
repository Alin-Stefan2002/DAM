package org.scrum.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class ReleaseDTO {
    @EqualsAndHashCode.Include
    private Integer releaseId;
    private String codeName; // NEW born
    private Date publishDate;
    //
    public ReleaseDTO(String codeName, Date publishDate) {
        this.codeName = codeName;
        this.publishDate = publishDate;
    }
}
