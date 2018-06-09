package be.icc.entity;

import java.util.Collection;
import java.util.Objects;
import javax.persistence.*;
@Entity
@Table(name = "localities", schema = "reservations", catalog = "")
public class LocalitiesEntity {
    private Integer id;
    private String postalCode;
    private String locality;
    private Collection<LocationsEntity> locationsById;

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

    @OneToMany(cascade = {}, mappedBy = "localitiesByLocalityId")
    public Collection<LocationsEntity> getLocationsById() {
        return locationsById;
    }

    public void setLocationsById(Collection<LocationsEntity> locationsById) {
        this.locationsById = locationsById;
    }
}
