package javafxcrudcapacitacao.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafxcrudcapacitacao.API;
import javafxcrudcapacitacao.JavaFXCrudCapacitacao;
import javafxcrudcapacitacao.model.domain.Embalagens;
import javafxcrudcapacitacao.model.domain.Produtos;
import org.json.JSONArray;
import org.json.JSONObject;

public class FXMLAnchorPaneListaProdutoController implements Initializable {

    private Integer cdProdutoSelecionado = -1;

    private void mudarController() {
        Produtos produtoSelect = tableViewProdutos.getSelectionModel().getSelectedItem();
        FXMLAtulizarCadastroController.produto = produtoSelect;
    }

    public static Produtos produto;

    public void setProdutos(Produtos produto) {
        this.produto = produto;
    }

    @FXML
    private TableView<Produtos> tableViewProdutos;

    @FXML
    private TableColumn<Produtos, String> tableColumnProdutoDescricao;

    @FXML
    private TableColumn<Produtos, String> tableColumnProdutoMarca;

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
    private Button buttonInserir;

    @FXML
    private Button buttonAlterar;

    @FXML
    private Button buttonRemover;

    private ObservableList<Produtos> dados;

    private ObservableList<Embalagens> dadosEmbalagem;

    @Override
    public void initialize(URL url, ResourceBundle resources) {

        try {
            API api = new API();
            String teste = api.sendget();
            JSONArray jsonArray = new JSONArray(teste);
            this.dados = FXCollections.observableArrayList();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject objetoJson = jsonArray.getJSONObject(i);
                Integer cdProduto = objetoJson.getInt("cdproduto");
                String descricao = objetoJson.getString("descricao");
                String marca = objetoJson.getString("marca");
                String fabricante = objetoJson.getString("fabricante");
                String codigo_barra = objetoJson.getString("codigo_barra");
                double preco_custo = objetoJson.getDouble("preco_custo");
                double preco_venda = objetoJson.getDouble("preco_venda");
                dados.add(new Produtos(cdProduto, descricao, marca, fabricante, codigo_barra, preco_custo, preco_venda));
            }

            //Lança os dados na tabela de produto
            tableViewProdutos.getSelectionModel().selectedItemProperty().addListener((observable, prodAnte, novoProd) -> {
                if (novoProd != null) {
                    labelProdutoDescricao.setText(novoProd.getDescricao());
                    labelProdutoMarca.setText(novoProd.getMarca());
                    labelProdutoFabricante.setText(novoProd.getMarca());
                    labelProdutoCodigoBarra.setText(novoProd.getCodigo_barra());
                    labelProdutoPrecoCusto.setText(String.format("%.2f", novoProd.getPreco_custo()));
                    labelProdutoPrecoVenda.setText(String.format("%.2f", novoProd.getPreco_venda()));
                    if (novoProd != null) {
                        cdProdutoSelecionado = novoProd.getCdproduto();
                    }

                    if (novoProd != null) {
                        System.out.println("Produto selecionado" + novoProd.getDescricao());
                    }

                    try {

                        API api2 = new API();
                        String testeEmba = api2.sendgetEmbalagem(cdProdutoSelecionado.toString());
                        JSONArray jsonArray2 = new JSONArray(testeEmba);
                        this.dadosEmbalagem = FXCollections.observableArrayList();
                        for (int j = 0; j < jsonArray2.length(); j++) {
                            JSONObject objetoJsonEmba = jsonArray2.getJSONObject(j);
                            Integer cdembalagem = objetoJsonEmba.getInt("cdembalagem");
                            String descricao = objetoJsonEmba.getString("descricao");
                            Integer quantidade = objetoJsonEmba.getInt("quantidade");
                            Integer cdproduto = objetoJsonEmba.getInt("cdproduto");
                            dadosEmbalagem.add(new Embalagens(cdembalagem, descricao, quantidade, cdproduto));
                        }
                        tableColumnDescricaoEmbalagem.setCellValueFactory(new PropertyValueFactory<>("descricao"));
                        tableColumnQuantidadeEmbalagem.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
                        tableViewEmbalagem.setItems(dadosEmbalagem);

                    } catch (Exception t) {
                        t.printStackTrace();
                    }
                } else {
                    labelProdutoDescricao.setText("");
                    labelProdutoMarca.setText("");
                    labelProdutoFabricante.setText("");
                    labelProdutoCodigoBarra.setText("");
                    labelProdutoPrecoCusto.setText("");
                    labelProdutoPrecoVenda.setText("");
                }

            });
            tableViewEmbalagem.getSelectionModel().selectedItemProperty().addListener((obs, embaAnte, novaEmba) -> {
                if (novaEmba != null) {
                    txtDescricaoEmbalagem.setText(novaEmba.getDescricao());
                    txtQuantidadeEmbalagem.setText(Integer.toString(novaEmba.getQuantidade()));
                } else {
                    txtDescricaoEmbalagem.setText("");
                    txtQuantidadeEmbalagem.setText("");
                }
            });

            tableColumnProdutoDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
            tableColumnProdutoMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
            tableViewProdutos.setItems(dados);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Botão para remover
    @FXML
    public void handleButtonRemover() throws Exception {

        Produtos produtoSelecionado = tableViewProdutos.getSelectionModel().getSelectedItem();

        if (produtoSelecionado != null) {
            String cdProduto = String.valueOf(produtoSelecionado.getCdproduto());

            API api = new API();
            String resposta = api.sendDelete(cdProduto);

            if (resposta.equals("Deletado com sucesso")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso");
                alert.setHeaderText(null);
                alert.setContentText("Produto removido com sucesso!");
                alert.showAndWait();

                atualizarTabelaProdutos();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("Não foi possível remover o produto. Erro: " + resposta);
                alert.showAndWait();
            }
            atualizarTabelaProdutos();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione um produto para remover.");
            alert.showAndWait();
        }
    }

    //Busca o produto
    public ObservableList<Produtos> buscarProdutos() {
        try {
            API api = new API();
            String teste = api.sendget();
            JSONArray jsonArray = new JSONArray(teste);
            ObservableList<Produtos> dados = FXCollections.observableArrayList();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject objetoJson = jsonArray.getJSONObject(i);
                Integer cdProduto = objetoJson.getInt("cdproduto");
                String descricao = objetoJson.getString("descricao");
                String marca = objetoJson.getString("marca");
                String fabricante = objetoJson.getString("fabricante");
                String codigo_barra = objetoJson.getString("codigo_barra");
                double preco_custo = objetoJson.getDouble("preco_custo");
                double preco_venda = objetoJson.getDouble("preco_venda");
                dados.add(new Produtos(cdProduto, descricao, marca, fabricante, codigo_barra, preco_custo, preco_venda));
            }
            return dados;
        } catch (Exception a) {
            a.printStackTrace();
            return null;
        }
    }

