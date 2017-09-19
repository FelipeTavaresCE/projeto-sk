package projeto.com.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import projeto.com.model.Produtos;
import projeto.com.serviceImpl.ProdutoServiceImpl;

@Controller
public class ProdutosController {

    ProdutoServiceImpl service = new ProdutoServiceImpl();

    @RequestMapping(value = "/exibirProdutos", method = RequestMethod.GET)
    public @ResponseBody
    List<Produtos> mostrarProdutos() {
        List<Produtos> produtos = service.mostrarProdutos();
        return produtos;
    }

}
