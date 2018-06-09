package be.icc.entity;

import java.util.Objects;
import javax.persistence.*;
@Entity
@Table(name = "artiste_type_show", schema = "reservations", catalog = "")
public class ArtisteTypeShowEntity {
    private Integer id;
    private ArtisteTypeEntity artisteTypeByArtisteTypeId;
    private ShowsEntity showsByShowId;

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
    public ArtisteTypeEntity getArtisteTypeByArtisteTypeId() {
        return artisteTypeByArtisteTypeId;
    }

    public void setArtisteTypeByArtisteTypeId(ArtisteTypeEntity artisteTypeByArtisteTypeId) {
        this.artisteTypeByArtisteTypeId = artisteTypeByArtisteTypeId;
    }

    @ManyToOne(cascade = {})
    @JoinColumn(name = "show_id", referencedColumnName = "id", nullable = false, table = "")
    public ShowsEntity getShowsByShowId() {
        return showsByShowId;
    }

    public void setShowsByShowId(ShowsEntity showsByShowId) {
        this.showsByShowId = showsByShowId;
    }
}
