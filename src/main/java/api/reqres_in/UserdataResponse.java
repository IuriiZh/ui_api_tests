package api.reqres_in;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserdataResponse {

    private Integer id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;

    public UserdataResponse(Integer id, String email, String first_name, String last_name, String avatar) {
        this.id = id;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.avatar = avatar;
    }

    private UserdataResponse() {
    }

}
