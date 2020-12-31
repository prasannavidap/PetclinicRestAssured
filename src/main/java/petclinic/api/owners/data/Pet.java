package petclinic.api.owners.data;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Getter;
import java.util.List;


@Getter
@Builder
public class Pet {

    @Expose
    private int id;

    @Expose
    private String name;

    @Expose
    private String birthDate;

    @Expose
    private Type type;

    @Expose
    private int owner;

    @Expose
    private List<Visit> visits;

}
