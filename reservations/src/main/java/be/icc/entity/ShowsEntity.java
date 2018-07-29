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
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;
@Entity
@Table(name = "shows", schema = "reservations", catalog = "")
public class ShowsEntity {
    private Integer id;
    private String slug;
    private String title;
    private String posterUrl;
    private Byte bookable;
    private BigDecimal price;
    private Collection<ArtisteTypeShowEntity> artistes;
    private Collection<RepresentationsEntity> representations;
    private LocationsEntity location;

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
    @Column(name = "title", nullable = false, length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "poster_url", nullable = false, length = 255)
    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    @Basic
    @Column(name = "bookable", nullable = false)
    public Byte getBookable() {
        return bookable;
    }

    public void setBookable(Byte bookable) {
        this.bookable = bookable;
    }

    @Basic
    @Column(name = "price", nullable = false, precision = 2)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShowsEntity that = (ShowsEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(slug, that.slug) &&
                Objects.equals(title, that.title) &&
                Objects.equals(posterUrl, that.posterUrl) &&
                Objects.equals(bookable, that.bookable) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, slug, title, posterUrl, bookable, price);
    }

    @OneToMany(cascade = {}, mappedBy = "show")
    public Collection<ArtisteTypeShowEntity> getArtistes() {
        return artistes;
    }

    public void setArtistes(Collection<ArtisteTypeShowEntity> artistes) {
        this.artistes = artistes;
    }

    @OneToMany(cascade = {}, mappedBy = "show")
    public Collection<RepresentationsEntity> getRepresentations() {
        return representations;
    }

    public void setRepresentations(Collection<RepresentationsEntity> representations) {
        this.representations = representations;
    }

    @ManyToOne(cascade = {})
    @JoinColumn(name = "location_id", referencedColumnName = "id", nullable = false, table = "")
    public LocationsEntity getLocation() {
        return location;
    }

    public void setLocation(LocationsEntity location) {
        this.location = location;
    }
}
