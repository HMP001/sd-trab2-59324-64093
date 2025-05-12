package network;


import api.Post;
import api.User;
import jakarta.ws.rs.core.UriBuilder;
import network.grpc.ContentProtoBuf;
import network.grpc.UsersProtoBuf;

import java.net.URI;

public class DataModelAdaptor {

	public static User GrpcUser_to_User(UsersProtoBuf.GrpcUser from )  {
		var userId = from.hasUserId() ? from.getUserId() : null;
		var fullname = from.hasFullName() ? from.getFullName() : null;
		var email = from.hasEmail() ? from.getEmail() : null;
		var password = from.hasPassword() ? from.getPassword() : null;
		var avatar = from.hasAvatar() ? from.getAvatar() : null;
		return new User(userId, fullname, email, password, avatar);
	}

	public static UsersProtoBuf.GrpcUser User_to_GrpcUser(User from )  {
		var b = UsersProtoBuf.GrpcUser.newBuilder();
		if (from.getUserId() != null)
			b.setUserId(from.getUserId());
		if (from.getPassword() != null)
			b.setPassword(from.getPassword());
		if (from.getEmail() != null)
			b.setEmail(from.getEmail());
		if (from.getFullName() != null)
			b.setFullName(from.getFullName());
		if(from.getAvatarUrl() != null)
			b.setAvatar(from.getAvatarUrl());
		return b.build();
	}

	public static Post GrpcPost_to_Post(ContentProtoBuf.GrpcPost from) {
		var postId = from.getPostId().isEmpty() ? null : from.getPostId();
		var authorId = from.getAuthorId().isEmpty() ? null : from.getAuthorId();
		var creationTime = from.getCreationTimestamp();
		var content = from.getContent().isEmpty() ? null : from.getContent();
		var mediaUrl = from.getMediaUrl().isEmpty() ? null : from.getMediaUrl();
		var parentUrl = from.getParentUrl().isEmpty() ? null : from.getParentUrl();
		var upVote = from.getUpVote();
		var downVote = from.getDownVote();
		return new Post(postId, authorId, creationTime, content, mediaUrl, parentUrl, upVote, downVote);
	}

	public static ContentProtoBuf.GrpcPost Post_to_GrpcPost(Post from) {
		var b = ContentProtoBuf.GrpcPost.newBuilder();
		if (from.getPostId() != null)
			b.setPostId(from.getPostId());
		if (from.getAuthorId() != null)
			b.setAuthorId(from.getAuthorId());
		b.setCreationTimestamp(from.getCreationTimestamp());
		if (from.getContent() != null)
			b.setContent(from.getContent());
		if (from.getMediaUrl() != null)
			b.setMediaUrl(from.getMediaUrl());
		if (from.getParentUrl() != null)
			b.setParentUrl(from.getParentUrl());
		b.setUpVote(from.getUpVote());
		b.setDownVote(from.getDownVote());
		return b.build();
	}

	public static String extractIdFromUrl(String postUrl) {
		var splitUrl = postUrl.split("/");
		return splitUrl[splitUrl.length - 1];
	}

	public static String incorporateUrlToId(URI base, String postId) {
		return UriBuilder.fromUri(base).path(postId).build().toASCIIString();
	}

}
