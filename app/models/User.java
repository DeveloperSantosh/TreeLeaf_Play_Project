package models;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.List;

@Entity
public class User extends Model {

    @Id
    @Constraints.Required
    public Integer id;

    @Constraints.Required
    public String name;

    @Constraints.Required
    public String surname;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Blog> blogs;

    public User() {
    }

    public User(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public static Finder<Integer, User> find = new Finder<>(User.class);

}

