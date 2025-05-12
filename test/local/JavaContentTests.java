package local;

import impl.JavaContent;
import impl.JavaImage;
import impl.JavaUsers;
import service.ContentTests;

public class JavaContentTests extends ContentTests {

    public JavaContentTests() {
        this.users = new JavaUsers();
        this.content = new JavaContent();
        var images = new JavaImage();
        ((JavaUsers) users).setContents(content);
        ((JavaUsers) users).setImages(images);
        ((JavaContent) content).setUsers(users);
        ((JavaContent) content).setImages(images);
        images.setUsers(users);
    }

    @Override
    public String getUriPrefix(String id) {
        return id;
    }
}
