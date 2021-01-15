package com.petclinic.api;

import api.common.ApiClient;
import api.common.ApiRequest;
import api.common.ApiResponse;
import api.common.exception.InvalidResponseException;
import com.google.gson.GsonBuilder;
import com.petclinic.api.shopOnline.data.Cart;
import com.petclinic.api.shopOnline.data.Order;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.http.Method;
import io.restassured.internal.mapping.GsonMapper;
import io.restassured.mapper.ObjectMapperType;

public class OnlineShopClient extends ApiClient {

    public OnlineShopClient(String baseUrl, String basePathOrders) {
        super(baseUrl, basePathOrders);

        ObjectMapperConfig config = new ObjectMapperConfig(ObjectMapperType.GSON)
                .gsonObjectMapperFactory((type, s) -> new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create());
        setObjectMapper(new GsonMapper(config.gsonObjectMapperFactory()));

    }

    public Cart[] getProducts() throws InvalidResponseException {

        ApiResponse<Cart[]> response = caller.executeRequest(getRequest(), Method.GET, Cart[].class);
        return response.getContent();

    }

    public ApiResponse<Order> getOrderSummary() {

        ApiResponse<Order> response = caller.executeRequest(getRequest(), Method.GET, Order.class);
        return response;

    }

    public ApiResponse<Cart[]> clearCart() {
        ApiResponse<Cart[]> response = caller.executeRequest(getRequest(), Method.DELETE, Cart[].class);
        return response;
    }

    public Cart createOrder(Cart cart) throws InvalidResponseException {

        ApiRequest request = getRequest().withBody(cart).withHeader("Content-Type", "application/json");
        ApiResponse<Cart> response = caller.executeRequest(request, Method.POST, Cart.class);
        return response.getContent();
    }
}
