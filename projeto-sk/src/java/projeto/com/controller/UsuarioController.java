package projeto.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import projeto.com.model.Usuario;
import projeto.com.serviceImpl.UsuarioServiceImpl;

@Controller
public class UsuarioController {

    UsuarioServiceImpl service = new UsuarioServiceImpl();

    @RequestMapping(value = "/criarUsuario", method = RequestMethod.POST)
    public @ResponseBody
    Usuario criarUsuario(@RequestBody Usuario usuario) {
        if (!service.verificaUsuarioCadastrado(usuario)) {
            service.criarUsuario(usuario);
        }
        return usuario;
    }

    @RequestMapping(value = "/loginUsuario", method = RequestMethod.POST)
    public @ResponseBody
    Usuario loginUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioLogado = service.loginUsuario(usuario);
        if (usuarioLogado != null) {
            System.out.println("LOGADO");
        } else {
            System.out.println("HOUVE UM PROBLEMA");
        }
        return usuario;
    }

    @RequestMapping(value = "/alterarUsuario", method = RequestMethod.POST)
    public @ResponseBody
    Usuario atualizarUsuario(@RequestBody Usuario usuario) {
        service.atualizarUsuario(usuario);
        return usuario;
    }

    @RequestMapping(value = "/excluirUsuario", method = RequestMethod.POST)
    public @ResponseBody
    Usuario deletarUsuario(@RequestBody Usuario usuario) {
        service.deletarUsuario(usuario);
        return usuario;
    }
}
