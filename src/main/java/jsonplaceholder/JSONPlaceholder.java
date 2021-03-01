package jsonplaceholder;

import lombok.Getter;

public class JSONPlaceholder {
    private final String baseUrl = "http://jsonplaceholder.typicode.com";
    @Getter private final String
            posts = baseUrl + "/posts";
    @Getter private final String
            comments = baseUrl + "/comments";
    @Getter private final String
            users = baseUrl + "/users";
    @Getter private final String
            post = posts +"/%d";
    @Getter private final String
            comment = comments + "/%d";
    @Getter private final String
            user = users + "/%d";
}
