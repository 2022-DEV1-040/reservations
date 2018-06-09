package be.icc.entity;

import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "artiste_type", schema = "reservations", catalog = "")
public class ArtisteTypeEntity {
    private int id;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtisteTypeEntity that = (ArtisteTypeEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
