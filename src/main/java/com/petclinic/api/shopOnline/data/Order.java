package com.petclinic.api.shopOnline.data;


import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Order {

    @Expose
    private List<Cart> cart;

    @Expose
    private double totalAmount;

}
