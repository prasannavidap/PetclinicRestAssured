import api.common.ApiResponse;
import api.common.exception.InvalidResponseException;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import com.petclinic.api.OwnersApiClient;
import com.petclinic.api.owners.data.Owner;

public class OwnersApiTest {

    static String apiUrl;
    SoftAssertions softly = new SoftAssertions();
    private String ownerId;

    @BeforeAll
    static void getApiUrl() {
        apiUrl = System.getProperty("apiUrl");
    }


    @Test
    //Fetching  the details of owner
    public void getOwners_checkFieldsMatches() throws InvalidResponseException {
        OwnersApiClient client = new OwnersApiClient(apiUrl,"/api/owners/");
        Owner[] owners = client.getOwners();

        softly.assertThat(owners[0].getFirstName()).isEqualTo("George");
        softly.assertThat(owners[0].getLastName()).isEqualTo("Franklin");
       // softly.assertThat(owners[0].getCity()).isEqualTo("Sun Prairie");
        softly.assertThat(owners[0].getId()).isGreaterThan("0");
        softly.assertThat(owners[0].getAddress()).isNotEmpty();
        softly.assertAll();
    }

    @Test
    //Creating the owner
    public void createOwner_addingDetails() throws InvalidResponseException {

        OwnersApiClient client = new OwnersApiClient(apiUrl,"/api/owners/");
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

    @Test
    //Fetching owner details by the newly created owner id
    public void getOwner_byId() throws InvalidResponseException {

        OwnersApiClient client = new OwnersApiClient(apiUrl,"/api/owners/");
        Owner createdOwner = client.createOwner(Owner.builder()
                .firstName("Monica")
                .lastName("Geller")
                .address("Central Perk")
                .city("NYC")
                .telephone("7896541236").build());
        ownerId = createdOwner.getId();

       OwnersApiClient clientRequest = new OwnersApiClient(apiUrl,"/api/owners/"+ownerId);
        Owner getOwner = clientRequest.getById().getContent();

        softly.assertThat(getOwner.getFirstName()).isEqualTo("Monica");
        softly.assertThat(getOwner.getLastName()).isEqualTo("Geller");
        softly.assertAll();

    }
   @Test
   @Disabled
    //Updating owner details by fetched owner details.
    public void updateOwner_addingDetails() throws InvalidResponseException {
        OwnersApiClient client = new OwnersApiClient(apiUrl,"/api/owners/");
        Owner createdOwner = client.createOwner(Owner.builder()
                .firstName("Monica")
                .lastName("Geller")
                .address("Central Perk")
                .city("NYC")
                .telephone("7896541236").build());
        ownerId = createdOwner.getId();

        OwnersApiClient clientRequest = new OwnersApiClient(apiUrl,"/api/owners/"+ownerId);
       Owner getOwner = clientRequest.getById().getContent();
       getOwner.setFirstName("Joey");
       Owner updateOwner = clientRequest.updateById(getOwner);
        softly.assertThat(updateOwner.getFirstName()).isEqualTo("Joey");
        softly.assertAll();
    }
    @Test
    //Deleting the owner details for the updated owner.
    public void deleteOwner_byId() throws InvalidResponseException {

        OwnersApiClient client = new OwnersApiClient(apiUrl,"/api/owners/");
        Owner createdOwner = client.createOwner(Owner.builder()
                .firstName("Monica")
                .lastName("Geller")
                .address("Central Perk")
                .city("NYC")
                .telephone("7896541236").build());
        ownerId = createdOwner.getId();

        OwnersApiClient clientRequest = new OwnersApiClient(apiUrl,"/api/owners/"+ownerId);

        ApiResponse<Owner[]> deleteOwner = clientRequest.deleteId();

        softly.assertThat(deleteOwner.getHttpStatusCode().equals(204));
        softly.assertAll();

    }

}

