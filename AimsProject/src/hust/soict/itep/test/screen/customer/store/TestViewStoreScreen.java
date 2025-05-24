```java
package hust.soict.itep.test.screen.customer.store;

import hust.soict.itep.aims.cart.Cart;
import hust.soict.itep.aims.media.DigitalVideoDisc;
import hust.soict.itep.aims.store.Store;
import hust.soict.itep.aims.screen.customer.controller.ViewStoreController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestViewStoreScreen extends Application {
    private Store store;
    private Cart cart;

    @Override
    public void start(Stage stage) throws Exception {
        // Khởi tạo Store và Cart
        store = new Store();
        cart = new Cart();

        // Thêm dữ liệu mẫu vào Store
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladdin", "Animation", null, 0, 18.99f);
        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(dvd3);

        // Tải Store.fxml và thiết lập ViewStoreController
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/hust/soict/itep/aims/screen/customer/view/Store.fxml"));
        fxmlLoader.setController(new ViewStoreController(store, cart));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Test View Store Screen");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
```