package com.vlad.interview.domain;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class User {
    private String id;
    private String username;
    private String name;
    private String surname;

}
