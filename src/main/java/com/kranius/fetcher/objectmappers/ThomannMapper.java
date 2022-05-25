package com.kranius.fetcher.objectmappers;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class ThomannMapper {
    private String name;
    private String brand;
    private String price;
    private String reference;

    @SuppressWarnings("unchecked")
    @JsonProperty("ecommerce")
    private void unpackNested(Map<String, Object> ecommerce) {

        Map<String, Object> detail = (Map<String, Object>) ecommerce.get("detail");
        Map<String, Object>[] products = (Map<String, Object>[]) detail.get("products");
        Map<String, Object> product = products[0];

        this.brand = (String) product.get("brand");
        this.name = (String) product.get("name");
        this.price = (String) product.get("price");
        this.reference = (String) product.get("reference");

//
//        this.brandName = (String)ecommerce.get("name");
//        Map<String,String> owner = (Map<String,String>)brand.get("owner");
//        this.ownerName = owner.get("name");

        //ecommerce.detail.products[0].name
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
