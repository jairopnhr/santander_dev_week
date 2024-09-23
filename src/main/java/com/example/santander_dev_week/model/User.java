package com.example.santander_dev_week.model;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Schema(description = "Entidade Usuario")
@Table(name = "tb_user")
@Entity
public class User {
    
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Schema(description = "Indentificador do usuario")
    private Long id;
    @Schema(description = "Nome do usuario")
    private String name ;
    @Schema(description = "Conta do usuario")
    @OneToOne(cascade = CascadeType.ALL)
    private Account account;
    
    @Schema(description = "Fatura do usuario")
    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private List<Feature> feature ;
    @Schema(description = "Cartão do usuario")
    @OneToOne(cascade = CascadeType.ALL)
    private Card card;
    
    @OneToMany(cascade = CascadeType.ALL ,fetch =  FetchType.EAGER)
    private List<News> news;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Feature> getFeature() {
        return feature;
    }

    public void setFeature(List<Feature> feature) {
        this.feature = feature;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
