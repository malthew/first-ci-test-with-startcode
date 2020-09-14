package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Movie;
import utils.EMF_Creator;
import facades.MovieFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("movies")
public class MovieResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    
    private static final MovieFacade FACADE =  MovieFacade.getMovieFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieCount() {
        long count = FACADE.getMovieCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }
    
    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllMovies() {
        return GSON.toJson(FACADE.getAll());
    }
    
    @GET
    @Path("/{title}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieByTitle(@PathParam("title") String title) {
        return GSON.toJson(FACADE.getMovieByTitle(title));
    }
    
//    @POST
//    @Path("/addmovie/{movie}")
//    public void addMovie(@QueryParam("movie") Movie movie) {
//        FACADE.addMovie(movie);
//    }
}
