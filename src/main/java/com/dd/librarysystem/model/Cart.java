package com.dd.librarysystem.model;

import javax.persistence.*;
import java.security.PrivateKey;
import java.util.List;
import java.util.Set;

// 为一次加入购物车操作
@Entity
@Table(name = "cart")

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id")
    private Integer id;

    @Column(name = "reader_id")
    private Integer readerId;

    @Column(name = "submit_time")
    private String submitTime;

    @Column(name = "stat")
    private String stat;

    @OneToMany(targetEntity = Reserve.class, mappedBy = "reserveId",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Reserve> reserves;

    @OneToOne(targetEntity =Reader.class, mappedBy = "cart")
    private Reader reader;


    public List<Reserve> getReserves() {
        return reserves;
    }

    public void setReserves(List<Reserve> reserves) {
        this.reserves = reserves;
    }

    public Cart() {
    }

    public Cart(Integer cartId, Integer readerId, String submitTime, String stat, List<Reserve> reserves) {
        this.id = cartId;
        this.readerId = readerId;
        this.submitTime = submitTime;
        this.stat = stat;
        this.reserves = reserves;
    }

    public Cart(Cart cart) {
        this.id = cart.id;
        this.readerId = cart.readerId;
        this.submitTime = cart.submitTime;
        this.stat = cart.stat;
        this.reserves = cart.reserves;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", readerId=" + readerId +
                ", submitTime='" + submitTime + '\'' +
                ", stat='" + stat + '\'' +
                ", reserves=" + reserves +
                '}';
    }
}
