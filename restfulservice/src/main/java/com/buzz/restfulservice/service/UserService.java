package com.buzz.restfulservice.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;

import com.buzz.restfulservice.model.User;
import com.buzz.restfulservice.model.Users;
 
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "user-management")
@Path("/user-management")
public class UserService {
	private Logger log = Logger.getLogger(UserService.class); 
    @XmlElement(name = "users")
    private String uri1 = "/user-management/users";
 
    @XmlElement(name = "report")
    private String uri2 = "/user-managemet/generate-report";
 
    public String getUri1() {
        return uri1;
    }
 
    public void setUri1(String uri1) {
        this.uri1 = uri1;
    }
 
    public String getUri2() {
        return uri2;
    }
 
    public void setUri2(String uri2) {
        this.uri2 = uri2;
    }
 
    @GET
    @Path("/")
    @Produces("application/xml")
    public UserService getServiceInfo() {
        return new UserService();
    }
 
    @DenyAll
    @GET
    @Path("/users")
    @Produces("application/xml")
    public Users getAllUsers() {
        User user1 = new User();
        user1.setId(1);
        user1.setFirstName("demo");
        user1.setLastName("user");
        user1.setUri("/user-management/users/1");
 
        User user2 = new User();
        user2.setId(2);
        user2.setFirstName("demo");
        user2.setLastName("user");
        user2.setUri("/user-management/users/2");
 
        Users users = new Users();
        users.setUsers(new ArrayList<User>());
        users.getUsers().add(user1);
        users.getUsers().add(user2);
 
        return users;
    }
 
    @GET
    @Path("/users/{id}")
    @Produces("application/xml")
    public User getUserById(@PathParam("id") int id) {
        User user = new User();
        user.setId(id);
        user.setFirstName("demo");
        user.setLastName("user");
        user.setUri("/user-management/users/" + id);
        return user;
    }
 
    @POST
    @Path("/users")
    @Consumes("application/xml")
    public Response createUser(User user,
            @DefaultValue("false") @QueryParam("allow-admin") boolean allowAdmin)
            throws URISyntaxException {
    	log.info("**********ENTRA AL METODO POST");
        System.out.println(user.getFirstName());
        System.out.println(user.getLastName());
        return Response.status(201)
                .contentLocation(new URI("/user-management/users/123")).build();
    }
 
    @RolesAllowed("ADMIN")
    @PUT
    // @Path("/users/{id: [0-9]*}")
    @Path("/users/{id}")
    @Consumes("application/json")
    @Produces("application/xml")
    public User updateUser(@PathParam("id") int id, User user)
            throws URISyntaxException {
    	
        user.setId(id);
        user.setFirstName(user.getFirstName() + "updated");
        return user;
    }
    
	@RolesAllowed("ADMIN")
    @PUT
    @Path("/users1/{id}")
	@Consumes("application/xml")
	@Produces("application/xml")
    public Response updateUserById(@PathParam("id") int id)
    {

        return Response.status(200).build();
    }
 
    @DELETE
    @Path("/users/{id}")
    public Response deleteUser(@PathParam("id") int id)
            throws URISyntaxException {
        return Response.status(200).build();
    }
}
