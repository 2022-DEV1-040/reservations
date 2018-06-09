package be.icc.entity;

import java.util.Collection;
import java.util.Objects;
import javax.persistence.*;
@Entity
@Table(name = "types", schema = "reservations", catalog = "")
public class TypesEntity {
    private Integer id;
    private String type;
    private Collection<ArtisteTypeEntity> artisteTypesById;

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
    @Column(name = "type", nullable = false, length = 60)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypesEntity that = (TypesEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type);
    }

    @OneToMany(cascade = {}, mappedBy = "typesByTypeId")
    public Collection<ArtisteTypeEntity> getArtisteTypesById() {
        return artisteTypesById;
    }

    public void setArtisteTypesById(Collection<ArtisteTypeEntity> artisteTypesById) {
        this.artisteTypesById = artisteTypesById;
    }
}
