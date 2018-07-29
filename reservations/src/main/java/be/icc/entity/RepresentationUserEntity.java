package be.icc.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;
@Entity
@Table(name = "representation_user", schema = "reservations", catalog = "")
public class RepresentationUserEntity {
    private Integer id;
    private Integer places;
    private RepresentationsEntity representation;
    private UsersEntity user;

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
    @Column(name = "places", nullable = false)
    public Integer getPlaces() {
        return places;
    }

    public void setPlaces(Integer places) {
        this.places = places;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepresentationUserEntity that = (RepresentationUserEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(places, that.places);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, places);
    }

    @ManyToOne(cascade = {})
    @JoinColumn(name = "representation_id", referencedColumnName = "id", nullable = false, table = "")
    public RepresentationsEntity getRepresentation() {
        return representation;
    }

    public void setRepresentation(RepresentationsEntity representation) {
        this.representation = representation;
    }

    @ManyToOne(cascade = {})
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, table = "")
    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }
}
