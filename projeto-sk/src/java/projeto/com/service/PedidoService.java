package projeto.com.service;

import java.util.List;
import projeto.com.model.Pedidos;

public interface PedidoService {
    
    public boolean efetuarPedido (Pedidos pedidos);
    public boolean deltarPedido(Pedidos pedido);
    public List<Pedidos> exibirPedidosPorUsuario (int usuario);
    public boolean atualizarPedido(Pedidos pedido);
    public boolean verificaPedidoCadastrado(Pedidos pedido);
    
}
