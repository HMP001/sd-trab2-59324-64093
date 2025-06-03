package Imgur;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.List;
import java.util.Map;

import com.github.scribejava.apis.ImgurApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SearchAlbum extends ImgurClient {
	
	private static final String SEARCH_ALBUM_URL = "https://api.imgur.com/3/account/me/albums";
		
	private static final int HTTP_SUCCESS = 200;
	
	private final Gson json;
	private final OAuth20Service service;
	private final OAuth2AccessToken accessToken;
	
	public SearchAlbum() {
		json = new Gson();
		accessToken = new OAuth2AccessToken(accessTokenStr);
		service = new ServiceBuilder(apiKey).apiSecret(apiSecret).build(ImgurApi.instance());
	}
	
	public String execute(String albumName) {
		OAuthRequest request = new OAuthRequest(Verb.GET, SEARCH_ALBUM_URL);
		
		service.signRequest(accessToken, request);
		
		try {
			Response r = service.execute(request);

			if (r.getCode() != HTTP_SUCCESS) {
				System.err.println("Failed to fetch albums.\nStatus: " + r.getCode() + "\nBody: " + r.getBody());
				return null;
			}

			Map<String, Object> responseMap = json.fromJson(r.getBody(), new TypeToken<Map<String, Object>>() {}.getType());
			List<Map<String, Object>> albums = (List<Map<String, Object>>) responseMap.get("data");

			for (Map<String, Object> album : albums) {
				if (albumName.equals(album.get("title"))) {
					System.out.println("Found album: " + albumName + " with ID: " + album.get("id"));
					return (String) album.get("id");
				}
			}

			System.out.println("Album '" + albumName + "' not found.");
			return null;

		} catch (InterruptedException | ExecutionException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}