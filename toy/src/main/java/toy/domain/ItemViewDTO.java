package toy.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
public class ItemViewDTO {
    Long id;

    @NotNull
    @Range(min=0, max=10000)
    int point;

    @NotNull
    String tags_str;

    @NotNull
    String fileName;

    public ItemViewDTO() {
    }

    public ItemViewDTO(Long id,int point, String tags_str, String fileName) {
        this.id=id;
        this.point = point;
        this.tags_str = tags_str;
        this.fileName=fileName;
    }
}
