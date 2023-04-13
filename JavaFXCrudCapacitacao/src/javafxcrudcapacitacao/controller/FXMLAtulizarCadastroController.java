package javafxcrudcapacitacao.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafxcrudcapacitacao.API;
import javafxcrudcapacitacao.JavaFXCrudCapacitacao;
import javafxcrudcapacitacao.model.domain.Produtos;
import org.json.JSONObject;

public class FXMLAtulizarCadastroController implements Initializable {

    private Integer cdProdSelecionado = -1;

    public static Produtos produto;

    public void setProdutos(Produtos produto) {
        txtDescricao.setText(produto.getDescricao());
        txtMarca.setText(produto.getMarca());
        txtFabricante.setText(produto.getFabricante());
        txtPrecoCusto.setText(String.valueOf(produto.getPreco_custo()));
        txtPrecoVenda.setText(String.valueOf(produto.getPreco_venda()));
        txtCodigoBarra.setText(produto.getCodigo_barra());
    }

    @FXML
    private TextField txtDescricao;

    @FXML
    private TextField txtMarca;

    @FXML
    private TextField txtFabricante;

    @FXML
    private TextField txtPrecoCusto;

    @FXML
    private TextField txtPrecoVenda;

    @FXML
    private TextField txtCodigoBarra;

    @FXML
    private Button buttonAtualizar;

    @FXML
    private Button buttonCancelar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            setProdutos(produto);
        } catch (Exception a) {
            a.printStackTrace();
        }

    }

    @FXML
    public void handleButtonAtualizar() throws Exception {

        if (produto != null) {

            JSONObject jsonProduto = new JSONObject();
            jsonProduto.put("descricao", txtDescricao.getText());
            jsonProduto.put("marca", txtMarca.getText());
            jsonProduto.put("fabricante", txtFabricante.getText());
            jsonProduto.put("codigo_barra", txtCodigoBarra.getText());
            jsonProduto.put("preco_custo", Integer.parseInt(txtPrecoCusto.getText()));
            jsonProduto.put("preco_venda", Integer.parseInt(txtPrecoVenda.getText()));

            API api = new API();
            String resposta = api.sendPut(jsonProduto, String.valueOf(produto.getCdproduto()));

            if (resposta.equals("Atualizado com sucesso")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso");
                alert.setHeaderText(null);
                alert.setContentText("Produto atualizado com sucesso!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("Não foi possível atualizar o produto. Erro: " + resposta);
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione um produto para atualizar.");
            alert.showAndWait();
        }
        JavaFXCrudCapacitacao.alterarTela("FXMLAnchorPaneListaProduto");
    }

    @FXML
    public void handleButtonCancelar() throws Exception {
        JavaFXCrudCapacitacao.alterarTela("FXMLAnchorPaneListaProduto");
    }

}
