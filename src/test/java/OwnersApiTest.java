import api.common.ApiResponse;
import api.common.exception.InvalidResponseException;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import petclinic.api.OwnersApiClient;
import petclinic.api.owners.data.Owner;

public class OwnersApiTest {

    static String apiUrl;
    SoftAssertions softly = new SoftAssertions();
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

        //Using wait to get the details fetched for newly created owner
        Thread.sleep(4000);

        //3. Fetching owner details by newly created id in 2. Method- GET
        getOwner_byId();

        Thread.sleep(3000);

        //4.Updating the owner details. Method- PUT
        updateOwner_addingDetails();

        //5. Deleting the owner details. Method -DELETE
        deleteOwner_byId();
    }


    //Fetching  the details of owner
    public void getOwners_checkFieldsMatches() throws InvalidResponseException {
        OwnersApiClient client = new OwnersApiClient(apiUrl);
        Owner[] owners = client.getOwners();

        softly.assertThat(owners[0].getFirstName()).isEqualTo("Betty");
        softly.assertThat(owners[0].getLastName()).isEqualTo("Davis");
        softly.assertThat(owners[0].getCity()).isEqualTo("Sun Prairie");
        softly.assertThat(owners[0].getId()).isGreaterThan("0");
        softly.assertThat(owners[0].getAddress()).isNotEmpty();
        softly.assertAll();
    }

    //Creating the owner
    public void createOwner_addingDetails() throws InvalidResponseException {

        OwnersApiClient client = new OwnersApiClient(apiUrl);
        Owner createdOwner = client.createOwner(Owner.builder()
                .firstName("Monica")
                .lastName("Geller")
                .address("Central Perk")
                .city("NYC")
                .telephone("7896541236").build());
        ownerId = createdOwner.getId();

        softly.assertThat(createdOwner.getFirstName()).isEqualTo("Monica");
        softly.assertThat(createdOwner.getLastName()).isEqualTo("Geller");
        softly.assertThat(createdOwner.getId()).isNotEmpty();
        softly.assertAll();
    }

    //Fetching owner details by the newly created owner id
    public void getOwner_byId() throws InvalidResponseException {

        OwnersApiClient client = new OwnersApiClient(apiUrl, ownerId);

        Owner getOwner = client.getById().getContent();

        softly.assertThat(getOwner.getFirstName()).isEqualTo("Monica");
        softly.assertThat(getOwner.getLastName()).isEqualTo("Geller");
        softly.assertAll();


    }

    //Updating owner details by fetched owner details.
    public void updateOwner_addingDetails() throws InvalidResponseException {
        OwnersApiClient client = new OwnersApiClient(apiUrl, ownerId);
        Owner getOwner = client.getById().getContent();

        getOwner.setFirstName("Joey");

        Owner updateOwner = client.updateById(getOwner);

        softly.assertThat(updateOwner.getFirstName()).isEqualTo("Joey");
        softly.assertAll();
    }

    //Deleting the owner details for the updated owner.
    public void deleteOwner_byId() throws InvalidResponseException {

        OwnersApiClient client = new OwnersApiClient(apiUrl, ownerId);

        ApiResponse<Owner[]> deleteOwner = client.deleteId();

        ApiResponse<Owner> getOwner = client.getById();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(deleteOwner.getHttpStatusCode().equals(204));
        softly.assertThat(getOwner.getHttpStatusCode().equals(404));
        softly.assertAll();

    }

}

