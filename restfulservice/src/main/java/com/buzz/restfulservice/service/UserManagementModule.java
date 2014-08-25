package com.buzz.restfulservice.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.buzz.restfulservice.model.User;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "user-management-1")
@Path("/user-management-1")
public class UserManagementModule
{
	@XmlElement(name="users")
	private String url1 = "/user-management-1/users";
	
	
	
	public String getUrl1() {
		return url1;
	}

	public void setUrl1(String url1) {
		this.url1 = url1;
	}

	@GET
    @Path("/")
    @Produces("application/vnd.com.demo.user-management+xml;charset=UTF-8;version=1")
	//@Produces("application/json")
    public UserManagementModule getServiceInfo() {
        return new UserManagementModule();
    }
	
    @GET
    @Path("/users/{id}")
    @Produces("application/json")
    public Response getUserById(@PathParam("id") Integer id)
    {
        User user = new User();
        user.setId(id);
        user.setFirstName("Lokesh");
        user.setLastName("Gupta");
        return Response.status(200).entity(user).build();
    }
}