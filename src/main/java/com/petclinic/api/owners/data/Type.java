package com.petclinic.api.owners.data;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Type {

    @Expose
    private  int id;

    @Expose
    private  String name;
}
