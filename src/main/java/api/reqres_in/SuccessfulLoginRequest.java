package api.reqres_in;

import lombok.Getter;
import lombok.Setter;
import org.junit.Assert;
import static org.hamcrest.Matchers.equalTo;
import static api.setup.RequestSpecs.request;
import static utils.PropertyReader.*;

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
