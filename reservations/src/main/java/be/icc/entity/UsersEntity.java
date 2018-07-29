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
import java.util.Collection;
import java.util.Objects;
@Entity
@Table(name = "users", schema = "reservations", catalog = "")
public class UsersEntity {
    private Integer id;
    private String login;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String langue;
    private Collection<RepresentationUserEntity> representationUsers;
    private RolesEntity role;

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
    @Column(name = "login", nullable = false, length = 30)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "firstname", nullable = false, length = 60)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "lastname", nullable = false, length = 60)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "langue", nullable = false, length = 2)
    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(login, that.login) &&
                Objects.equals(password, that.password) &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(email, that.email) &&
                Objects.equals(langue, that.langue);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, login, password, firstname, lastname, email, langue);
    }

    @OneToMany(cascade = {}, mappedBy = "user")
    public Collection<RepresentationUserEntity> getRepresentationUsers() {
        return representationUsers;
    }

    public void setRepresentationUsers(Collection<RepresentationUserEntity> representationUsers) {
        this.representationUsers = representationUsers;
    }

    @ManyToOne(cascade = {})
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false, table = "")
    public RolesEntity getRole() {
        return role;
    }

    public void setRole(RolesEntity role) {
        this.role = role;
    }
}
