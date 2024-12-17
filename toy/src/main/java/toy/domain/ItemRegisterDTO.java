package toy.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
public class ItemRegisterDTO {
    @NotNull
    @Range(min=0, max=10000)
    int point;

    @NotBlank
    String tags_str;

    public ItemRegisterDTO() {
    }

    public ItemRegisterDTO(int point, String tags_str) {
        this.point = point;
        this.tags_str = tags_str;
    }
}
