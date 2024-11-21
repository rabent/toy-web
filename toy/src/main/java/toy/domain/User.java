package toy.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Stream;

@Getter
@Setter
@EqualsAndHashCode
public class User {
    @NotNull
    @Size(min=5, max=12)
    String user_id;
    @NotNull
    @Size(min=5, max=15)
    String password;
    @NotNull
    @Size(min=5, max=12)
    String name;
    @Max(1000000)
    @Min(0)
    int point=10000;
    List<Long> items;
    String items_str;

    public User() {
    }

    public User(String user_id, String password, String name) {
        this.user_id = user_id;
        this.password = password;
        this.name = name;
    }

    public List<String> getItems() {
        if(items_str!=null) return Stream.of(items_str.split(",")).toList();
        else return null;
    }
}
