package com.svirskiy.crm_hotelpet;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    @FXML
    private TreeView<String> treeView;
    @FXML
    private AnchorPane pane;

    private final Node rootIcon = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("menu.png"))));
    TreeItem<String> rootNode =
            new TreeItem<>("MyCompany Menu", rootIcon);

    List<MenuCategory> menuCategories = Arrays.asList(
            new MenuCategory("Sales Department", Arrays.asList(
                    new MenuItem("Ethan Williams"),
                    new MenuItem("Emma Jones"),
                    new MenuItem("Michael Brown"),
                    new MenuItem("Anna Black"),
                    new MenuItem("Rodger York"),
                    new MenuItem("Susan Collins")
            )),
            new MenuCategory("IT Support", Arrays.asList(
                    new MenuItem("Mike Graham"),
                    new MenuItem("Judy Mayer"),
                    new MenuItem("Gregory Smith")
            )),
            new MenuCategory("Accounts Department", Arrays.asList(
                    new MenuItem("Jacob Smith"),
                    new MenuItem("Isabella Johnson")
            ))
    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rootNode.setExpanded(true);
        for (MenuCategory category : menuCategories) {
            TreeItem<String> depNode = new TreeItem<>(category.getName());
            for (MenuItem item : category.getItems()) {
                TreeItem<String> itemLeaf = new TreeItem<>(item.getName());
                itemLeaf.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
                    System.out.println("Selected Item: " + item.getName());
                });
                depNode.getChildren().add(itemLeaf);
            }
            rootNode.getChildren().add(depNode);
        }
        treeView.setRoot(rootNode);

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("test.fxml"));
        FXMLLoader fxmlLoader1 = new FXMLLoader(Application.class.getResource("test2.fxml"));
        try {
            Node a = fxmlLoader.load();
            Node b = fxmlLoader1.load();

            treeView.setOnMouseClicked(e -> {
                System.out.println(e);
                System.out.println(treeView.getSelectionModel().getSelectedItems());
                if (treeView.getSelectionModel().getSelectedItems().get(0).getValue().equals("Emma Jones")) {
                    if (!pane.getChildren().isEmpty())
                        pane.getChildren().remove(0);
                    pane.getChildren().add(a);
                } else {
                    if (!pane.getChildren().isEmpty())
                        pane.getChildren().remove(0);
                    pane.getChildren().add(b);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("hello");
    }

    public static class MenuCategory {

        private final SimpleStringProperty name;
        private final List<MenuItem> items;

        private MenuCategory(String name, List<MenuItem> items) {
            this.name = new SimpleStringProperty(name);
            this.items = items;
        }

        public String getName() {
            return name.get();
        }

        public void setName(String fName) {
            name.set(fName);
        }

        public List<MenuItem> getItems() {
            return items;
        }
    }

    public static class MenuItem {

        private final SimpleStringProperty name;

        private MenuItem(String name) {
            this.name = new SimpleStringProperty(name);
        }

        public String getName() {
            return name.get();
        }

        public void setName(String fName) {
            name.set(fName);
        }
    }
}
