package projeto.com.serviceImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import projeto.com.model.Usuario;
import projeto.com.service.UsuarioService;
import projeto.com.util.DBConnection;

public class UsuarioServiceImpl implements UsuarioService {

    @Override
    public boolean criarUsuario(Usuario usuario) {
        try {
            Connection con = DBConnection.getDb();
            String query = "{CALL usuario_criarUsuario(?,?,?,?)}";
            CallableStatement ptmt = con.prepareCall(query);

            ptmt.setString(1, usuario.getNome());
            ptmt.setString(2, usuario.getSobrenome());
            ptmt.setString(3, usuario.getEmail());
            ptmt.setString(4, usuario.getSenha());

            ptmt.execute();
            con.close();

            return true;
        } catch (SQLException sql) {
            sql.getErrorCode();
        } catch (ClassNotFoundException ex) {
            ex.getException();
        }
        return false;
    }

    @Override
    public Usuario loginUsuario(Usuario usuario) {
        try {
            Connection con = DBConnection.getDb();
            String query = "{CALL usuario_login(?,?)}";
            CallableStatement ptmt = con.prepareCall(query);

            ptmt.setString(1, usuario.getEmail());
            ptmt.setString(2, usuario.getSenha());

            ResultSet rs = ptmt.executeQuery();
            EnderecoServiceImpl enderecoPorUsuario = new EnderecoServiceImpl();
            PedidoServiceImpl pedidoPorUsuario = new PedidoServiceImpl();
            while (rs.next()) {

                usuario.setIdusuario(rs.getInt("idusuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSobrenome(rs.getString("sobrenome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setEndereco(enderecoPorUsuario.listaEnderecoPorUsuario(rs.getInt("idusuario")));
                usuario.setPedido(pedidoPorUsuario.exibirPedidosPorUsuario(rs.getInt("idusuario")));
                return usuario;
            }
            rs.close();
            ptmt.close();
        } catch (SQLException sql) {
            sql.getErrorCode();

        } catch (ClassNotFoundException ex) {
            ex.getException();
        }
        return null;
    }

    @Override
    public boolean atualizarUsuario(Usuario usuario) {
        try {
            Connection con = DBConnection.getDb();
            String query = "{CALL usuario_atualizarUsuario(?,?,?,?,?)}";
            CallableStatement ptmt = con.prepareCall(query);

            ptmt.setString(1, usuario.getNome());
            ptmt.setString(2, usuario.getSobrenome());
            ptmt.setString(3, usuario.getEmail());
            ptmt.setString(4, usuario.getSenha());
            ptmt.setInt(5, usuario.getIdusuario());

            int ret = ptmt.executeUpdate();
            con.close();

            return true;
        } catch (SQLException ex) {
            ex.getErrorCode();
        } catch (ClassNotFoundException error) {
            error.getException();
        }
        return false;
    }

    @Override
    public boolean deletarUsuario(Usuario usuario) {
        try {
            Connection con = DBConnection.getDb();
            String query = "{CALL usuario_deletarUsuario(?)}";
            CallableStatement ptmt = con.prepareCall(query);

            ptmt.setInt(1, usuario.getIdusuario());
            ptmt.execute();
            ptmt.close();

            return true;
        } catch (SQLException sql) {
            sql.getErrorCode();
        } catch (ClassNotFoundException ex) {
            ex.getException();
        }
        return false;
    }

    @Override
    public boolean verificaUsuarioCadastrado(Usuario usuario) {
        try {
            Connection con = DBConnection.getDb();
            String query = "{CALL usuario_verificaUsuarioCadastrado(?)}";
            CallableStatement ptmt = con.prepareCall(query);

            ptmt.setString(1, usuario.getEmail());

            ResultSet rs = ptmt.executeQuery();
            boolean check = false;

            while (rs.next()) {
                check = true;
                System.out.println("ESSE USUARIO JÁ ESTÁ CADASTRADO");
            }
            rs.close();
            ptmt.close();
            return check;

        } catch (ClassNotFoundException ex) {
            ex.getException();
            return false;
        } catch (SQLException sql) {
            sql.getErrorCode();
            return false;
        }
    }

}
