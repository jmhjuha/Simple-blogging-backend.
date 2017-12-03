package fi.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.logging.Logger;


@RestController
public class MyRestController {

    String  s = getClass().getSimpleName();

    private final Logger log = Logger.getLogger(" --JmH. - - " + s + " - -");

    @Autowired
    BlogRepository       blogDataBase;
    @Autowired
    CommentRepository commentDataBase;

    @PostConstruct
    public void init() {
        log.info("Hit init()");
        // called when spring has created the bean
    }

    //------------------------------------------------------------------
    // P O S T one blog entry.
    //
    // curl -X POST -H "Content-Type: application/json" -d '{"title":"True story","body":"One day..."}' http://localhost:8080/...
    //
    // Note that after inserting entry you get id which van be used to access the entry.
    //
    //
    @RequestMapping(value = "/blogs",  method = RequestMethod.POST)
    public Blog saveCustomer(@RequestBody Blog b) {
        b = blogDataBase.save(b);
        log.info("Added new blog: [" + b.getId() + "]");
        log.info("         title: [" + b.getTitle() + "]");
        log.info("          body: [" + b.getBody() + "]");
        return b;
    }

    //------------------------------------------------------------------
    // P O S T one comment to known blog entry.
    //
    // curl -X POST -H "..." -d '{"body":"True story..."}' http://localhost:8080/blogs/1/comments/
    //
    // Note that after inserting entry you get id which can be used to access the entry.
    //
    @RequestMapping(value = "/blogs/{blogId}/comments",  method = RequestMethod.POST)
    public Comment saveBlogComment(@RequestBody Comment c, @PathVariable long blogId) {
        log.info("saveBlogComment()");
        Blog b = blogDataBase.findOne(blogId);
        // b.addComment("Testi");
        // blogDataBase.save(b);
        if (b != null) {
            long nextCommentId = b.nextCommentId();
            c.setBlogId(blogId);
            c.setCommentId(nextCommentId);
            // c.setDatabaseKey();
            if (c.getCommentId() == 0) {
                log.info("Something wrong in saveBlogComment");
            } else {
                c = commentDataBase.save(c);
                log.info("Comment to blog Id: [" + blogId + "]");
                log.info("  comment Id in DB: [" + c.getCommentId() + "]");
                log.info("              body: [" + c.getBody() + "]");
            }
        }
        return c;
    }

    //------------------------------------------------------------------
    // G E T known blog known comment by their id's.
    //
    // curl -X GET http://localhost:8080/blogs/1/comments/2
    //
    //
    @RequestMapping(value = "/blogs/{blogId}/comments/{commentId}", method = RequestMethod.GET)
    public Comment getBlogComment(@PathVariable long blogId, @PathVariable long commentId) {
        log.info("getBlogComment()");
        // long dbKey = Utils.makeDbKey(blogId, commentId);
        // Comment c = commentDataBase.findOne(dbKey);
        Blog b = blogDataBase.findOne(blogId);
        if (b != null) {
            // Collect all commment having the same glog and comment id.
            // Missing findAllById() in Derby?
            Iterable<Comment> commentList = commentDataBase.findAll();  // *** Brute force
            for (Comment c : commentList) {
                if ((blogId == c.getBlogId()) && (commentId == c.getCommentId())) {
                    return c;
                }
            }
        }
        return null;
    }

    //------------------------------------------------------------------
    // G E T - all known blog comments.
    //
    @RequestMapping(value = "/blogs/{blogId}/comments/", method = RequestMethod.GET)
    public ArrayList<Comment> getAllBlogComments(@PathVariable long blogId) {
        log.info("getAllBlogComments()");
        ArrayList<Comment> blogCommentList = new ArrayList<Comment>();
        Blog b = blogDataBase.findOne(blogId);
        if (b != null) {
            // Collect all commment having the same glog id. Missing findAllById() in Derby?
            Iterable<Comment> commentList = commentDataBase.findAll();
            for (Comment c : commentList) {
                if (blogId == c.getBlogId()) {
                    blogCommentList.add(c);
                }
            }
        }
        return blogCommentList;
    }

    //------------------------------------------------------------------
    // G E T - all blogs
    //
    @RequestMapping(value = "/blogs",  method = RequestMethod.GET)
    public Iterable<Blog> getAllBlogs() {
        log.info("getAllBlogs()");
        Iterable<Blog> allBlogs = blogDataBase.findAll();
        return allBlogs;
    }

    //------------------------------------------------------------------
    // G E T - known blog by it's blog id.
    //
    @RequestMapping(value = "/blogs/{blogId}",  method=RequestMethod.GET)
    public Blog getBlogById(@PathVariable long blogId) {
        log.info("getBlogById()");
        Blog b = blogDataBase.findOne(blogId);
        return b;
    }

    //------------------------------------------------------------------
    // D E L E T E - known blog with blog id.
    // *** Not sure if deleting from database using this interface should be allowed at all.
    // *** Anyway, should delete all related comments as well and it's not implemented here.
    // *** Currently this is only for a reference.
    //
    @RequestMapping(value = "/blogs/{blogId}",  method=RequestMethod.DELETE)
    public void deleteBlogById(@PathVariable long blogId) {
        if (null == blogDataBase.findOne(blogId)) {
            log.info("deleteBlogById() - Delete failed. Id [" + blogId + "] not found.");
            return;
        }
        log.info("deleteBlogById() - Deleted blog: [" + blogId + "]");
        log.info("   *** Remember to delete blog comments as well. ***");
        blogDataBase.delete(blogId);
    }

    public MyRestController() {};
}