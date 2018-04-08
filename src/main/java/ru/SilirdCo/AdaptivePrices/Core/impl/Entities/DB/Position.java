package ru.SilirdCo.AdaptivePrices.Core.impl.Entities.DB;

import org.hibernate.annotations.GenericGenerator;
import ru.SilirdCo.AdaptivePrices.Core.impl.Entities.BaseEntity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table(name = "position", schema = "public")
@XmlRootElement
public class Position extends BaseEntity implements Serializable {

    @Id
    @Basic(optional = false)
    @GenericGenerator(name = "key_gen", strategy = "increment")
    @GeneratedValue(generator = "key_gen")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", columnDefinition="TEXT")
    private String name;

    @Column(name = "price")
    private Float price;

    @Column(name = "defaultPrice")
    private Float defaultPrice;

    @Column(name = "sales")
    private Float sales;

    @Column(name = "min_price")
    private Float minPrice;

    @Column(name = "max_price")
    private Float maxPrice;

    @Column(name = "increase")
    private Boolean increase;

    @Column(name = "use")
    private Boolean use;

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

        Position other = (Position) object;
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

    public Float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Float minPrice) {
        this.minPrice = minPrice;
    }

    public Float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Float maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Boolean getUse() {
        return use;
    }

    public void setUse(Boolean use) {
        this.use = use;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getSales() {
        return sales;
    }

    public void setSales(Float sales) {
        this.sales = sales;
    }

    public Boolean getIncrease() {
        return increase;
    }

    public void setIncrease(Boolean increase) {
        this.increase = increase;
    }

    public Float getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(Float defaultPrice) {
        this.defaultPrice = defaultPrice;
    }
}
