package petclinic.api.owners.data;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Getter;

import java.util.Arrays;

@Getter
@Builder
public class OwnerResponse {
    @Expose
    private Owner[] owners;

    @Override
    public String toString() {
        return "OwnerResponse[{" +
                "owners=" + Arrays.toString(owners) +
                "}]";
    }
}
