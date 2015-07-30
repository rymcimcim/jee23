package pl.jeeweb.zadanie23.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "ADDRESS")
public class Address implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "addr_id")
    private int addr_id;
    
    @Column(name = "type")
    private String type;
    
    @Column(name = "province")
    private String province;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "post1")
    private int post1;
    
    @Column(name = "post2")
    private int post2;
    
    @Column(name = "street")
    private String street;
    
    @Column(name = "house_nr")
    private int house_nr;
    
    @Column(name = "flat_nr")
    private int flat_nr;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public Address() {
    }

    public int getId() {
        return addr_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String typ) {
        this.type = typ;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String wojewodztwo) {
        this.province = wojewodztwo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String miasto) {
        this.city = miasto;
    }

    public int getPost1() {
        return post1;
    }

    public void setPost1(int kod1) {
        this.post1 = kod1;
    }

    public int getPost2() {
        return post2;
    }

    public void setPost2(int kod2) {
        this.post2 = kod2;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String ulica) {
        this.street = ulica;
    }

    public int getAddr_id() {
        return addr_id;
    }

    public int getHouse_nr() {
        return house_nr;
    }

    public void setHouse_nr(int house_nr) {
        this.house_nr = house_nr;
    }

    public int getFlat_nr() {
        return flat_nr;
    }

    public void setFlat_nr(int flat_nr) {
        this.flat_nr = flat_nr;
    }

}
