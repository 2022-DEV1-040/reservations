package be.icc.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Objects;
@Entity
@Table(name = "localities", schema = "reservations", catalog = "")
public class LocalitiesEntity {
    private Integer id;
    private String postalCode;
    private String locality;
    private Collection<LocationsEntity> locations;

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
    @Column(name = "postal_code", nullable = false, length = 6)
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Basic
    @Column(name = "locality", nullable = false, length = 60)
    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocalitiesEntity that = (LocalitiesEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(postalCode, that.postalCode) &&
                Objects.equals(locality, that.locality);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, postalCode, locality);
    }

    @OneToMany(cascade = {}, mappedBy = "locality")
    public Collection<LocationsEntity> getLocations() {
        return locations;
    }

    public void setLocations(Collection<LocationsEntity> locations) {
        this.locations = locations;
    }
}
