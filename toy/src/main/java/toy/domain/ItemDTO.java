package toy.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@Getter
@Setter
public class ItemDTO {
    @NotNull
    @Min(0)
    @Max(20000)
    int point;

    String tags_str;

    public ItemDTO() {
    }

    public ItemDTO(int point, String tags_str) {
        this.point = point;
        this.tags_str = tags_str;
    }
}
