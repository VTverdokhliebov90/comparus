package com.comparus.interview.repositories;


import com.comparus.interview.controllers.requests.UsersListRequest;
import com.comparus.interview.domain.User;

import java.util.List;

public interface IUsersRepository {

    List<User> listUsers(UsersListRequest usersListRequest);

}
