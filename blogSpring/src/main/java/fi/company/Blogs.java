package fi.company;

import java.util.ArrayList;

public class Blogs {

    private ArrayList<Blog> blogs;

    @Override
    public String toString() {
        return "Blogs{}";
    }

    public Blogs() {
        blogs = new ArrayList<>();
    }

    public Blog add(Blog c) {
        blogs.add(c);
        return c;
    }

    public ArrayList<Blog> getAll() {
        return blogs;
    }

}
