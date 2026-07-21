import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    // 1. Método que GRAVA no banco de dados
    public boolean cadastrarProduto(ProdutosDTO produto) {
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        
        try {
            conn = new conectaDAO().connectDB();
            
            if (conn == null) {
                JOptionPane.showMessageDialog(null, "Conexão com o banco retornou NULA!");
                return false;
            }
            
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            
            int linhasAfetadas = prep.executeUpdate(); // Usar executeUpdate para INSERT/UPDATE/DELETE
            
            // Fecha as conexões para confirmar a gravação
            prep.close();
            conn.close();
            
            return linhasAfetadas > 0;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto no banco: " + e.getMessage());
            return false;
        }
    }
    
    // 2. Método que BUSCA os dados para a listagem
    public ArrayList<ProdutosDTO> listarProdutos() {
        String sql = "SELECT * FROM produtos";
        
        try {
            conn = new conectaDAO().connectDB();
            
            if (conn == null) {
                JOptionPane.showMessageDialog(null, "Conexão com o banco retornou NULA na listagem!");
                return listagem;
            }
            
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            
            listagem.clear();
            
            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                
                listagem.add(produto);
            }
            
            // Fecha as conexões
            resultset.close();
            prep.close();
            conn.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos no banco: " + e.getMessage());
        }
        
        return listagem;
    }
}