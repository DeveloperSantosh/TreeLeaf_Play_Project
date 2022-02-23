package controllers;

import models.Blog;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BlogController extends Controller {

    List<Blog> blogs;

    @Inject
    FormFactory formFactory;

    public BlogController() {
        blogs = new ArrayList<>();
        blogs.add(new Blog("My First Blog", "This is content of my first blog."));
        blogs.add(new Blog("My Second Blog", "This is content of my second blog."));
    }

    public Result home(){
        return ok(views.html.blog.home.render(blogs));
    }

    public Result showBlog(String title){
        Optional <Blog> showBlog = Optional.ofNullable(Blog.find.byId(title));
        Blog blog;
        if(showBlog.isPresent()){
            blog = showBlog.get();
            return ok(views.html.blog.show.render(blog));
        }
//        for(Blog blog: blogs){
//            if(blog.title.equals(title)){
//                return ok(views.html.blog.show.render(blog));
//            }
//        }
        return notFound("Sorry! blog not found");
    }

    public Result createBlog(){
        Form<Blog> form = formFactory.form(Blog.class);
        return ok(views.html.blog.create.render(form));
    }

    public Result saveBlog(Http.Request request){
        Form <Blog> blogForm = formFactory.form(Blog.class).bindFromRequest(request);
        Blog blog = blogForm.get();
        blog.save();
//        blogs.add(blog);
        return redirect(routes.BlogController.home());
    }

    public Result deleteBlog(String title){
        Optional<Blog> deleteBlog = Optional.ofNullable(Blog.find.byId(title));
        if(deleteBlog.isPresent()) {
            deleteBlog.get().delete();
            return redirect(routes.BlogController.home());
        }
//        for(Blog blog : blogs){
//            if(blog.title.equals(title)){
//                blogs.remove(blog);
//                return redirect(routes.BlogController.home());
//            }
//        }
        return notFound("Sorry! blog not found");
    }

}
