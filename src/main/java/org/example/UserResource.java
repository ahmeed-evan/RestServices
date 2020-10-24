package org.example;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("users")
public class UserResource {

    private UserRepository repository;

    public UserResource() throws Exception {
        repository = new UserRepository();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUser() throws Exception {
        return repository.getAllUser();
    }

    @GET
    @Path("user/{uId}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("uId") int uId) throws Exception {
        return repository.getUser(uId);
    }

    @POST
    @Path("user")
    @Produces(MediaType.APPLICATION_JSON)
    public User createUser(User user) throws Exception {
        return repository.createUser(user);
    }

    @PUT
    @Path("user")
    @Produces(MediaType.APPLICATION_JSON)
    public User updateUser(User user) throws Exception {
        if (repository.getUser(user.getuId()).getuId() == 0) {
            return repository.createUser(user);
        } else {
            return repository.updateUser(user);
        }
    }

}
