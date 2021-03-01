package apichecking;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jsonplaceholder.JSONPlaceholder;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class CheckMethods extends JSONPlaceholder{

    private final String posts = getPosts();
    private final String comments = getComments();
    private final String users = getUsers();

    private void get200(String route){
        given()
                .when().get(route)
                .then().statusCode(200);
    }

    private void get404(String route){
        given()
                .when().get(route)
                .then().statusCode(404);
    }

    private Response getJSON(String route){
        return given()
                .when().get(route)
                .then().contentType(ContentType.JSON).extract().response();
    }

    @Test
    public void testGetAllPosts(){
        String body = "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto";
        get200(posts);
        List<HashMap> jsonResponse = getJSON(posts).jsonPath().getList("$");
        Assert.assertEquals(100, jsonResponse.size());
        Assert.assertEquals(jsonResponse.get(0).get("body"), body);
    }

    @Test
    public void testGetOnePost(){
        int index = (int) (Math.random()*100);
        String post = String.format(getPost(), index);
        String notExistedPost = String.format(getPost(), index+100);
        get200(post);
        get404(notExistedPost);
        int id = getJSON(post).jsonPath().get("id");
        Assert.assertEquals(id, index);
    }

    @Test
    public void testGetAllComments(){
        String email = "Eliseo@gardner.biz";
        get200(comments);
        List<HashMap> jsonResponse = getJSON(comments).jsonPath().getList("$");
        Assert.assertEquals(500, jsonResponse.size());
        Assert.assertEquals(jsonResponse.get(0).get("email"), email);
    }

    @Test
    public void testGetOneComment(){
        int index = (int) (Math.random()*500);
        String comment = String.format(getComment(), index);
        String notExistedComment = String.format(getComment(), index+100);
        get200(comment);
        get404(notExistedComment);
        int id = getJSON(comment).jsonPath().get("id");
        Assert.assertEquals(id, index);
    }

    @Test
    public void testGetAllUsers(){
        String email = "Sincere@april.biz";
        get200(users);
        List<HashMap> jsonResponse = getJSON(users).jsonPath().getList("$");
        Assert.assertEquals(10, jsonResponse.size());
        Assert.assertEquals(jsonResponse.get(0).get("email"), email);
    }

    @Test
    public void testGetOneUser(){
        int index = (int) (Math.random()*10);
        String user = String.format(getUser(), index);
        String notExistedUser = String.format(getUser(), index+100);
        get200(user);
        get404(notExistedUser);
        int id = getJSON(user).jsonPath().get("id");
        Assert.assertEquals(id, index);
    }
}
