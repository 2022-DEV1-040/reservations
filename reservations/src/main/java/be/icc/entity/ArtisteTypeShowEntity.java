package be.icc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;
@Entity
@Table(name = "artiste_type_show", schema = "reservations", catalog = "")
public class ArtisteTypeShowEntity {
    private Integer id;
    private ArtisteTypeEntity artisteType;
    private ShowsEntity show;

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtisteTypeShowEntity that = (ArtisteTypeShowEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @ManyToOne(cascade = {})
    @JoinColumn(name = "artiste_type_id", referencedColumnName = "id", nullable = false, table = "")
    public ArtisteTypeEntity getArtisteType() {
        return artisteType;
    }

    public void setArtisteType(ArtisteTypeEntity artisteType) {
        this.artisteType = artisteType;
    }

    @ManyToOne(cascade = {})
    @JoinColumn(name = "show_id", referencedColumnName = "id", nullable = false, table = "")
    public ShowsEntity getShow() {
        return show;
    }

    public void setShow(ShowsEntity show) {
        this.show = show;
    }
}
