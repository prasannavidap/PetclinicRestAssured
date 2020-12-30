/*
import api.common.exception.InvalidResponseException;
import petclinic.api.stores.StoresApiClient;
import petclinic.api.stores.data.Store;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class StoresApiTest {

    static String apiUrl;

    @BeforeAll
    static void getApiUrl() {
        apiUrl = System.getProperty("apiUrl");
    }

    @Test
    public void getStores_DefaultLimit_ShouldReturn10Results() throws InvalidResponseException {
        StoresApiClient client = new StoresApiClient(apiUrl);
        List<Store> storeList = client.getStores();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(storeList.size()).isEqualTo(10);
        softly.assertThat(storeList.stream().allMatch(store -> store.getId() > 0)).isTrue();
        softly.assertAll();
    }
}
*/
