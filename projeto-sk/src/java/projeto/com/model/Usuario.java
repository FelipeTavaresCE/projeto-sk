package projeto.com.model;

import java.util.List;

public class Usuario {

    private int idusuario;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private List<Endereco> endereco;
    private List<Pedidos> pedido;

    public List<Pedidos> getPedido() {
        return pedido;
    }

    public void setPedido(List<Pedidos> pedido) {
        this.pedido = pedido;
    }

    public List<Endereco> getEndereco() {
        return endereco;
    }

    public void setEndereco(List<Endereco> endereco) {
        this.endereco = endereco;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
