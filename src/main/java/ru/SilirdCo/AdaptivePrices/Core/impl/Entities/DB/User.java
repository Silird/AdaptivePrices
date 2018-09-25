package ru.SilirdCo.AdaptivePrices.Core.impl.Entities.DB;

import org.hibernate.annotations.GenericGenerator;
import ru.SilirdCo.AdaptivePrices.Core.impl.Entities.BaseEntity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table(name = "user1", schema = "public")
@XmlRootElement
public class User extends BaseEntity implements Serializable {

    @Id
    @Basic(optional = false)
    @GenericGenerator(name = "key_gen", strategy = "increment")
    @GeneratedValue(generator = "key_gen")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", columnDefinition="TEXT")
    private String name;

    @Column(name = "password", columnDefinition="TEXT")
    private String password;

    @Column(name = "admin")
    private Boolean admin;

    //@JoinColumn(name = "id_parent", referencedColumnName = "id")
    //@ManyToOne(fetch = FetchType.LAZY)
    //private User parent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if((object == null) || (getClass()!= object.getClass())) return  false;

        User other = (User) object;
        if ((this.id == null) && (other.id != null)) return false;
        if  ((this.id != null) && (!this.id.equals(other.id))) return false;

        return true;
    }

    @Override
    public String toString() {
        String result;
        if ((name == null)) {
            result = "[Без названия]";
        }
        else {
            result = "" + name;
        }
        return result;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
