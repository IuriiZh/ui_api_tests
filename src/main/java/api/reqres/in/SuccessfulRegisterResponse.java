package api.reqres.in;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SuccessfulRegisterResponse {

    private Integer id;
    private String token;

    public SuccessfulRegisterResponse(Integer id, String token) {
        this.id = id;
        this.token = token;
    }

    private SuccessfulRegisterResponse() { }

}
