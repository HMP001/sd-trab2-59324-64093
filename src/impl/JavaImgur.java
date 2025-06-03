package impl;

import api.java.Image;
import api.java.Result;
import api.java.Users;
import jakarta.validation.constraints.NotNull;

import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import static api.java.Result.ErrorCode.*;
import static api.java.Result.*;

import Imgur.AddImageToAlbum;
import Imgur.CreateAlbum;
import Imgur.DeleteImage;
import Imgur.DownloadImage;
import Imgur.GetImagesFromAlbum;
import Imgur.ImageUpload;
import Imgur.SearchAlbum;

public class JavaImgur implements Image {

	private static final Logger log = Logger.getLogger(JavaImgur.class.getName());

	private Users users;

	public JavaImgur() {
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

		ImageUpload upload = new ImageUpload();
		String imageId = upload.execute(uid + "=" + UUID.randomUUID().toString(), content);

		String proxyName = getProxyName();
		SearchAlbum album = new SearchAlbum();
		String albumId = album.execute(proxyName);

		AddImageToAlbum addToAlbum = new AddImageToAlbum();
		addToAlbum.execute(albumId, imageId);

		return ok(imageId);
	}

	@Override
	public Result<byte[]> getImage(String uid, String iid) {
		log.info("getImage(uid -> %s, iid -> %s)".formatted(uid, iid));

		if (iid == null)
			return error(BAD_REQUEST);

		DownloadImage downloadImage = new DownloadImage();
		byte[] content = downloadImage.execute(iid);
		if (content == null)
			return error(NOT_FOUND);
		return ok(content);

	}

	@Override
	public Result<Void> deleteImage(String uid, String iid, String pwd) {
		log.info("deleteImage(uid -> %s, iid -> %s, pwd -> %s)\n".formatted(uid, iid, pwd));
		if (iid == null)
			return error(BAD_REQUEST);

		DeleteImage delete = new DeleteImage();
		Boolean exists = delete.execute(iid);
		
		if(exists)
			return ok(null);
		else return error(NOT_FOUND);
		
	}

	@Override
	public Result<Void> deleteImageUponUserOrPostRemoval(@NotNull String uid, @NotNull String iid) {
		return ok();
	}

	public Result<Void> teardown() {
		String proxyName = getProxyName();
		SearchAlbum album = new SearchAlbum();
		String albumId = album.execute(proxyName);

		GetImagesFromAlbum images = new GetImagesFromAlbum();
		List<String> allimages = images.execute(albumId);

		for (String imageId : allimages) {
			if (imageId != null && !imageId.isEmpty()) {
				DeleteImage delete = new DeleteImage();
				delete.execute(imageId);

			}
		}
		return ok(null);
	}

	private String getProxyName() {
		String ks = System.getProperty("javax.net.ssl.keyStore");
		if (ks != null) {
			String fileName = Paths.get(ks).getFileName().toString();
			if (fileName.startsWith("image-") && fileName.endsWith("-server.ks")) {
				String proxyName = fileName.replace("-server.ks", "");
				return proxyName;
			}
		}
		return null;
	}
}
