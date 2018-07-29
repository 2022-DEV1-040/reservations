package be.icc.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;
@Entity
@Table(name = "representations", schema = "reservations", catalog = "")
public class RepresentationsEntity {
    private Long id;
    private Timestamp when;
    private Collection<RepresentationUserEntity> representationUsers;
    private ShowsEntity show;
    private LocationsEntity location;

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
    public Collection<RepresentationUserEntity> getRepresentationUsers() {
        return representationUsers;
    }

    public void setRepresentationUsers(Collection<RepresentationUserEntity> representationUsers) {
        this.representationUsers = representationUsers;
    }

    @ManyToOne(cascade = {})
    @JoinColumn(name = "show_id", referencedColumnName = "id", nullable = false, table = "")
    public ShowsEntity getShow() {
        return show;
    }

    public void setShow(ShowsEntity show) {
        this.show = show;
    }

    @ManyToOne(cascade = {})
    @JoinColumn(name = "location_id", referencedColumnName = "id", nullable = true, table = "")
    public LocationsEntity getLocation() {
        return location;
    }

    public void setLocation(LocationsEntity location) {
        this.location = location;
    }
}
