package be.icc.entity;

import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "representation_user", schema = "reservations", catalog = "")
public class RepresentationUserEntity {
    private int id;
    private int places;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "places")
    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepresentationUserEntity that = (RepresentationUserEntity) o;
        return id == that.id &&
                places == that.places;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, places);
    }
}
