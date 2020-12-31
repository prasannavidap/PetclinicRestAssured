import api.common.ApiResponse;
import api.common.exception.InvalidResponseException;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import petclinic.api.OwnersApiClient;
import petclinic.api.owners.data.Owner;

public class OwnersApiTest {

    static String apiUrl;
    private String ownerId;

    @BeforeAll
    static void getApiUrl() {
        apiUrl = System.getProperty("apiUrl");
    }

    @Test
    public void owners_crud_operations() throws InvalidResponseException, InterruptedException {

        //1. Fetching the details of Owners. Method- GET
        getOwners_checkFieldsMatches();

        //2. Creating the Owner. Method- POST
        createOwner_addingDetails();
    }



    public void getOwners_checkFieldsMatches() throws InvalidResponseException {
        OwnersApiClient client = new OwnersApiClient(apiUrl);
        Owner[] owners = client.getOwners();

        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(owners[0].getFirstName()).isEqualTo("Betty");
        softly.assertThat(owners[0].getLastName()).isEqualTo("Davis");
        softly.assertThat(owners[0].getCity()).isEqualTo("Sun Prairie");
        softly.assertAll();
    }


    public void createOwner_addingDetails() throws InvalidResponseException {

        OwnersApiClient client = new OwnersApiClient(apiUrl);
        Owner createdOwner = client.createOwner(Owner.builder()
                .firstName("Monica")
                .lastName("Geller")
                .address("Central Perk")
                .city("NYC")
                .telephone("7896541236").build());
        ownerId = createdOwner.getId();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(createdOwner.getFirstName()).isEqualTo("Monica");
        softly.assertAll();
    }





    @Disabled
    public void getOwner_byId() throws InvalidResponseException{

        OwnersApiClient client=new OwnersApiClient(apiUrl,ownerId);

        Owner getOwner = client.getById();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(getOwner.getFirstName()).isEqualTo("Monica");
        softly.assertThat(getOwner.getLastName()).isEqualTo("Geller");
        softly.assertAll();


    }

    @Disabled
    public void updateOwner_addingDetails() throws InvalidResponseException {
        OwnersApiClient client = new OwnersApiClient(apiUrl, ownerId);
        Owner getOwner = client.getById();

        getOwner.setFirstName("Name Updated");

        Owner updateOwner = client.updateById(getOwner);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(updateOwner.getFirstName()).isEqualTo("Name Updated");
        softly.assertAll();
    }

    @Disabled
    public void deleteOwner_byId(){

        OwnersApiClient client=new OwnersApiClient(apiUrl,ownerId);

        ApiResponse<Owner[]> deleteOwner = client.deleteId();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(deleteOwner.getHttpStatusCode().equals(204));
        softly.assertAll();

    }

}

