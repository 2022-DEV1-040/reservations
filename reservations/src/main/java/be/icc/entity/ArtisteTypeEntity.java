package be.icc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Objects;
@Entity
@Table(name = "artiste_type", schema = "reservations", catalog = "")
public class ArtisteTypeEntity {
    private Integer id;
    private ArtistsEntity artist;
    private TypesEntity type;
    private Collection<ArtisteTypeShowEntity> shows;

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtisteTypeEntity that = (ArtisteTypeEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @ManyToOne(cascade = {})
    @JoinColumn(name = "artist_id", referencedColumnName = "id", nullable = false, table = "")
    public ArtistsEntity getArtist() {
        return artist;
    }

    public void setArtist(ArtistsEntity artist) {
        this.artist = artist;
    }

    @ManyToOne(cascade = {})
    @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false, table = "")
    public TypesEntity getType() {
        return type;
    }

    public void setType(TypesEntity type) {
        this.type = type;
    }

    @OneToMany(cascade = {}, mappedBy = "artisteTypeByArtisteTypeId")
    public Collection<ArtisteTypeShowEntity> getShows() {
        return shows;
    }

    public void setShows(Collection<ArtisteTypeShowEntity> shows) {
        this.shows = shows;
    }
}
