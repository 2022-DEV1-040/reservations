package be.icc.entity;

import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "localities", schema = "reservations", catalog = "")
public class LocalitiesEntity {
    private int id;
    private String postalCode;
    private String locality;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "postal_code")
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Basic
    @Column(name = "locality")
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
        return id == that.id &&
                Objects.equals(postalCode, that.postalCode) &&
                Objects.equals(locality, that.locality);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, postalCode, locality);
    }
}
