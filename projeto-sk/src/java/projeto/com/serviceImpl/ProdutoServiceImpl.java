package projeto.com.serviceImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import projeto.com.model.Produtos;
import projeto.com.service.ProdutoService;
import projeto.com.util.DBConnection;

public class ProdutoServiceImpl implements ProdutoService {

    @Override
    public List<Produtos> mostrarProdutos() {
        try {
            Connection con = DBConnection.getDb();
            String query = "{CALL produtos_mostrarProdutos}";
            CallableStatement ptmt = con.prepareCall(query);
            List<Produtos> produtos = new ArrayList<Produtos>();

            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                Produtos produto = new Produtos();
                produto.setIdProdutos(rs.getInt("idprodutos"));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getString("preco"));
               
                produtos.add(produto);
            }
            rs.close();
            ptmt.close();
            return produtos;
        }catch (SQLException sql) {
            sql.getErrorCode();
        } catch (ClassNotFoundException ex) {
            ex.getException();
        }
        return null;

    }

}
