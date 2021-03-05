package apichecking;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jsonplaceholder.JSONPlaceholder;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class CheckMethods extends BaseTest{
    private final JSONPlaceholder jsonPlaceholder = new JSONPlaceholder();

    private final String posts = jsonPlaceholder.getPosts();
    private final String comments = jsonPlaceholder.getComments();
    private final String users = jsonPlaceholder.getUsers();
    private final String URI = jsonPlaceholder.getBaseURI();
    private final String post = jsonPlaceholder.getPost();
    private final String comment = jsonPlaceholder.getComment();
    private final String user = jsonPlaceholder.getUser();

    private Response getJSON(String route){
        return given()
                .when().get(route)
                .then().statusCode(200).contentType(ContentType.JSON).extract().response();
    }

    private void get404(String path){
        given()
                .when().get(URI + path)
                .then().statusCode(404);
    }

    @Test
    public void testGetAllPosts(){
        String body = "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto";
        List<HashMap> jsonResponse = getJSON(URI + posts).jsonPath().getList("$");
        Assert.assertEquals(jsonPlaceholder.getPostsCount(), jsonResponse.size());
        Assert.assertEquals(jsonResponse.get(0).get("body"), body);
    }

    @Test
    public void testGetOnePost(){
        int index = (int) (Math.random() * jsonPlaceholder.getPostsCount());
        String validPost = String.format(post, index);
        int id = getJSON(URI + validPost).jsonPath().get("id");
        Assert.assertEquals(id, index);

        String invalidPost = String.format(post, index + jsonPlaceholder.getPostsCount());
        get404(invalidPost);
    }

    @Test
    public void testGetAllComments(){
        String email = "Eliseo@gardner.biz";
        List<HashMap> jsonResponse = getJSON(URI + comments).jsonPath().getList("$");
        Assert.assertEquals(jsonPlaceholder.getCommentsCount(), jsonResponse.size());
        Assert.assertEquals(jsonResponse.get(0).get("email"), email);
    }

    @Test
    public void testGetOneComment(){
        int index = (int) (Math.random()* jsonPlaceholder.getCommentsCount());
        String validComment = String.format(comment, index);
        int id = getJSON(URI + validComment).jsonPath().get("id");
        Assert.assertEquals(id, index);

        String invalidComment = String.format(comment, index + jsonPlaceholder.getCommentsCount());
        get404(invalidComment);
    }

    @Test
    public void testGetAllUsers(){
        String email = "Sincere@april.biz";
        List<HashMap> jsonResponse = getJSON(URI + users).jsonPath().getList("$");
        Assert.assertEquals(jsonPlaceholder.getUsersCount(), jsonResponse.size());
        Assert.assertEquals(jsonResponse.get(0).get("email"), email);
    }

    @Test
    public void testGetOneUser(){
        int index = (int) (Math.random()* jsonPlaceholder.getUsersCount());
        String validUser = String.format(user, index);
        int id = getJSON(URI + validUser).jsonPath().get("id");
        Assert.assertEquals(id, index);

        String invalidUser = String.format(user, index + jsonPlaceholder.getUsersCount());
        get404(invalidUser);
    }
}
