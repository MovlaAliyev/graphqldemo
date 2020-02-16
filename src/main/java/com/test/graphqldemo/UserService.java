package com.test.graphqldemo;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotation.GraphQLApi;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@GraphQLApi
@Service
public class UserService {

    private UserRepo userRepo;

    public UserService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @GraphQLQuery
    public Iterable<User> getAllUsers(){
        return userRepo.findAll ();
    }

    @GraphQLMutation(name = "saveUser")
    public User saveUser(@GraphQLArgument (name = "user") User user){
        return userRepo.save (user);
    }
}
