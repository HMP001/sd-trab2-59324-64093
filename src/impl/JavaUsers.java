package impl;

import api.User;
import api.java.Content;
import api.java.Image;
import api.java.Result;
import api.java.Users;
import org.hibernate.Session;

import java.util.List;
import java.util.logging.Logger;

import static api.java.Result.ErrorCode.*;
import static api.java.Result.*;

public class JavaUsers implements Users {

    private static final Logger log = Logger.getLogger(JavaUsers.class.getName());

    private Image images;

    private Content contents;

    private final Hibernate db = Hibernate.getInstance();

    public void setImages(Image images) {
        this.images = images;
    }

    public void setContents(Content contents) {
        this.contents = contents;
    }

    @Override
    public Result<String> createUser(User u) {
        log.info("createUser(user -> %s)\n".formatted(u));
        if (!inputHasAllFields(u))
            return error(BAD_REQUEST);
        if (u.getAvatarUrl() != null && u.getAvatarUrl().isEmpty())
            u.setAvatarUrl(null);
        try {
            db.persist(u);
        } catch (Exception e) {
            log.warning(e.getMessage());
            return error(CONFLICT);
        }
        return ok(u.getUserId());
    }

    // Avatar can be null
    private boolean inputHasAllFields(User u) {
        return u.getUserId() != null && !u.getUserId().isEmpty() &&
                u.getPassword() != null && !u.getPassword().isEmpty() &&
                u.getEmail() != null && !u.getEmail().isEmpty() &&
                u.getFullName() != null && !u.getFullName().isEmpty();
    }

    @Override
    public Result<User> getUser(String uid, String pwd) {
        log.info("getUser(uid -> %s, pwd -> %s)\n".formatted(uid, pwd));
        if (uid == null)
            return error(BAD_REQUEST);
        if (pwd == null)
            return error(FORBIDDEN);
        var u = db.get(User.class, uid);
        if (u == null)
            return error(NOT_FOUND);
        if (!u.getPassword().equals(pwd))
            return error(FORBIDDEN);
        return ok(u);
    }

    @Override
    public Result<User> updateUser(String uid, String pwd, User updatedFields) {
        log.info("updateUser(uid -> %s, pwd -> %s, updatedFields -> %s)\n".formatted(uid, pwd, updatedFields));
        if (uid == null)
            return error(BAD_REQUEST);
        if (pwd == null)
            return error(FORBIDDEN);
        return db.execTransaction(s -> updateUserTx(uid, pwd, updatedFields, s));
    }

    // In a transaction, addresses the interleaving where a user is found in this thread but deleted/updated in another before being updated here
    private static Result<User> updateUserTx(String uid, String pwd, User updatedFields, Session s) {
        var res = getUserInTx(uid, pwd, s);
        if (!res.isOK())
            return res;
        var u = res.value();
        if (updatedFields.getFullName() != null && !updatedFields.getFullName().isEmpty())
            u.setFullName(updatedFields.getFullName());
        if (updatedFields.getPassword() != null && !updatedFields.getPassword().isEmpty())
            u.setPassword(updatedFields.getPassword());
        if (updatedFields.getEmail() != null && !updatedFields.getEmail().isEmpty())
            u.setEmail(updatedFields.getEmail());
        if (updatedFields.getAvatarUrl() != null && !updatedFields.getAvatarUrl().isEmpty())
            u.setAvatarUrl(updatedFields.getAvatarUrl());
        s.merge(u);
        return ok(u);
    }

    @Override
    public Result<User> deleteUser(String uid, String pwd) {
        log.info("deleteUser(uid -> %s, pwd -> %s)\n".formatted(uid, pwd));
        if (uid == null)
            return error(BAD_REQUEST);
        if (pwd == null)
            return error(FORBIDDEN);
        var res = db.execTransaction(s -> deleteUserTx(uid, pwd, s));
        if (!res.isOK())
            return res;
        var u = res.value();
        tryToDeleteAvatar(u);
        contents.forgetUser(uid);
        return ok(u);
    }

    private void tryToDeleteAvatar(User u) {
        if (u.getAvatarUrl() != null) {
            var splitAvatar = u.getAvatarUrl().split("/");
            var iid = splitAvatar[splitAvatar.length - 1];
            images.deleteImageUponUserOrPostRemoval(u.getUserId(), iid);
        }
    }

    // Could not be necessary because remove does not fail when object is not found.
    // However, there is no consistent cut where two deletes can be successful
    private static Result<User> deleteUserTx(String uid, String pwd, Session s) {
        var res = getUserInTx(uid, pwd, s);
        if (!res.isOK())
            return error(res.error());
        var u = res.value();
        s.remove(u);
        return res;
    }

    private static Result<User> getUserInTx(String uid, String pwd, Session s) {
        var u = s.get(User.class, uid);
        if (u == null)
            return error(NOT_FOUND);
        if (!u.getPassword().equals(pwd))
            return error(FORBIDDEN);
        return ok(u);
    }

    @Override
    public Result<List<User>> searchUsers(String pattern) {
        log.info("searchUsers(pattern -> %s)\n".formatted(pattern));
        pattern = pattern == null ? "" : pattern;
        List<User> users = db.sql("SELECT * FROM User u WHERE UPPER(u.fullName) LIKE UPPER('%" + pattern +"%')", User.class);
        return ok(users.stream().map(this::hidePwd).toList());
    }

    private User hidePwd(User u) {
        return new User(u.getUserId(), u.getFullName(), u.getEmail(), null, u.getAvatarUrl());
    }

}
