package impl;

import api.java.Image;
import api.java.Result;
import api.java.Users;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.core.UriBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Logger;

import static api.java.Result.ErrorCode.*;
import static api.java.Result.*;

public class JavaImage implements Image {

    private static final Logger log = Logger.getLogger(JavaImage.class.getName());

    private static final String IMAGES_DIR = "./media";

    private Users users;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public JavaImage() {
        try {
            Files.createDirectories(Paths.get(IMAGES_DIR));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public Result<String> createImage(String uid, byte[] content, String pwd) {
        log.info("createImage(uid -> %s, content _, pwd -> %s)\n".formatted(uid, pwd));
        if (content == null || content.length == 0)
            return error(BAD_REQUEST);
        var uRes = users.getUser(uid, pwd);
        if (!uRes.isOK())
            return error(uRes.error());
        var iid = UUID.randomUUID().toString();
        var path = Paths.get(IMAGES_DIR, uid, iid);
        createPathDirectories(uid);
        storeImage(path, content);
        return ok(UriBuilder.fromUri(uid).path(iid).build().toASCIIString());
    }

    private static void createPathDirectories(String uid) {
        try {
            Files.createDirectories(Paths.get(IMAGES_DIR, uid));
        } catch (IOException e) {
            log.severe("Unable to create directories for user %s".formatted(uid));
            throw new RuntimeException(e);
        }
    }

    public void storeImage(Path path, byte[] content) {
        try {
            lock.writeLock().lock();
            tryToStoreImage(path, content);
        } finally {
            lock.writeLock().unlock();
        }
    }

    private void tryToStoreImage(Path path, byte[] content) {
        try {
            Files.deleteIfExists(path);
            Files.write(path, content);
        } catch (IOException e) {
            log.severe("Unable to store image %s".formatted(path));
            throw new RuntimeException(e);
        }
    }

    @Override
    public Result<byte[]> getImage(String uid, String iid) {
        log.info("getImage(uid -> %s, iid -> %s)".formatted(uid, iid));
        if (uid == null || iid == null)
            return error(BAD_REQUEST);
        var path = Paths.get(IMAGES_DIR, uid, iid);
        return retrieveImage(path);
    }

    private Result<byte[]> retrieveImage(Path path) {
        try {
            lock.readLock().lock();
            return tryToRetrieveImage(path);
        } finally {
            lock.readLock().unlock();
        }
    }

    private Result<byte[]> tryToRetrieveImage(Path path) {
        if (!Files.exists(path))
            return error(NOT_FOUND);
        try {
            return ok(Files.readAllBytes(path));
        } catch (IOException e) {
            log.severe("Unable to read image file with path %s".formatted(path.toString()));
            throw new RuntimeException(e);
        }
    }

    @Override
    public Result<Void> deleteImage(String uid, String iid, String pwd) {
        log.info("deleteImage(uid -> %s, iid -> %s, pwd -> %s)\n".formatted(uid, iid, pwd));
        if (iid == null)
            return error(BAD_REQUEST);
        var uRes = users.getUser(uid, pwd);
        if (!uRes.isOK())
            return error(uRes.error());
        Path path = Paths.get(IMAGES_DIR, uid, iid);
        return deleteValidImg(path);
    }

    @Override
    public Result<Void> deleteImageUponUserOrPostRemoval(@NotNull String uid, @NotNull String iid) {
        log.info("Deleting image from removed entity: uid %s, iid %s\n".formatted(uid, iid));
        Path path = Paths.get(IMAGES_DIR, uid, iid);
        return deleteValidImg(path);
    }

    private Result<Void> deleteValidImg(Path path) {
        try {
            lock.writeLock().lock();
            return tryToDeleteValidImg(path);
        } finally {
            lock.writeLock().unlock();
        }
    }

    private Result<Void> tryToDeleteValidImg(Path path) {
        try {
            var deleted = Files.deleteIfExists(path);
            if (!deleted)
                return error(NOT_FOUND);
            return ok();
        } catch (IOException e) {
            log.severe("Unable to delete image with path %s".formatted(path.toString()));
            throw new RuntimeException(e);
        }
    }

    public Result<Void> teardown() {
        var dir = new File(IMAGES_DIR);
        File[] images = dir.listFiles();
        for (File img : images) {
            img.delete();
        }
        return ok();
    }
    
    public Result<Void> reset() {
        return ok();
    }

}
