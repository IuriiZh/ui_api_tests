package api.reqres.in;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreatedUserResponse {

    private String name;
    private String job;
    private Integer id;
    private String createdAt;

    public CreatedUserResponse(String name, String job, Integer id, String createdAt) {
        this.name = name;
        this.job = job;
        this.id = id;
        this.createdAt = createdAt;
    }

    private CreatedUserResponse() {
    }

}
