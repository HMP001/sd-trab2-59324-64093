package Imgur;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import com.github.scribejava.apis.ImgurApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.google.gson.Gson;

import Imgur.data.BooleanBasicResponse;

public class DeleteImage extends ImgurClient {
	
	private static final String DELETE_IMAGE_URL = "https://api.imgur.com/3/image/{{imageDeleteHash}}";
		
	private static final int HTTP_SUCCESS = 200;
	
	private final Gson json;
	private final OAuth20Service service;
	private final OAuth2AccessToken accessToken;
	
	public DeleteImage() {
		json = new Gson();
		accessToken = new OAuth2AccessToken(accessTokenStr);
		service = new ServiceBuilder(apiKey).apiSecret(apiSecret).build(ImgurApi.instance());
	}
	
	public boolean execute(String imageId) {
		String requestURL = DELETE_IMAGE_URL.replaceAll("\\{\\{imageDeleteHash\\}\\}", imageId);
		
		OAuthRequest request = new OAuthRequest(Verb.DELETE, requestURL);
		
		service.signRequest(accessToken, request);
		
		try {
			Response r = service.execute(request);
			
			
			if(r.getCode() != HTTP_SUCCESS) {
				//Operation failed
				System.err.println("Delete Failed\nStatus: " + r.getCode() + "\nBody: " + r.getBody());
                return false;
			} else {
				 System.out.println("Delete Success\nResponse: " + r.getBody());
	             BooleanBasicResponse body = json.fromJson(r.getBody(), BooleanBasicResponse.class);
	             return body != null && body.isSuccess();
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (ExecutionException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		return false;
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