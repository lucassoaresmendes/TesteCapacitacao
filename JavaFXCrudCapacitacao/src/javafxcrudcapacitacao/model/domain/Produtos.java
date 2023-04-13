package javafxcrudcapacitacao.model.domain;

import java.io.Serializable;

public class Produtos implements Serializable {

    private int cdproduto;
    private String descricao;
    private String marca;
    private String fabricante;
    private String codigo_barra;
    private double preco_custo;
    private double preco_venda;

    public Produtos(Integer cdproduto, String descricao, String marca, String fabricante, String codigo_barra, double preco_custo, double preco_venda) {
        this.cdproduto = cdproduto;
        this.descricao = descricao;
        this.marca = marca;
        this.fabricante = fabricante;
        this.preco_custo = preco_custo;
        this.preco_venda = preco_venda;
        this.codigo_barra = codigo_barra;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getCdproduto() {
        return cdproduto;
    }

    public void setCdproduto(int cdproduto) {
        this.cdproduto = cdproduto;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public double getPreco_custo() {
        return preco_custo;
    }

    public void setPreco_custo(Double preco_custo) {
        this.preco_custo = preco_custo;
    }

    public double getPreco_venda() {
        return preco_venda;
    }

    public void setPreco_venda(Double preco_venda) {
        this.preco_venda = preco_venda;
    }

    public String getCodigo_barra() {
        return codigo_barra;
    }

    public void setCodigo_barra(String codigo_barra) {
        this.codigo_barra = codigo_barra;
    }

    @Override
    public String toString() {
        return "Produtos{"+"cdproduto="+cdproduto+"decricao=" + descricao + ", marca=" + marca + ", fabricante" + fabricante + ", preco_custo" + preco_custo + ", preco_venda" + preco_venda + ", codigo_barra" + codigo_barra + "}";
    }

}
