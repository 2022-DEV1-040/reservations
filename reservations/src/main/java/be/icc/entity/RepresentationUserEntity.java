package be.icc.entity;

import java.util.Objects;
import javax.persistence.*;
@Entity
@Table(name = "representation_user", schema = "reservations", catalog = "")
public class RepresentationUserEntity {
    private Integer id;
    private Integer places;
    private RepresentationsEntity representationsByRepresentationId;
    private UsersEntity usersByUserId;

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
    public RepresentationsEntity getRepresentationsByRepresentationId() {
        return representationsByRepresentationId;
    }

    public void setRepresentationsByRepresentationId(RepresentationsEntity representationsByRepresentationId) {
        this.representationsByRepresentationId = representationsByRepresentationId;
    }

    @ManyToOne(cascade = {})
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, table = "")
    public UsersEntity getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(UsersEntity usersByUserId) {
        this.usersByUserId = usersByUserId;
    }
}
