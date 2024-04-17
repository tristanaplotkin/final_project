package org.example.healthcare;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HealthcareController {

    @FXML
    private AnchorPane passwordAnchor, dataAnchor, topAnchor;

    @FXML
    private Button enterButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TableView<Patient> dataTableView;

    @FXML
    private TableColumn<Patient, String> nameColumn;

    @FXML
    private TableColumn<Patient, String> lastNameColumn;

    @FXML
    private TableColumn<Patient, String> bdColumn;

    @FXML
    private TableColumn<Patient, Character> genderColumn;

    @FXML
    private TableColumn<Patient, String> descriptionColumn;

    @FXML
    public void checkPassword() throws FileNotFoundException {
        PasswordReadWrite passwordReadWrite = new PasswordReadWrite();
        passwordReadWrite.readPasswords();
        ArrayList<Password> passwords = passwordReadWrite.getPasswords();
        for (Password password : passwords) {
            if (password.getPassword().equals(passwordField.getText())) {
                switchPage();
            }
        }
    }

    private void switchPage() {
        for (int i = 0; i < topAnchor.getChildren().size(); i++) {
            topAnchor.getChildren().get(i).setDisable(false);
        }

        for (int i = 0; i < passwordAnchor.getChildren().size(); i++) {
            passwordAnchor.getChildren().get(i).setVisible(false);
        }

        dataAnchor.setVisible(true);
        dataTableView.setVisible(true);
        tableInitialize();
    }

    private void tableInitialize() {
        dataTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        nameColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("firstname"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("lastname"));
        bdColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("birthdate"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<Patient, Character>("gender"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("conditions"));
        dataTableView.setItems(getPatients());
    }

    private ObservableList<Patient> getPatients() {
        ObservableList<Patient> patients = FXCollections.observableArrayList();

        try {
            String path = "src/main/resources/data/patients.csv";
            BufferedReader br = new BufferedReader(new FileReader(path));
            String header = br.readLine();
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\\s+");
                patients.add(new Patient(values[0], values[1], values[2], Character.toUpperCase(values[3].charAt(0)), values[4]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Cannot read the file");
        }

        int n = 5;
        for (int i = 0; i < n; i++) {
            patients.add(new Patient("test" + i, "test" + i, "test" + i, 'F', "test" + i));
        }

        return patients.sorted();
    }
}