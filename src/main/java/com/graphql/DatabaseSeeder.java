package com.graphql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.URI;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@Component
public class DatabaseSeeder {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseSeeder.class);

    private final PokemonClient client;
    private final PokemonRepository repository;

    @Autowired
    public DatabaseSeeder(PokemonClient client, PokemonRepository repository) {
        this.client = client;
        this.repository = repository;
    }

    @PostConstruct
    public void postConstruct() throws Exception {
        LOGGER.info("Seeding Database...");
        long start = System.nanoTime();
        var uris = new ArrayList<URI>();
        for (int i = 1; i < 152; i++) {
            uris.add(new URI("https://pokeapi.co/api/v2/pokemon/" + i));
        }
        this.repository.saveAll(this.client.sendAsync(uris));
        long end = System.nanoTime();
        var result = TimeUnit.NANOSECONDS.toSeconds(end - start);
        LOGGER.info(String.format("Finished seeding database in %d seconds", result));
    }

    @PreDestroy
    public void destroy() {
        this.repository.deleteAll();
    }
}
