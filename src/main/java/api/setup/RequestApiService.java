package api.setup;

import api.reqres.in.UserdataResponse;

public class RequestApiService {
    public RequestApiService getSingleUser(String id, int status){
        UserdataResponse user = RequestSpecs.request(status).get("/api/users/" + id).then().statusCode(200).extract().as(UserdataResponse.class);
        String userEmail = user.getEmail();
        return this;
    }
}
