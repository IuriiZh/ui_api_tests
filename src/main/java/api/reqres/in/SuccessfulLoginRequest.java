package api.reqres.in;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SuccessfulLoginRequest {

    private String email;
    private String password;

    public SuccessfulLoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    private SuccessfulLoginRequest() {}

}
