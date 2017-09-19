package projeto.com.model;

public class Pedidos {
    
    private int idPedidos;
    private Usuario usuario;
    private Produtos produto;
    private Endereco endereco;
    
    public Pedidos (){
        usuario = new Usuario();
        produto = new Produtos();
        endereco = new Endereco();
    }

    public int getIdPedidos() {
        return idPedidos;
    }

    public void setIdPedidos(int idPedidos) {
        this.idPedidos = idPedidos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Produtos getProduto() {
        return produto;
    }

    public void setProduto(Produtos produto) {
        this.produto = produto;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    
}
