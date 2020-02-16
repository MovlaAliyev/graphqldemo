package com.test.graphqldemo;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.query.AnnotatedResolverBuilder;
import io.leangen.graphql.metadata.strategy.value.jackson.JacksonValueMapperFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

//@RestController
public class GraphQlController {
   /* private final GraphQL graphQL;

    public GraphQlController(UserService userService){
        GraphQLSchema schema =  new GraphQLSchemaGenerator ()
                .withResolverBuilders (
                        new AnnotatedResolverBuilder ()
                ).withOperationsFromSingleton (userService)
                .withValueMapperFactory (
                        new JacksonValueMapperFactory ()
                ).generate ();
        graphQL = GraphQL.newGraphQL (schema).build ();
    }

    @PostMapping(value = "/graphql")
    public Map<String, Object> graphql(@RequestBody Map<String, String> request, HttpServletRequest raw){
        ExecutionResult executionResult = graphQL.execute (
                ExecutionInput.newExecutionInput().query (
                        request.get ("query")
                ).operationName (request.get ("operationName"))
                .context (raw)
                .build ()
        );
        return executionResult.toSpecification ();
    }*/
}
