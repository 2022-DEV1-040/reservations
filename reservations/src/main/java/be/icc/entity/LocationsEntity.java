package be.icc.entity;

import java.util.Collection;
import java.util.Objects;
import javax.persistence.*;
@Entity
@Table(name = "locations", schema = "reservations", catalog = "")
public class LocationsEntity {
    private Integer id;
    private String slug;
    private String designation;
    private String address;
    private String website;
    private String phone;
    private LocalitiesEntity localitiesByLocalityId;
    private Collection<RepresentationsEntity> representationsById;
    private Collection<ShowsEntity> showsById;

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
    @Column(name = "slug", nullable = false, length = 60)
    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Basic
    @Column(name = "designation", nullable = false, length = 60)
    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Basic
    @Column(name = "address", nullable = false, length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "website", nullable = false, length = 255)
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Basic
    @Column(name = "phone", nullable = false, length = 30)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationsEntity that = (LocationsEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(slug, that.slug) &&
                Objects.equals(designation, that.designation) &&
                Objects.equals(address, that.address) &&
                Objects.equals(website, that.website) &&
                Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, slug, designation, address, website, phone);
    }

    @ManyToOne(cascade = {})
    @JoinColumn(name = "locality_id", referencedColumnName = "id", nullable = false, table = "")
    public LocalitiesEntity getLocalitiesByLocalityId() {
        return localitiesByLocalityId;
    }

    public void setLocalitiesByLocalityId(LocalitiesEntity localitiesByLocalityId) {
        this.localitiesByLocalityId = localitiesByLocalityId;
    }

    @OneToMany(cascade = {}, mappedBy = "locationsByLocationId")
    public Collection<RepresentationsEntity> getRepresentationsById() {
        return representationsById;
    }

    public void setRepresentationsById(Collection<RepresentationsEntity> representationsById) {
        this.representationsById = representationsById;
    }

    @OneToMany(cascade = {}, mappedBy = "locationsByLocationId")
    public Collection<ShowsEntity> getShowsById() {
        return showsById;
    }

    public void setShowsById(Collection<ShowsEntity> showsById) {
        this.showsById = showsById;
    }
}
