import api.common.ApiResponse;
import api.common.exception.InvalidResponseException;
import com.petclinic.api.OnlineShopClient;
import com.petclinic.api.shopOnline.data.Cart;
import com.petclinic.api.shopOnline.data.Order;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class OnlineShopTest {

    static String apiUrl;
    SoftAssertions softly = new SoftAssertions();

    @BeforeAll
    static void getApiUrl() {
        apiUrl = System.getProperty("apiUrl");
    }

    @Test
    //Fetching  the details of products
    public void getProducts_fromShopOnline() throws InvalidResponseException {

        OnlineShopClient clientRequest = new OnlineShopClient(apiUrl,"/api/shop/getproducts");
        Cart[] cart = clientRequest.getProducts();

        softly.assertThat(cart[0].getName()).as("This refers to Product name").isEqualTo("Kennel Kitchen");
        softly.assertThat(cart[0].getId()).isGreaterThan(0);
        softly.assertThat(cart[0].getCurrency()).as("Currency should be dollar").isEqualTo("$");
        softly.assertThat(cart[0].getAmount()).isNotNegative();
        softly.assertAll();
    }

    //Fetching  the details of order summary
    @Test
    public void getOrdersSummary_fromCart() throws InvalidResponseException {
        OnlineShopClient client = new OnlineShopClient(apiUrl,"/api/shop/addtocart");
        Cart createdOrder = client.createOrder(Cart.builder()
                .name("Velcote Liquid")
                .amount(500)
                .currency("$")
                .id(4)
                .build());
        OnlineShopClient clientRequest = new OnlineShopClient(apiUrl,"/api/shop/ordersummary");
       Order order = clientRequest.getOrderSummary().getContent();

        softly.assertThat(order.getTotalAmount()).isNotNull();
        softly.assertAll();
    }

    //Deleting the order summary details
    @Test
    public void deletingOrderSummaryDetails() throws InvalidResponseException {
        OnlineShopClient client = new OnlineShopClient(apiUrl,"/api/shop/addtocart");
        Cart createdOrder = client.createOrder(Cart.builder()
                .name("Velcote Liquid")
                .amount(50)
                .currency("$")
                .id(4)
                .build());

        OnlineShopClient clientRequest = new OnlineShopClient(apiUrl, "/api/shop/clearcart");
        ApiResponse<Cart[]> clearedCart = clientRequest.clearCart();

        softly.assertThat(clearedCart.getHttpStatusCode()).isEqualTo(200);
        softly.assertAll();
    }

    //Adding the products to the cart
    @Test
    public void createOrder_addingItemsToCart() throws InvalidResponseException {

        OnlineShopClient client = new OnlineShopClient(apiUrl,"/api/shop/addtocart");
        Cart createdOrder = client.createOrder(Cart.builder()
                .name("Velcote Liquid")
                .amount(500)
                .currency("$")
                .id(4)
                .build());

        softly.assertThat(createdOrder.getName()).isEqualTo("Velcote Liquid");
        softly.assertThat(createdOrder.getAmount()).isNotNegative();
        softly.assertThat(createdOrder.getCurrency()).isEqualTo("$");
        softly.assertThat(createdOrder.getId()).isNotNull();
        softly.assertAll();
    }

}
