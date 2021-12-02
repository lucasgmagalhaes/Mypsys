package dao;
import java.sql.*;


import model.UsuarioModel;
import util.ConexaoBd;

public class UsuarioDAO {
    protected Connection conn;

    public UsuarioDAO() {
        conn = null;
    }

    protected boolean connect() {
        boolean status = false;
        if (conn == null) {
            try {
                conn = ConexaoBd.getConnection();
                status = true;
            } catch (Exception e) {
                System.out.println("Algo inesperado aconteceu");
            }
        } else {
            status = true;
        }
        return status;
    }

    public boolean disconect() {
        boolean status = false;
        try {
            ConexaoBd.closeConnection();
            status = true;
        } catch (Exception e) {
            System.out.println("Algo inesperado aconteceu");
        }
        return status;
    }
    public boolean createUsuario(UsuarioModel usuarioModel) {
        connect();
        boolean status = false;

        try {
            String query = "INSERT INTO public.usuario "
                +"(email,senha,nome)"
                +" VALUES (ARRAY[?], ARRAY[?], ARRAY[?]);";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, usuarioModel.getEmail());
            st.setString(2, usuarioModel.getSenha());
            st.setString(3, usuarioModel.getNome());
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }
    
    public UsuarioModel getUsuario(String email){
        connect();
        UsuarioModel usuarioModel = null;
        try{
            String query = "SELECT * FROM public.usuario WHERE email = ARRAY[?];";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1,email);
            
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                usuarioModel = new UsuarioModel(email,(rs.getString("senha")),(rs.getString("nome")));
            }
            st.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return usuarioModel;
    }

    public boolean updateUsuario(UsuarioModel usuarioModel) {
        connect();
        boolean status = false;

        try {
            String query = "UPDATE public.usuario"
            +" SET senha=ARRAY[?], nome=ARRAY[?]"
            +" WHERE email=ARRAY[?]";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, usuarioModel.getNome());
            st.setString(2, usuarioModel.getSenha());
            st.setString(3, usuarioModel.getEmail());

            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public boolean deleteUsuario(String email){
        connect();
        boolean status = false;
		try {  
			String query = "DELETE FROM public.usuario WHERE email = ARRAY[?];";
			PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, email);
            st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
    }

}