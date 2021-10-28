package jpabopok.jpashop.domain;

import javax.persistence.Entity;

/**
 * @author gj.an@okestro.com
 * @since 2021/07/09
 */
@Entity
public class Album extends Item{
    private String artist;
    private String etc;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }
}
