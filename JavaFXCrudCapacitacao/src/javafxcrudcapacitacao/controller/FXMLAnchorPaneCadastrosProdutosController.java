package javafxcrudcapacitacao.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafxcrudcapacitacao.API;
import javafxcrudcapacitacao.JavaFXCrudCapacitacao;
import org.json.JSONObject;

public class FXMLAnchorPaneCadastrosProdutosController implements Initializable {

    @FXML
    private Label labelProdutoDescricao;

    @FXML
    private Label labelProdutoMarca;

    @FXML
    private Label labelProdutoFabricante;

    @FXML
    private Label labelProdutoPrecoCusto;

    @FXML
    private Label labelProdutoPrecoVenda;

    @FXML
    private Label labelProdutoCodigoBarra;

    @FXML
    private TextField textFieldProdutoDescricao;

    @FXML
    private TextField textFieldProdutoMarca;

    @FXML
    private TextField textFieldProdutoFabricante;

    @FXML
    private TextField textFieldProdutoPrecoCusto;

    @FXML
    private TextField textFieldProdutoPrecoVenda;

    @FXML
    private TextField textFieldProdutoCodigoBarra;

    @FXML
    private Button buttonConfirmar;

    @FXML
    private Button buttonCancelar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    //Ação do botão de confirmar
    @FXML
    public void handleButtonConfirmar() throws Exception {
        JSONObject objetoJson = new JSONObject();
        objetoJson.put("descricao", textFieldProdutoDescricao.getText());
        objetoJson.put("marca", textFieldProdutoMarca.getText());
        objetoJson.put("fabricante", textFieldProdutoFabricante.getText());
        objetoJson.put("preco_custo", textFieldProdutoPrecoCusto.getText());
        objetoJson.put("preco_venda", textFieldProdutoPrecoVenda.getText());
        objetoJson.put("codigo_barra", textFieldProdutoCodigoBarra.getText());

        API api = new API();
        System.out.println(objetoJson.toString());
        String resposta = api.sendPost(objetoJson);
        if (resposta.equals("Erro ao inserir")) {
        }
        JavaFXCrudCapacitacao.alterarTela("FXMLAnchorPaneListaProduto");
    }

    //Ação do botão de cancelar
    //Mudar para voltar para a lista
    @FXML
    public void handleButtonCancelar() throws Exception {
        JavaFXCrudCapacitacao.alterarTela("FXMLAnchorPaneListaProduto");
    }

}
