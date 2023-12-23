package com.salmaboubaker.projet.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference", unique = true, nullable = false)
    private String reference;

    @Column(name = "designation", unique = true, nullable = false)
    private String designation;

    @Column(name = "prix")
    private Double prix;

    @Column(name = "marque", nullable = false)
    private String marque;

    @Column(name = "qtestock")
    private Integer qtestock;

    @Column(name = "imageart")
    private String imageart;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public Integer getQtestock() {
        return qtestock;
    }

    public void setQtestock(Integer qtestock) {
        this.qtestock = qtestock;
    }

    public String getImageart() {
        return imageart;
    }

    public void setImageart(String imageart) {
        this.imageart = imageart;
    }


}
