package javafxcrudcapacitacao.model.domain;

public class Embalagens {
    
    private int cdembalagem;
    private String descricao;
    private int quantidade;
    private int cdproduto;
    
    public Embalagens(Integer cdembalagem, String descricao, Integer quantidade, Integer cdproduto){
        this.cdembalagem = cdembalagem;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.cdproduto = cdproduto;
    }

    public int getCdembalagem() {
        return cdembalagem;
    }

    public void setCdembalagem(int cdembalagem) {
        this.cdembalagem = cdembalagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getCdproduto() {
        return cdproduto;
    }

    public void setCdproduto(int cdproduto) {
        this.cdproduto = cdproduto;
    }
    
    @Override
    public String toString() {
        return "Produtos{"+"cdembalagem="+cdembalagem+"decricao=" + descricao +"quantidade="+quantidade+"cdproduto="+cdproduto+ "}";
    }

}
