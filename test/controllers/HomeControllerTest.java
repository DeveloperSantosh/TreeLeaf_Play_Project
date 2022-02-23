package controllers;

import models.Blog;
import models.User;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;

public class HomeControllerTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void testIndex() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }

    @Test
    public void testBlogCreation(){
        Blog blog = new Blog("My blog","This is a test blog.");
        blog.save();
        List<Blog> blogs = Blog.find.all();
        assertNotNull(blogs);
    }

    @Test
    public void testUserCreation(){
        User user = new User(1, "Santosh", "Mahato");
        assert  user != null;
        user.save();
        List<User> users = User.find.all();
        assertNotNull(users);
    }

    @Test
    public void testUserDeletion(){
        User user = new User(1, "Santosh", "Mahato");
        assert  user != null;
        user.save();
        user.delete();
        User deletedUser = User.find.byId(user.id);
        assert deletedUser == null;
    }

    @Test
    public void testUserUpdate(){
        User user = new User(1, "Santosh", "Mahato");
        assert  user != null;
        user.save();
        user.setName("firstname");
        user.setSurname("lastname");
        user.update();
        User updatedUser = User.find.byId(1);
        assert updatedUser != null;
        assert updatedUser.name.equals("firstname");
        assert updatedUser.surname.equals("lastname");
    }

    @Test
    public void testOneToToneMapping(){
        User user = new User(1, "Santosh", "Mahato");
        assert  user != null;
        user.save();
        Blog blog = new Blog("MY First Blog", "This is content of my first blog");
        blog.setAuthor(user);
        user.delete();
        List<Blog> blogs = Blog.find.all();
        for(Blog b: blogs){
            assert !b.getAuthor().equals(user);
        }
    }

}
