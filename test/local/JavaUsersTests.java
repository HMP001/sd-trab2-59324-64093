package local;

import impl.JavaContent;
import impl.JavaImage;
import impl.JavaUsers;
import service.UsersTests;

public class JavaUsersTests extends UsersTests {

    public JavaUsersTests() {
        users = new JavaUsers();
        var images = new JavaImage();
        var contents = new JavaContent();
        ((JavaUsers) users).setImages(images);
        ((JavaUsers) users).setContents(contents);
        images.setUsers(users);
        contents.setUsers(users);
        contents.setImages(images);
    }
}
