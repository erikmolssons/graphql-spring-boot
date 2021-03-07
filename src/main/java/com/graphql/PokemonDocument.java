package com.graphql;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "pokemon")
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonDocument {
    @Id
    private String id;
    private String name;
    private String height;
    private String weight;
    private Sprites sprites;
    private List<PokemonType> types;

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Sprites {
        private String front_default;
        private String back_default;
        private String front_shiny;
        private String back_shiny;
    }

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PokemonType {
        private Type type;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @Data
        public static class Type {
            private String name;
        }
    }
}
