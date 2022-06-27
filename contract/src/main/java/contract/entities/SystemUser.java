package contract.entities;

import contract.validation.FieldMatch;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
public class SystemUser implements Serializable {
    @NotNull(message = "please, fill field")
    @Size(min = 3, message = "username length must be greater than 2 symbols")
    private String userName;

    @NotNull(message = "please, fill field")
    @Size(min = 1, message = "password had to contain at least 1 symbol")
    private String password;

    @NotNull(message = "please, fill field")
    @Size(min = 1, message = "password had to contain at least 1 symbol")
    private String matchingPassword;

    @NotNull(message = "please, fill field")
    @Size(min = 1, message = "first name had to contain at least 1 symbol")
    private String firstName;

    @NotNull(message = "please, fill field")
    @Size(min = 1, message = "last name had to contain at least 1 symbol")
    private String lastName;

    @NotNull(message = "please, fill field")
    @Size(min = 1, message = "email name had to contain at least 1 symbol")
    @Email
    private String email;

    private List<Role> roles;
    
    private Long id;
}
