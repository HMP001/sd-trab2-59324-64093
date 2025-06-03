package api.rest;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path(RestImgur.PATH)
public interface RestImgur {

	String PATH = "/image";
	String IMAGE_ID = "id";
	String USER_ID = "user";
	String PASSWORD = "pwd";

	String DELETE = "delete";

	String DELETEALL = "deleteall";

	/**
	 * Create an image
	 * 
	 * @param imageContents the bytes of the image in PNG format (in the body of the request)
	 * @return 	OK in the case of success returning the URI to access the image. 
	 * 		   	NOT_FOUND if user does not exists
	 * 			FORBIDDEN if user password is incorrect
	 * 		   	BAD_REQUEST if imageContents has a size of zero or password is null
	 */
	@POST
	@Path("{" + USER_ID + "}")
	@Consumes(MediaType.APPLICATION_OCTET_STREAM)
	String createImage(@PathParam(USER_ID) String userId, byte[] imageContents, @QueryParam(PASSWORD) String password);


	//TODO: Check if having both the userId and the imageId in the path was on purpose...
	/**
	 * Gets the contents of an image associated with the imageId
	 * 
	 * @param imageId the identifier of the image
	 * @return OK the case of success returning the bytes of the image exists
	 *  	   NOT_FOUND should be returned if the image does not exists
	 */
	@GET
	@Path("{" + IMAGE_ID + "}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	byte[] getImage(@PathParam(USER_ID) String userId, @PathParam(IMAGE_ID) String imageId);
	
	/**
	 * Deletes an image identified by imageId
	 * 
	 * @param imageId the identifier of the image
	 * @return 	NO_CONTENT in the case of success. 
	 * 			NOT_FOUND if the image or user does not exists
	 * 			FORBIDDEN if user password is incorrect
	 * 		   	BAD_REQUEST password is null
	 */
	@DELETE
	@Path("{" + IMAGE_ID + "}")
	void deleteImage(@PathParam(USER_ID) String userId, @PathParam(IMAGE_ID) String imageId, @QueryParam(PASSWORD) String password);

	/**
	 * Deletes an image when it belongs to a deleted post or is the avatar of a deleted user
	 * @param uid
	 * @param iid
	 * @return 	NO_CONTENT in the case of success.
	 * 			NOT_FOUND if the image does not exist
	 * 			FORBIDDEN if the user is not the owner
	 */
	@DELETE
	@Path(DELETE + "/{" + USER_ID + "}/{" + IMAGE_ID + "}")
	void deleteImageUponUserOrPostRemoval(@PathParam(USER_ID) String uid, @PathParam(IMAGE_ID) String iid);

	@DELETE
	@Path(DELETEALL)
	void teardown();

}