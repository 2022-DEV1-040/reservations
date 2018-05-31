package entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "representations", schema = "reservations", catalog = "")
public class RepresentationsEntity {
    private int id;
    private Timestamp when;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "when")
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
        return id == that.id &&
                Objects.equals(when, that.when);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, when);
    }
}