    //Atualiza a tabela
    public void atualizarTabelaProdutos() {
        try {

            ObservableList<Produtos> listaProdutos = buscarProdutos();

            tableViewProdutos.getItems().clear();

            tableViewProdutos.getItems().addAll(listaProdutos);
        } catch (Exception e) {
            // Trata a exceção caso ocorra um erro ao recuperar a lista de produtos
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao recuperar lista de produtos");
            alert.setHeaderText(null);
            alert.setContentText("Ocorreu um erro ao tentar recuperar a lista de produtos. Por favor, tente novamente mais tarde.");
            alert.showAndWait();
        }
    }

    //Botão alterar produtos
    @FXML
    public void handleButtonAlterar() throws Exception {
        Produtos produtoSelect = tableViewProdutos.getSelectionModel().getSelectedItem();
        FXMLAtulizarCadastroController.produto = produtoSelect;
        JavaFXCrudCapacitacao.alterarTela("FXMLAtulizarCadastro");
    }

    @FXML
    public void handleButtonInserir() throws Exception {
        JavaFXCrudCapacitacao.alterarTela("FXMLAnchorPaneCadastrosProdutos");
    }

    //Parte das embalagens
    @FXML
    private TableView<Embalagens> tableViewEmbalagem;

    @FXML
    private TableColumn<Embalagens, String> tableColumnDescricaoEmbalagem;

    @FXML
    private TableColumn<Embalagens, String> tableColumnQuantidadeEmbalagem;

    @FXML
    private TextField txtDescricaoEmbalagem;

    @FXML
    private TextField txtQuantidadeEmbalagem;

    @FXML
    private Button btRemover;

    @FXML
    private Button btAlterar;

    @FXML
    private Button btInserir;

