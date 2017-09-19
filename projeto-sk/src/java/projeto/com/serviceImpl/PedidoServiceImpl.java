package projeto.com.serviceImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import projeto.com.model.Pedidos;
import projeto.com.service.PedidoService;
import projeto.com.util.DBConnection;

public class PedidoServiceImpl implements PedidoService {

    @Override
    public boolean efetuarPedido(Pedidos pedido) {
        try {
            Connection con = DBConnection.getDb();
            String query = "{CALL pedidos_efetuarPedidos(?,?)}";
            CallableStatement ptmt = con.prepareCall(query);

            ptmt.setInt(1, pedido.getUsuario().getIdusuario());
            ptmt.setInt(2, pedido.getProduto().getIdProdutos());

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
    public boolean deltarPedido(Pedidos pedido) {
        try {
            Connection con = DBConnection.getDb();
            String query = "{CALL pedido_deletarPedido(?)}";
            CallableStatement ptmt = con.prepareCall(query);

            ptmt.setInt(1, pedido.getIdPedidos());
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
    public List<Pedidos> exibirPedidosPorUsuario(int usuario) {
        try {
            Connection con = DBConnection.getDb();
            String query = "{CALL pedidos_exibirPedidosPorUsuario(?)}";
            CallableStatement ptmt = con.prepareCall(query);
            List<Pedidos> pedidos = new ArrayList<Pedidos>();

            ptmt.setInt(1, usuario);

            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                Pedidos pedido = new Pedidos();

                pedido.setIdPedidos(rs.getInt("idpedidos"));
                pedido.getProduto().setIdProdutos(rs.getInt("idprodutos"));
                pedido.getProduto().setNome(rs.getString("nome"));
                pedido.getProduto().setDescricao(rs.getString("descricao"));
                pedido.getProduto().setPreco(rs.getString("preco"));
                pedido.getUsuario().setIdusuario(rs.getInt("idusuario"));

                pedidos.add(pedido);
            }
            rs.close();
            ptmt.close();
            return pedidos;
        } catch (SQLException sql) {
            sql.getErrorCode();
        } catch (ClassNotFoundException ex) {
            ex.getException();
        }
        return null;
    }

    @Override
    public boolean atualizarPedido(Pedidos pedido) {
        try {
            Connection con = DBConnection.getDb();
            String query = "{CALL pedidos_atualizarPedidos(?,?)}";
            CallableStatement ptmt = con.prepareCall(query);

            ptmt.setInt(1, pedido.getEndereco().getIdEndereco());
            ptmt.setInt(2, pedido.getIdPedidos());
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
    public boolean verificaPedidoCadastrado(Pedidos pedido) {
        try {
            Connection con = DBConnection.getDb();
            String query = "{CALL pedidos_verificaPedidoCadastrado(?,?)}";
            CallableStatement ptmt = con.prepareCall(query);

            ptmt.setInt(1, pedido.getProduto().getIdProdutos());
            ptmt.setInt(2, pedido.getUsuario().getIdusuario());
            ResultSet rs = ptmt.executeQuery();
            boolean check = false;

            while (rs.next()) {
                check = true;
                System.out.println("ESSE PEDIDO JÁ FOI EFETUADO");
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
