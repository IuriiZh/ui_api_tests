package api.reqres_in;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SuccessfulRegisterRequest {

    private String username;
    private String email;
    private String password;

    public SuccessfulRegisterRequest(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    private SuccessfulRegisterRequest() {
    }

}
