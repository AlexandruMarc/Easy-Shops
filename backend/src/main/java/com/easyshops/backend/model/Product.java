package com.easyshops.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String brand;
  private BigDecimal price;
  private int inventory;
  private String description;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;


  //orphan is it in case I want to delete a product all the images related with that product will be removed
  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL,
             orphanRemoval = true)
  @OrderBy("id ASC")
  private List<Image> images;

  public Product(String name, String brand, BigDecimal price, int inventory, String description, Category category) {
    this.name = name;
    this.brand = brand;
    this.price = price;
    this.inventory = inventory;
    this.description = description;
    this.category = category;
  }
}
