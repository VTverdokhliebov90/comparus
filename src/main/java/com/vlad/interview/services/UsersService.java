package com.vlad.interview.services;

import com.vlad.interview.controllers.requests.UsersListRequest;
import com.vlad.interview.domain.User;
import com.vlad.interview.repositories.IUsersRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

import static java.util.concurrent.CompletableFuture.supplyAsync;


@Service
@RequiredArgsConstructor
public class UsersService {

    private final List<IUsersRepository> usersRepositories;

    @Qualifier("usersTaskExecutor")
    @NonNull
    private final Executor usersTaskExecutor;

    /**
     * Lists retrieves users from multiple data sources
     * @param usersListRequest - contains resource filtering options
     * @return User's objects list
     */
    public List<User> listUsers(UsersListRequest usersListRequest) {
        var results = new ArrayList<User>();
        var tasks = usersRepositories.stream()
                .map(usersRepository -> supplyAsync(() -> usersRepository.listUsers(usersListRequest), usersTaskExecutor))
                .toList();
        try {
            for (CompletableFuture<List<User>> task : tasks) {
                results.addAll(task.get());

            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            throw new RuntimeException("Failed to list users: ", e.getCause());
        }
        return results;
    }

}
