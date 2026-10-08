package ressources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import entities.Option;
import metiers.OptionBusiness;

@Path("/options")
public class OptionRessource {

    OptionBusiness optBusiness = new OptionBusiness();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.status(Response.Status.CREATED).entity(this.optBusiness.getListeOptions()).build();
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Option getOptionByCode(@PathParam("id")int code){
        return  this.optBusiness.getOptionByCode(code);
    }
    /*@POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Option option){
        if(this.optBusiness.addOption(option)){
            return Response.status(Response.Status.CREATED).entity("Added with succes").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }*/
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_XML)
    public Response create(Option option){
        if(this.optBusiness.addOption(option)){
            return Response.status(Response.Status.CREATED).entity("Added with succes").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
    @PUT
    @Path("/{idop}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("idop")int idop, Option option){
        if(this.optBusiness.updateOption(idop, option)) {
            return Response.status(Response.Status.CREATED).entity("updated with succes").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}