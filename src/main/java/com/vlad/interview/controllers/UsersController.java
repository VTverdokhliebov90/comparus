package com.vlad.interview.controllers;

import com.vlad.interview.controllers.requests.UsersListRequest;
import com.vlad.interview.domain.User;
import com.vlad.interview.services.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Tag(name = "Users", description = "Users management APIs")
@RestController
@RequestMapping("${application.apiPrefix}/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @Operation(
            summary = "Retrieve a list of users",
            description = "List of users. The response is an array of objects containing users information.",
            tags = {"users", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = User.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping
    public List<User> listUsers(@RequestParam Optional<String> search) {
        return usersService.listUsers(UsersListRequest.of(search));
    }

}
