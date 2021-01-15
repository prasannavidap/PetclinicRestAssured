package com.petclinic.api.owners.data;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Visit {


    @Expose
    private  int id;

    @Expose
    private  String date;

    @Expose
    private  String description;

    @Expose
    private  int pet;
}
