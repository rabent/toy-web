package toy.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@Getter
@Setter
public class ItemDTO {
    @NotBlank
    @Range(min=0, max=10000)
    int point;

    @NotBlank
    String tags_str;

    public ItemDTO() {
    }

    public ItemDTO(int point, String tags_str) {
        this.point = point;
        this.tags_str = tags_str;
    }
}
