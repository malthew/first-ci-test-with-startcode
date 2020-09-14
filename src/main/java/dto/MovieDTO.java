package dto;
import entities.Movie;

/**
 *
 * @author Malthe
 */
public class MovieDTO {
    private int year;
    private String title;
    private String[] actors;
    
    public MovieDTO(Movie movie) {
        this.year = movie.getYear();
        this.title = movie.getTitle();
        this.actors = movie.getActors();
    }
    
    @Override
    public String toString() {
        return "MovieDTO{" + "year=" + year + ", title=" + title + ", actors=" + actors + '}';
    }
    
    public int getYear() {
        return year;
    }
    
    public void setYear(int year) {
        this.year = year;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String[] getActors() {
        return actors;
    }
    
    public void setActors(String[] actors) {
        this.actors = actors;
    }
    
}

