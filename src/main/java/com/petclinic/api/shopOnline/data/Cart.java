package com.petclinic.api.shopOnline.data;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Cart {

    @Expose
    private double amount;

    @Expose
    private String currency;

    @Expose
    private int id;

    @Expose
    private String name;

    @Expose
    private int quantity;


}
