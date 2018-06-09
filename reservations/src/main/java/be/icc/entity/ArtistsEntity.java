package be.icc.entity;

import java.util.Collection;
import java.util.Objects;
import javax.persistence.*;
@Entity
@Table(name = "artists", schema = "reservations", catalog = "")
public class ArtistsEntity {
    private Integer id;
    private String firstname;
    private String lastname;
    private Collection<ArtisteTypeEntity> artisteTypesById;

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "firstname", nullable = false, length = 60)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "lastname", nullable = false, length = 60)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistsEntity that = (ArtistsEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firstname, lastname);
    }

    @OneToMany(cascade = {}, mappedBy = "artistsByArtistId")
    public Collection<ArtisteTypeEntity> getArtisteTypesById() {
        return artisteTypesById;
    }

    public void setArtisteTypesById(Collection<ArtisteTypeEntity> artisteTypesById) {
        this.artisteTypesById = artisteTypesById;
    }
}
