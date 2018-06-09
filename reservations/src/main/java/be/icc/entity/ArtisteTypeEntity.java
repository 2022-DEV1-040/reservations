package be.icc.entity;

import java.util.Collection;
import java.util.Objects;
import javax.persistence.*;
@Entity
@Table(name = "artiste_type", schema = "reservations", catalog = "")
public class ArtisteTypeEntity {
    private Integer id;
    private ArtistsEntity artistsByArtistId;
    private TypesEntity typesByTypeId;
    private Collection<ArtisteTypeShowEntity> artisteTypeShowsById;

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
    public ArtistsEntity getArtistsByArtistId() {
        return artistsByArtistId;
    }

    public void setArtistsByArtistId(ArtistsEntity artistsByArtistId) {
        this.artistsByArtistId = artistsByArtistId;
    }

    @ManyToOne(cascade = {})
    @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false, table = "")
    public TypesEntity getTypesByTypeId() {
        return typesByTypeId;
    }

    public void setTypesByTypeId(TypesEntity typesByTypeId) {
        this.typesByTypeId = typesByTypeId;
    }

    @OneToMany(cascade = {}, mappedBy = "artisteTypeByArtisteTypeId")
    public Collection<ArtisteTypeShowEntity> getArtisteTypeShowsById() {
        return artisteTypeShowsById;
    }

    public void setArtisteTypeShowsById(Collection<ArtisteTypeShowEntity> artisteTypeShowsById) {
        this.artisteTypeShowsById = artisteTypeShowsById;
    }
}
