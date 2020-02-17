package com.test.graphqldemo;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLSubscription;
import io.leangen.graphql.spqr.spring.annotation.GraphQLApi;
import io.leangen.graphql.spqr.spring.util.ConcurrentMultiRegistry;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.ArrayList;
import java.util.List;

@GraphQLApi
@Service
public class UserService {

    private UserRepo userRepo;

    private final ConcurrentMultiRegistry<String, FluxSink<User>> subscribers = new ConcurrentMultiRegistry<> ();


    public UserService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @GraphQLQuery
    public Iterable<User> getAllUsers(){
        return userRepo.findAll ();
    }

    @GraphQLMutation(name = "saveUser")
    public User saveUser(@GraphQLArgument (name = "user") User user){
        User u = userRepo.save (user);
        subscribers.get("user").forEach(subscriber -> subscriber.next(u));
        return u;
    }

    @GraphQLSubscription
    public Publisher<User> userAdded(String key) {
        return Flux.create(subscriber -> subscribers.add(key, subscriber.onDispose(() -> subscribers.remove(key, subscriber))), FluxSink.OverflowStrategy.LATEST);
    }
}
