package toy.domain;

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
public class UserDTO {
    @NotNull
    @Size(min=5, max=12)
    String user_id;

    @NotNull
    @Size(min=5, max=15)
    String password;

    public UserDTO() {
    }

    public UserDTO(String user_id, String password) {
        this.user_id = user_id;
        this.password = password;
    }
}
