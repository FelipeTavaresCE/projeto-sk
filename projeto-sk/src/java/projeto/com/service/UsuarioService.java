package projeto.com.service;

import projeto.com.model.Usuario;

public interface UsuarioService {
    
    public boolean criarUsuario (Usuario usuario);
    public Usuario loginUsuario(Usuario usuario);
    public boolean atualizarUsuario(Usuario usuario);
    public boolean deletarUsuario(Usuario usuario);
    public boolean verificaUsuarioCadastrado(Usuario usuario);
    
}
