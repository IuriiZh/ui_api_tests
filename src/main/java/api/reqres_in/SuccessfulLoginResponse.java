package api.reqres_in;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SuccessfulLoginResponse {

    private String token;

    public SuccessfulLoginResponse(String token) { this.token = token; }

    private SuccessfulLoginResponse() {}

}
