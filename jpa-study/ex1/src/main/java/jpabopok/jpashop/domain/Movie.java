package jpabopok.jpashop.domain;

/**
 * @author gj.an@okestro.com
 * @since 2021/07/09
 */
public class Movie extends Item{
    private String director;
    private String actor;

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }
}
