package fi.company;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // the 1st entry is the @id.
    protected long id;
    private long commentId;
    private long blogId;
    private String body;


    protected long setDatabaseKey() {
        this.id = Utils.makeDbKey(this.blogId, this.commentId);
        return this.id;
    }

    public Comment(String body, long blogId, long commentId) {
        this.setBody(body);
        this.setBlogId(blogId);
        this.setCommentId(commentId);
        this.setDatabaseKey();
    }

    public Comment() {
        this.setBody("No comments");
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getBlogId() {
        return this.blogId;
    }

    public void setBlogId(long id) {
        this.blogId = id;
    }

    public long getCommentId() {
        return this.commentId;
    }

    public void setCommentId(long id) {
        this.commentId = id;
    }

    @Override
    public String toString() {
        return "Blog comment{" +
                "id=" + id +
                ", commentId=" + commentId +
                ", blogId=" + blogId +
                ", body='" + body + '\'' +
                '}';
    }
}
