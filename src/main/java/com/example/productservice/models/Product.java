package com.example.productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
public class Product extends BaseModel implements Serializable {

    private String title;
    private String description;
    @ManyToMany(cascade = {CascadeType.PERSIST},fetch = FetchType.EAGER )
    private List<Category> categoryList;
    private Double price;
    private String imageUrl;
}
