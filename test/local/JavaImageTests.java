package local;

import impl.JavaContent;
import impl.JavaImage;
import impl.JavaUsers;
import service.ImageTests;

public class JavaImageTests extends ImageTests {

    public JavaImageTests() {
        this.images = new JavaImage();
        this.users = new JavaUsers();
        var contents = new JavaContent();
        ((JavaImage) images).setUsers(users);
        ((JavaUsers) users).setImages(images);
        ((JavaUsers) users).setContents(contents);
        contents.setUsers(users);
        contents.setImages(images);
    }

}
