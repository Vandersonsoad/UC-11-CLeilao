import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {
    
    public Connection connectDB() {
        Connection conn = null;
        
        try {
            // Tenta carregar o driver JDBC do MySQL
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                Class.forName("com.mysql.jdbc.Driver");
            }
            
            // Conexão apontando para o banco uc11 na porta 3306
            String url = "jdbc:mysql://127.0.0.1:3306/uc11?useSSL=false&allowPublicKeyRetrieval=true";
            String usuario = "root";
            String senha = "1234"; // Senha informada
            
            conn = DriverManager.getConnection(url, usuario, senha);
            
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, 
                "ERRO DE DRIVER: O Driver JDBC não foi encontrado.\n" + e.getMessage());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "ERRO DE CONEXÃO MYSQL: " + e.getMessage());
        }
        
        return conn;
    }
}