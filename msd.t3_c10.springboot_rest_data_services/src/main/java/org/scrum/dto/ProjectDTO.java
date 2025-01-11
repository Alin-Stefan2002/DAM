package org.scrum.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data @EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor @NoArgsConstructor
public class ProjectDTO {
    @EqualsAndHashCode.Include
    @NonNull private Integer projectNo;
    @NonNull private String name;
    private Date startDate;
    //
    private List<ReleaseDTO> releases = new ArrayList<>();
    //
}
