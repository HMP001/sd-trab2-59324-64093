package api;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Vote {

    @Id
    private String voterId;

    @Id
    private String postId;

    private boolean isUpvote;

    public Vote(String voterId, String postId, boolean isUpvote) {
        this.voterId = voterId;
        this.postId = postId;
        this.isUpvote = isUpvote;
    }

    public Vote() {}

    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }

    public String getVoterId() {
        return voterId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostId() {
        return postId;
    }

    public void setIsUpvote(boolean isUpvote) {
        this.isUpvote = isUpvote;
    }

    public boolean getIsUpvote() {
        return isUpvote;
    }

}
