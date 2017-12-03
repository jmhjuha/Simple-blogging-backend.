package fi.company;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
// import java.util.logging.Logger;

@Entity
public class Blog {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;  // the 1st entry is the id
    private String title;
    private String body;
    private long commentId;
    private ArrayList <Comment> comments;

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public Blog(long id, String title, String body) {
        // log.info("HIT Blog Constructor id, body");
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    //
    // Add new comment to a blog entry. Return the number of comments.
    //
    public long addComment(String text) {
        Comment c = new Comment(text, this.id, nextCommentId());
        this.comments.add(c);
        return this.comments.size();
    }

    public String getCommentById(long commentId) {
        for (Comment c : comments) {
            if (c.getBlogId() == commentId) {
                return c.getBody();
            }
        }
        return null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long nextCommentId() {
        return ++commentId;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public Blog() {
        // log.info("HIT Blog default constructor");
        this.comments = new ArrayList<>();
    }
}

