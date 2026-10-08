package ressources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import entities.Etudiant;

import metiers.EtudiantBusiness;
import metiers.OptionBusiness;

@Path("/etudiants")
public class StudentRessource {

    private EtudiantBusiness etuBusiness = new EtudiantBusiness();
    private OptionBusiness optBusiness = new OptionBusiness();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Etudiant etudiant) {
        if (etudiant.getOption() != null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (etuBusiness.addEtudiant(etudiant)) {
            return Response.status(Response.Status.CREATED).entity(etudiant).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Etudiant> etudiants = etuBusiness.getAllEtudiants();
        return Response.status(Response.Status.CREATED).entity(etudiants).build();
    }

    @GET
    @Path("/{idet}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("idet") String identifiant) {
        Etudiant etudiant = etuBusiness.getEtudiantByIdentifiant(identifiant);
        if (etudiant == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.CREATED).entity(etudiant).build();
    }

    @DELETE
    @Path("/{idet}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("idet") String identifiant) {
        if (etuBusiness.deleteEtudiant(identifiant)) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("/{idet}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("idet") String identifiant, Etudiant etudiant) {
        if (etuBusiness.updateEtudiant(identifiant, etudiant)) {
            return Response.status(Response.Status.CREATED).entity(etudiant).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}