package api.Owners.data;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Type {

    @Expose
    private int id;

    @Expose
    private String name;
}
