package projeto.com.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import projeto.com.model.Endereco;
import projeto.com.model.Usuario;
import projeto.com.serviceImpl.EnderecoServiceImpl;

@Controller
public class EnderecoController {

    EnderecoServiceImpl service = new EnderecoServiceImpl();

    @RequestMapping(value = "/criarEndereco", method = RequestMethod.POST)
    public @ResponseBody
    Endereco criarEndereco(@RequestBody Endereco endereco) {
        Endereco enderecoCadastro = new Endereco();
        if (!service.verificaEnderecoCadastrado(endereco)) {
            enderecoCadastro = service.criarEndereco(endereco);
        }
        return enderecoCadastro;
    }

    @RequestMapping(value = "/exibirEnderecos", method = RequestMethod.GET)
    public @ResponseBody
    List<Endereco> exibirEnderecos(int usuario) {
        List<Endereco> enderecos = service.listaEnderecoPorUsuario(usuario);
        return enderecos;
    }

    @RequestMapping(value = "/alterarEndereco", method = RequestMethod.POST)
    public @ResponseBody
    Endereco atualizarEndereco(@RequestBody Endereco endereco) {
        service.atualizarEndereco(endereco);
        return endereco;
    }

    @RequestMapping(value = "/excluirEndereco", method = RequestMethod.POST)
    public @ResponseBody
    Endereco excluirEndereco(@RequestBody Endereco endereco) {
        service.deletarEndereco(endereco);
        return endereco;
    }

}
