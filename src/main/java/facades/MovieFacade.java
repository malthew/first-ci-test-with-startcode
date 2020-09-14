package facades;

import dto.MovieDTO;
import entities.Movie;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MovieFacade {

    private static MovieFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private MovieFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MovieFacade getMovieFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    //TODO Remove/Change this before use
    public long getMovieCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long movieCount = (long)em.createQuery("SELECT COUNT(m) FROM Movie m").getSingleResult();
            return movieCount;
        }finally{  
            em.close();
        }
    }
    
    public List<MovieDTO> getAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Movie> query
                    = em.createQuery("SELECT m FROM Movie m ORDER BY m.title ASC", Movie.class);
            List<Movie> movies = query.getResultList();
            List<MovieDTO> moviedto = new ArrayList<>();
            for (Movie m : movies) {
                moviedto.add(new MovieDTO(m));
            }
            return moviedto;
        }finally{
            em.close();
        }
    }
    
    public void addMovie (Movie movie) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(movie);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
    }
    
    public List<MovieDTO> getMovieByTitle (String title) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Movie> query
                    = em.createQuery("Select m from Movie m where m.title like :title", Movie.class);
            query.setParameter("title", title);
            List<Movie> movies = query.getResultList();
            List<MovieDTO> moviedto = new ArrayList<>();
            for (Movie m : movies) {
                moviedto.add(new MovieDTO(m));
            }
            return moviedto;
        } finally {
            em.close();
        }
    }

}
