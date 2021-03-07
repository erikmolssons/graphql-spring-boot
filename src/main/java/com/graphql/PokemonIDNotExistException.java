package com.graphql;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PokemonIDNotExistException extends RuntimeException implements GraphQLError {

    private final String id;

    public PokemonIDNotExistException(String id) {
        super(String.format("The Pokemon ID provided doesn't exist: %s", id));
        this.id = id;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return null;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return Collections.singletonMap("id", this.id);
    }

}
