package petclinic.api;

import api.common.ApiClient;
import api.common.ApiRequest;
import api.common.ApiResponse;
import api.common.exception.InvalidResponseException;
import com.google.gson.GsonBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.http.Method;
import io.restassured.internal.mapping.GsonMapper;
import io.restassured.mapper.ObjectMapperType;
import petclinic.api.owners.data.Owner;



public class OwnersApiClient extends ApiClient {

    public OwnersApiClient(String baseUrl, String basePathOwners) {
        super(baseUrl, basePathOwners);

        ObjectMapperConfig config = new ObjectMapperConfig(ObjectMapperType.GSON)
                .gsonObjectMapperFactory((type, s) -> new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create());
        setObjectMapper(new GsonMapper(config.gsonObjectMapperFactory()));

    }

    public Owner[] getOwners() throws InvalidResponseException {

        ApiResponse<Owner[]> response = caller.executeRequest(getRequest(), Method.GET, Owner[].class);
        return response.getContent();

    }

    public Owner createOwner(Owner owner) throws InvalidResponseException {

        ApiRequest request = getRequest().withBody(owner).withHeader("Content-Type", "application/json");
        ApiResponse<Owner> response = caller.executeRequest(request, Method.POST, Owner.class);
        return response.getContent();
    }

    public ApiResponse<Owner[]> deleteId() {

        ApiResponse<Owner[]> response = caller.executeRequest(getRequest(), Method.DELETE, Owner[].class);
        return response;
    }

    public ApiResponse<Owner> getById()  {

        ApiResponse<Owner> response = caller.executeRequest(getRequest(), Method.GET, Owner.class);
        return response;
    }

    public Owner updateById(Owner getOwner) throws InvalidResponseException {

        ApiRequest request = getRequest().withBody(getOwner).withHeader("Content-Type", "application/json");
        ApiResponse<Owner> response = caller.executeRequest(request, Method.PUT, Owner.class);
        return response.getContent();
    }


}