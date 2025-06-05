package Imgur;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.github.scribejava.apis.ImgurApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.google.gson.*;

import Imgur.data.AddImagesToAlbumArguments;
import Imgur.data.BooleanBasicResponse;

public class GetImagesFromAlbum extends ImgurClient {
	
	private static final String GET_IMAGES_FROM_ALBUM_URL = "https://api.imgur.com/3/album/{{albumHash}}/images";
		
	private static final int HTTP_SUCCESS = 200;
	
	private final Gson json;
	private final OAuth20Service service;
	private final OAuth2AccessToken accessToken;
	
	public GetImagesFromAlbum() {
		json = new Gson();
		accessToken = new OAuth2AccessToken(accessTokenStr);
		service = new ServiceBuilder(apiKey).apiSecret(apiSecret).build(ImgurApi.instance());
	}
	
	public List<String> execute(String albumId) {
	    String requestURL = GET_IMAGES_FROM_ALBUM_URL.replaceAll("\\{\\{albumHash\\}\\}", albumId);

	    OAuthRequest request = new OAuthRequest(Verb.GET, requestURL);
	    service.signRequest(accessToken, request);

	    try {
	        Response r = service.execute(request);
	        if (r.getCode() != HTTP_SUCCESS) {
	            System.err.println("Failed to fetch images\nStatus: " + r.getCode() + "\nBody: " + r.getBody());
	            return null;
	        } else {
	            JsonObject responseJson = JsonParser.parseString(r.getBody()).getAsJsonObject();

	            if (!responseJson.has("data") || responseJson.get("data").isJsonNull()) {
	                System.out.println("No images found in album.");
	                return new ArrayList<>();
	            }

	            JsonArray images = responseJson.getAsJsonArray("data");
	            List<String> imageIds = new ArrayList<>();

	            for (JsonElement element : images) {
	                JsonObject imgObj = element.getAsJsonObject();
	                imageIds.add(imgObj.get("id").getAsString());
	            }

	            return imageIds;
	        }

	    } catch (InterruptedException | ExecutionException | IOException e) {
	        e.printStackTrace();
	        System.exit(1);
	    }

	    return null;
	}

	
	public static void main(String[] args) throws Exception {
	
		if( args.length != 2 ) {
			System.err.println("usage: java " + AddImageToAlbum.class.getCanonicalName() +  " <album-id> <image-id>");
			System.exit(0);
		}	
		
		String albumId = args[0];
		String imageId = args[1];
		AddImageToAlbum ca = new AddImageToAlbum();
		
		if(ca.execute(albumId, imageId))
			System.out.println("Added " + imageId + " to album " + albumId + " successfuly.");
		else
			System.err.println("Failed to execute operation");
	}
	
}