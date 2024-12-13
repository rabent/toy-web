package toy.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class UserRegisterDTO {
    @NotBlank
    @Size(min=5, max=12)
    String user_id;

    @NotBlank
    @Size(min=5, max=15)
    String password;

    @NotBlank
    @Size(min=5, max=12)
    String name;

    public UserRegisterDTO() {
    }

    public UserRegisterDTO(String user_id, String password, String name) {
        this.user_id = user_id;
        this.password = password;
        this.name=name;
    }
}
