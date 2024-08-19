package com.vlad.interview.repositories;


import com.vlad.interview.controllers.requests.UsersListRequest;
import com.vlad.interview.domain.User;

import java.util.List;

public interface IUsersRepository {

    List<User> listUsers(UsersListRequest usersListRequest);

}
