package toy.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Getter
@Setter
@EqualsAndHashCode
public class Item {
    @NotNull
    Long item_id;
    @NotNull
    String fileName;
    @NotNull
    @Min(0)
    @Max(20000)
    int point;
    LocalDate date;
    List<String> tags;
    String tags_str;

    public Item(Long item_id, String fileName, int point, LocalDate date, String tags_str) {
        this.item_id = item_id;
        this.fileName = fileName;
        this.point = point;
        this.date = date;
        this.tags_str = tags_str;
    }

    public Item(String fileName, int point, LocalDate date, String tags_str) {
        this.fileName = fileName;
        this.point = point;
        this.date = date;
        this.tags_str = tags_str;
    }

    public Item() {
    }

    public List<String> getTags() {
        return Stream.of(tags_str.split(",")).toList();
    }
}
