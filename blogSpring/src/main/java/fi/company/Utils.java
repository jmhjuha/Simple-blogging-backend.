package fi.company;

import java.util.logging.Logger;

public class Utils {

    static long MAX_COMMENTS = 100;    // *** Add check.

    public static long makeDbKey(long blogId, long commentId) {
        long dbId = blogId * MAX_COMMENTS + commentId;
        return dbId;
    }

    static long blogIdFromDbKey(long dbId) {
        return ((dbId - (dbId % MAX_COMMENTS))) / MAX_COMMENTS;
    }

    static long commentIdFromDbKey(long dbId) {
        return dbId % MAX_COMMENTS;
    }
}
