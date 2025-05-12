package local;

import impl.JavaContent;
import impl.JavaImage;
import impl.JavaUsers;
import service.IntegrationTests;

public class LocalIntegrationTests extends IntegrationTests {

    public LocalIntegrationTests() {
        this.users = new JavaUsers();
        this.contents = new JavaContent();
        this.images = new JavaImage();
        ((JavaUsers) users).setContents(contents);
        ((JavaUsers) users).setImages(images);
        ((JavaContent) contents).setUsers(users);
        ((JavaContent) contents).setImages(images);
        ((JavaImage) images).setUsers(users);
    }

}
