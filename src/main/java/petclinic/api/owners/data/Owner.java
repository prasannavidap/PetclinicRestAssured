package petclinic.api.owners.data;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Getter;
import java.util.List;
@Getter
@Builder
public class Owner {



    @Expose
    private String id;

    @Expose
    private String firstName;

    @Override
    public String toString() {
        return "Owner{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", telephone='" + telephone + '\'' +
                ", pets=" + pets +
                '}';
    }

    @Expose
    private String lastName;

    @Expose
    private String address;

    @Expose
    private String city;

    @Expose
    private String telephone;

    @Expose
    private List<Pet> pets;

}