package projeto.com.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import projeto.com.model.Pedidos;
import projeto.com.serviceImpl.PedidoServiceImpl;

@Controller
public class PedidosController {

    PedidoServiceImpl service = new PedidoServiceImpl();

    @RequestMapping(value = "/efetuarPedido", method = RequestMethod.POST)
    public @ResponseBody
    Pedidos efetuarPedido(@RequestBody Pedidos pedido) {
        if (!service.verificaPedidoCadastrado(pedido)) {
            service.efetuarPedido(pedido);
        }
        return pedido;
    }

    @RequestMapping(value = "/exibirPedidos", method = RequestMethod.GET)
    public @ResponseBody
    List<Pedidos> exibirPedidos(int usuario) {
        List<Pedidos> pedidos = service.exibirPedidosPorUsuario(usuario);
        return pedidos;
    }

    @RequestMapping(value = "/excluirPedido", method = RequestMethod.POST)
    public @ResponseBody
    Pedidos excluirEndereco(@RequestBody Pedidos pedido) {
        service.deltarPedido(pedido);
        return pedido;
    }

    @RequestMapping(value = "/alterarPedido", method = RequestMethod.POST)
    public @ResponseBody
    Pedidos atualizarEndereco(@RequestBody Pedidos pedido) {
        service.atualizarPedido(pedido);
        return pedido;
    }
}
