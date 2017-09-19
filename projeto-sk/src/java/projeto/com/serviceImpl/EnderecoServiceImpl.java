package projeto.com.serviceImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import projeto.com.model.Endereco;
import projeto.com.model.Usuario;
import projeto.com.service.EnderecoService;
import projeto.com.util.DBConnection;

public class EnderecoServiceImpl implements EnderecoService {

    @Override
    public Endereco criarEndereco(Endereco endereco) {
        try {
            Connection con = DBConnection.getDb();
            String query = "{CALL endereco_criarEndereco(?,?,?,?,?,?,?,?,?)}";
            CallableStatement ptmt = con.prepareCall(query);

            ptmt.setString(1, endereco.getLogradouro());
            ptmt.setInt(2, endereco.getNumero());
            ptmt.setString(3, endereco.getComplemento());
            ptmt.setString(4, endereco.getCep());
            ptmt.setString(5, endereco.getBairro());
            ptmt.setString(6, endereco.getCidade());
            ptmt.setString(7, endereco.getEstado());
            ptmt.setInt(8, endereco.getUsuario().getIdusuario());
            ptmt.registerOutParameter(9, java.sql.Types.INTEGER);

            ptmt.execute();
            int returnValue = ptmt.getInt(9);

            con.close();
            endereco.setIdEndereco(returnValue);
            return endereco;

        } catch (SQLException sql) {
            sql.getErrorCode();
        } catch (ClassNotFoundException ex) {
            ex.getException();
        }
        return endereco;
    }

    @Override
    public boolean atualizarEndereco(Endereco endereco) {
        try {
            Connection con = DBConnection.getDb();
            String query = "{CALL endereco_atualizarEndereco(?,?,?,?,?,?,?,?)}";
            CallableStatement ptmt = con.prepareCall(query);

            ptmt.setString(1, endereco.getLogradouro());
            ptmt.setInt(2, endereco.getNumero());
            ptmt.setString(3, endereco.getComplemento());
            ptmt.setString(4, endereco.getCep());
            ptmt.setString(5, endereco.getBairro());
            ptmt.setString(6, endereco.getCidade());
            ptmt.setString(7, endereco.getEstado());
            ptmt.setInt(8, endereco.getIdEndereco());
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
    public boolean deletarEndereco(Endereco endereco) {
        try {
            Connection con = DBConnection.getDb();
            String query = "{CALL endereco_deletarEndereco(?)}";
            CallableStatement ptmt = con.prepareCall(query);

            ptmt.setInt(1, endereco.getIdEndereco());
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
    public List<Endereco> listaEnderecoPorUsuario(int usuario) {
        try {
            Connection con = DBConnection.getDb();
            String query = "{CALL endereco_listaEnderecoPorUsuario(?)}";
            CallableStatement ptmt = con.prepareCall(query);
            List<Endereco> enderecos = new ArrayList<Endereco>();

            ptmt.setInt(1, usuario);

            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                Endereco endereco = new Endereco();

                endereco.setIdEndereco(rs.getInt("idendereco"));
                endereco.setLogradouro(rs.getString("logradouro"));
                endereco.setNumero(rs.getInt("numero"));
                endereco.setComplemento(rs.getString("complemento"));
                endereco.setCep(rs.getString("cep"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setEstado(rs.getString("estado"));
                endereco.getUsuario().setIdusuario(rs.getInt("usuario"));
                enderecos.add(endereco);
            }
            rs.close();
            ptmt.close();
            return enderecos;
        } catch (SQLException sql) {
            sql.getErrorCode();
        } catch (ClassNotFoundException ex) {
            ex.getException();
        }
        return null;
    }

    @Override
    public boolean verificaEnderecoCadastrado(Endereco endereco) {
        try {
            Connection con = DBConnection.getDb();
            String query = "{CALL endereco_verificaEnderecoCadastrado(?,?)}";
            CallableStatement ptmt = con.prepareCall(query);

            ptmt.setString(1, endereco.getLogradouro());
            ptmt.setInt(2, endereco.getNumero());

            ResultSet rs = ptmt.executeQuery();
            boolean check = false;

            while (rs.next()) {
                check = true;
                System.out.println("ESSE ENDEREÇO JÁ ESTÁ CADASTRADO");
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
