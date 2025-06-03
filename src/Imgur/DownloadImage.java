package Imgur;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import com.github.scribejava.apis.ImgurApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.google.common.io.Files;
import com.google.gson.Gson;

import Imgur.data.BasicResponse;

public class DownloadImage extends ImgurClient {
	
	private static final String GET_IMAGE_URL = "https://api.imgur.com/3/image/{{imageHash}}";
		
	private static final int HTTP_SUCCESS = 200;
	
	private final Gson json;
	private final OAuth20Service service;
	private final OAuth2AccessToken accessToken;
	
	public DownloadImage() {
		json = new Gson();
		accessToken = new OAuth2AccessToken(accessTokenStr);
		service = new ServiceBuilder(apiKey).apiSecret(apiSecret).build(ImgurApi.instance());
	}
	
	public byte[] downloadImageBytes(String imageURL) {
		OAuthRequest request = new OAuthRequest(Verb.GET, imageURL);
		//This is a public operation hence you don't need to sign the request
		//service.signRequest(accessToken, request); 
		
		try {
			Response r = service.execute(request);
		
			if(r.getCode() == HTTP_SUCCESS) {
				byte[] imageContent = r.getStream().readAllBytes();
				System.err.println("Successfully downloaded " + imageContent.length + " bytes from the image.");
				return imageContent;
			} else {
				System.err.println("Operation to download image bytes Failed\nStatus: " + r.getCode() + "\nBody: " + r.getBody());
				return null;
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Failed to download image bytes");
			return null;
		}
	}
	
	
	public byte[] execute(String imageId) {
		String requestURL = GET_IMAGE_URL.replaceAll("\\{\\{imageHash\\}\\}", imageId);
		
		OAuthRequest request = new OAuthRequest(Verb.GET, requestURL);
		
		service.signRequest(accessToken, request);
		
		try {
			Response r = service.execute(request);
			
			
			if(r.getCode() != HTTP_SUCCESS) {
				//Operation failed
				System.err.println("Operation Failed\nStatus: " + r.getCode() + "\nBody: " + r.getBody());
				return null;
			} else {
				System.err.println("Contents of Body: " + r.getBody());
				BasicResponse body = json.fromJson(r.getBody(), BasicResponse.class);
				for(Object key: body.getData().keySet()) {
					System.err.println(key + " -> " + body.getData().get(key));
				}
				
				return this.downloadImageBytes(body.getData().get("link").toString());
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
		
		return null;
	}
	
	public static void main(String[] args) throws Exception {
	
		if( args.length != 2 ) {
			System.err.println("usage: java " + DownloadImage.class.getCanonicalName() +  " <image-id> <filename>");
			System.exit(0);
		}	
		
		String imageId = args[0];
		DownloadImage ca = new DownloadImage();
		/*
		if(ca.execute(imageId))
			System.out.println("Downloaded " + imageId + " successfuly.");
		else
			System.err.println("Failed to execute operation");
		*/	
	}
	
}