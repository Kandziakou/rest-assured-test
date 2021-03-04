package jsonplaceholder;

import lombok.Getter;

public class JSONPlaceholder {
    @Getter private final String baseURI = "http://jsonplaceholder.typicode.com";
    @Getter private final String posts = "/posts";
    @Getter private final String comments = "/comments";
    @Getter private final String users = "/users";
    @Getter private final String post = posts +"/%d";
    @Getter private final String comment = comments + "/%d";
    @Getter private final String user = users + "/%d";
    @Getter private final int postsCount = 100;
    @Getter private final int commentsCount = 500;
    @Getter private final int usersCount = 10;
}