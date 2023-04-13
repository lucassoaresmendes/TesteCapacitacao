package javafxcrudcapacitacao;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXCrudCapacitacao extends Application {

    private static Scene scene;
    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/FXMLAnchorPaneListaProduto.fxml"));

        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public static void alterarTela(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader(JavaFXCrudCapacitacao.class.getResource("/javafxcrudcapacitacao/view/" + fxml + ".fxml"));
        scene.setRoot(loader.load());

    }
   

}