    @FXML
    public void handleBtAlterar() throws Exception {
        Embalagens embalagemSelecionada = tableViewEmbalagem.getSelectionModel().getSelectedItem();

        if (embalagemSelecionada != null) {

            JSONObject jsonEmbalagem = new JSONObject();
            jsonEmbalagem.put("descricao", txtDescricaoEmbalagem.getText());
            jsonEmbalagem.put("quantidade", Integer.parseInt(txtQuantidadeEmbalagem.getText()));

            API api = new API();
            String resposta = api.sendPutEmbalagem(jsonEmbalagem, cdProdutoSelecionado, embalagemSelecionada.getCdembalagem());

            atualizarTabelaEmbalagem();
            if (resposta.equals("Atualizado com sucesso")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso");
                alert.setHeaderText(null);
                alert.setContentText("Embalagem atualizada com sucesso!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("Não foi possível atualizar a embalagem. Erro: " + resposta);
                alert.showAndWait();
            }
            atualizarTabelaEmbalagem();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione uma embalagem para atualizar.");
            alert.showAndWait();
        }
        atualizarTabelaEmbalagem();
    }

    //Botão de inserir embalagem
    @FXML
    public void handleBtInserir() throws Exception {
        if (cdProdutoSelecionado != null) {
            JSONObject objetoJsonEmb = new JSONObject();
            objetoJsonEmb.put("descricao", txtDescricaoEmbalagem.getText());
            objetoJsonEmb.put("quantidade", txtQuantidadeEmbalagem.getText());
            objetoJsonEmb.put("cdproduto", cdProdutoSelecionado);
            API api = new API();
            System.out.println(objetoJsonEmb.toString());
            String resposta = api.sendPostEmbalagem(objetoJsonEmb, cdProdutoSelecionado.toString());
            if (resposta.equals("Erro ao inserir")) {
            }
            atualizarTabelaEmbalagem();
        }else{
            txtDescricaoEmbalagem.setText("");
            txtQuantidadeEmbalagem.setText("");
        }

    }

    //Botão de remover Embalagem
    @FXML
    public void handleBtRemover() throws Exception {

        Embalagens embalagemSelecionada = tableViewEmbalagem.getSelectionModel().getSelectedItem();

        if (embalagemSelecionada != null) {
            String cdEmbalagem = String.valueOf(embalagemSelecionada.getCdembalagem());

            API api = new API();
            String resposta = api.sendDeleteEmbalagem(cdProdutoSelecionado.toString(), cdEmbalagem);

            atualizarTabelaEmbalagem();
            if (resposta.equals("Deletado com sucesso")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso");
                alert.setHeaderText(null);
                alert.setContentText("Embalagem removida com sucesso!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("Não foi possível remover a embalagem. Erro: " + resposta);
                alert.showAndWait();
            }
            atualizarTabelaEmbalagem();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione uma embalagem para remover.");
            alert.showAndWait();
        }
        atualizarTabelaEmbalagem();

    }

    public ObservableList<Embalagens> buscarEmbalagens() {
        try {
            API api3 = new API();
            String teste3 = api3.sendgetEmbalagem(cdProdutoSelecionado.toString());
            JSONArray jsonArray = new JSONArray(teste3);
            ObservableList<Embalagens> dados3 = FXCollections.observableArrayList();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject objetoJson = jsonArray.getJSONObject(i);
                Integer cdEmbalagem = objetoJson.getInt("cdembalagem");
                String descricao = objetoJson.getString("descricao");
                Integer quantidade = objetoJson.getInt("quantidade");
                Integer cdProduto = objetoJson.getInt("cdproduto");
                dados3.add(new Embalagens(cdEmbalagem, descricao, quantidade, cdProduto));
            }
            return dados3;
        } catch (Exception a) {
            a.printStackTrace();
            return null;
        }
    }

    //Atualiza a tabela
    public void atualizarTabelaEmbalagem() {
        try {
            ObservableList<Embalagens> listaEmbalagem = buscarEmbalagens();
            tableViewEmbalagem.getItems().clear();
            tableViewEmbalagem.getItems().addAll(listaEmbalagem);
        } catch (Exception e) {
            // Trata a exceção caso ocorra um erro ao recuperar a lista de embalagens
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao recuperar lista de embalagens");
            alert.setHeaderText(null);
            alert.setContentText("Ocorreu um erro ao tentar recuperar a lista de embalagens. Por favor, tente novamente mais tarde.");
            alert.showAndWait();
        }
    }

}
