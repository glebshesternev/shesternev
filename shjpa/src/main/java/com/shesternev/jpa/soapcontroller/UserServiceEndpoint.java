package com.shesternev.jpa.soapcontroller;

import com.shesternev.jpa.model.User;
import com.shesternev.jpa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import user.GetUserRequest;
import user.GetUserResponse;

@Endpoint
@RequiredArgsConstructor
public class UserServiceEndpoint {

    private final String NAMESPACE = "user";

    private final UserService userService;

    @PayloadRoot(namespace = NAMESPACE, localPart = "getUserRequest")
    @ResponsePayload
    public GetUserResponse getUser(@RequestPayload GetUserRequest request) {
        GetUserResponse response = new GetUserResponse();
        User user = userService.getUserById(request.getId());
        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setSecondName(user.getLastName());
        return response;
    }
}
