package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemSeller;

	@FXML
	private MenuItem menuItemDepartment;

	@FXML
	private MenuItem menuItemAbout;

	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("OnMenuItemSellerAction");
	}

	@FXML
	public void onMenuItemDepartmentAction() {
		System.out.println("onMenuItemDepartmentAction");
	}

	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml");
	}

	@Override
	public void initialize(URL uri, ResourceBundle rb) {

	}

	// Função para abrir outra tela
		
	private synchronized void loadView(String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			// Para trabalhar com a janela principal será necessário pegar um referencia da scene
			Scene mainScene = Main.getMainScene();
			// getRoot() pega o primeiro elemento da view (ScrollPane)
			// getContent() acessa o conteudo da ScrollPane
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			
			// Precisamos preservar os filhos (Children) da MainViwe
			// get(0) pega o primeiro filho do VBox
			Node mainMenu = mainVBox.getChildren().get(0);
			// Limpa todos os children do mainVBox
			mainVBox.getChildren().clear();
			// Precisamos adicionar primeiro os filho do mainMenu
			mainVBox.getChildren().add(mainMenu);
			// E adicionar os filhos do newBox
			// addAll() adiciona uma coleção
			mainVBox.getChildren().addAll(newVBox);
			
			
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

}
