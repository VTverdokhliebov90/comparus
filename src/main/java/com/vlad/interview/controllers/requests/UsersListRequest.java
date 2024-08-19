package com.vlad.interview.controllers.requests;

import lombok.Getter;
import lombok.Value;

import java.util.Optional;


@Value(staticConstructor = "of")
@Getter
public class UsersListRequest {
    Optional<String> search;

}
