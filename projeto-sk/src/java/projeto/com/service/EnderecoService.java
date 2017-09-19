package projeto.com.service;

import java.util.List;
import projeto.com.model.Endereco;
import projeto.com.model.Usuario;

public interface EnderecoService {
    public Endereco criarEndereco (Endereco endereco);
    public boolean atualizarEndereco(Endereco endereco);
    public boolean deletarEndereco(Endereco endereco);
    public List<Endereco> listaEnderecoPorUsuario(int usuario);
    public boolean verificaEnderecoCadastrado(Endereco endereco);
}
