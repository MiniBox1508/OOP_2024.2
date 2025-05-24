```java
package hust.soict.itep.aims.screen.customer.controller;

import hust.soict.itep.aims.store.Store;
import hust.soict.itep.aims.cart.Cart;
import hust.soict.itep.aims.media.Media;
import hust.soict.itep.aims.media.Playable;
import hust.soict.itep.aims.exception.PlayerException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import java.io.IOException;

public class CartController {
    private Store store;
    private Cart cart;
    private FilteredList<Media> filteredMedia;
    private FloatProperty totalCost;

    @FXML
    private TableView<Media> tblMedia;
    @FXML
    private TableColumn<Media, Integer> colMediaId;
    @FXML
    private TableColumn<Media, String> colMediaTitle;
    @FXML
    private TableColumn<Media, String> colMediaCategory;
    @FXML
    private TableColumn<Media, Float> colMediaCost;
    @FXML
    private Button btnPlay;
    @FXML
    private Button btnRemove;
    @FXML
    private Button btnPlaceOrder;
    @FXML
    private Button btnViewStore;
    @FXML
    private Label lblTotalCost;
    @FXML
    private TextField tfFilter;
    @FXML
    private RadioButton radioBtnFilterId;
    @FXML
    private RadioButton radioBtnFilterTitle;
    @FXML
    private ToggleGroup filterGroup;

    public CartController(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
        this.totalCost = new SimpleFloatProperty(0.0f);
    }

    @FXML
    public void initialize() {
        colMediaId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        colMediaTitle.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        colMediaCategory.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory()));
        colMediaCost.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getCost()).asObject());

        filteredMedia = new FilteredList<>(cart.getItemsOrdered(), p -> true);
        tblMedia.setItems(filteredMedia);

        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        tblMedia.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            btnPlay.setVisible(newSelection instanceof Playable);
            btnRemove.setVisible(newSelection != null);
        });

        totalCost.addListener((obs, oldValue, newValue) -> {
            lblTotalCost.setText(String.format("%.2f $", newValue.floatValue()));
        });
        updateTotalCost();

        tfFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredMedia.setPredicate(media -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (radioBtnFilterId.isSelected()) {
                    return String.valueOf(media.getId()).contains(lowerCaseFilter);
                } else {
                    return media.getTitle().toLowerCase().contains(lowerCaseFilter);
                }
            });
        });
    }

    private void updateTotalCost() {
        float total = 0.0f;
        for (Media media : cart.getItemsOrdered()) {
            total += media.getCost();
        }
        totalCost.set(total);
    }

    @FXML
    void btnPlayPressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media instanceof Playable) {
            try {
                ((Playable) media).play();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Play Media");
                alert.setHeaderText(null);
                alert.setContentText("Playing: " + media.getTitle());
                alert.showAndWait();
            } catch (PlayerException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Playback Error");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }

    @FXML
    void btnRemovePressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media != null) {
            cart.removeMedia(media);
            updateTotalCost();
        }
    }

    @FXML
    void btnPlaceOrderPressed(ActionEvent event) {
        cart.getItemsOrdered().clear();
        updateTotalCost();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Placed");
        alert.setHeaderText(null);
        alert.setContentText("Your order has been placed successfully!");
        alert.showAndWait();
    }

    @FXML
    void btnViewStorePressed(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/hust/soict/itep/aims/screen/customer/view/Store.fxml"));
            fxmlLoader.setController(new ViewStoreController(store, cart));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) btnViewStore.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```