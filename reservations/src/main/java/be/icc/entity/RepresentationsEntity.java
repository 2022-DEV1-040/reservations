package be.icc.entity;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.*;
@Entity
@Table(name = "representations", schema = "reservations", catalog = "")
public class RepresentationsEntity {
    private Long id;
    private Timestamp when;
    private Collection<RepresentationUserEntity> representationUsersById;
    private ShowsEntity showsByShowId;
    private LocationsEntity locationsByLocationId;

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "when", nullable = false)
    public Timestamp getWhen() {
        return when;
    }

    public void setWhen(Timestamp when) {
        this.when = when;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepresentationsEntity that = (RepresentationsEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(when, that.when);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, when);
    }

    @OneToMany(cascade = {}, mappedBy = "representationsByRepresentationId")
    public Collection<RepresentationUserEntity> getRepresentationUsersById() {
        return representationUsersById;
    }

    public void setRepresentationUsersById(Collection<RepresentationUserEntity> representationUsersById) {
        this.representationUsersById = representationUsersById;
    }

    @ManyToOne(cascade = {})
    @JoinColumn(name = "show_id", referencedColumnName = "id", nullable = false, table = "")
    public ShowsEntity getShowsByShowId() {
        return showsByShowId;
    }

    public void setShowsByShowId(ShowsEntity showsByShowId) {
        this.showsByShowId = showsByShowId;
    }

    @ManyToOne(cascade = {})
    @JoinColumn(name = "location_id", referencedColumnName = "id", nullable = true, table = "")
    public LocationsEntity getLocationsByLocationId() {
        return locationsByLocationId;
    }

    public void setLocationsByLocationId(LocationsEntity locationsByLocationId) {
        this.locationsByLocationId = locationsByLocationId;
    }
}
