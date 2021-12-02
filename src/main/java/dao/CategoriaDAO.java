package dao;
import java.sql.*;


import model.CategoriaModel;
import util.ConexaoBd;

public class CategoriaDAO {
    protected Connection conn;

    public CategoriaDAO() {
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

    public boolean createCategoria(CategoriaModel categoriaModel) {
        connect();
        boolean status = false;

        try {
            String query = "INSERT INTO public.categoria "
                +"(perecivel,nome)"
                +" VALUES (?, ARRAY[?]);";
            PreparedStatement st = conn.prepareStatement(query);
            //st.setInt(1, categoriaModel.getIdCategoria());
            st.setBoolean(1, categoriaModel.getPerecivel());
            st.setString(2, categoriaModel.getNome());
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public CategoriaModel getCategoria(int id_categoria){
        connect();
        CategoriaModel categoriaModel = null;
        try{
            String query = "SELECT * FROM public.mpsys WHERE id_categoria = ?;";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1,id_categoria);
            
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                categoriaModel = new CategoriaModel(id_categoria,(rs.getBoolean("perecivel")),(rs.getString("nome")));
            }
            st.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return categoriaModel;
    }

    public boolean updateCategoria(CategoriaModel categoriaModel) {
        connect();
        boolean status = false;

        try {
            String query = "UPDATE public.categoria"
            +" SET nome=ARRAY[?], perecivel=?"
            +" WHERE id_categoria=?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, categoriaModel.getNome());
            st.setBoolean(2, categoriaModel.getPerecivel());
            st.setInt(3, categoriaModel.getIdCategoria());

            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public boolean deleteCategoria(int id_categoria){
        connect();
        boolean status = false;
		try {  
			String query = "DELETE FROM public.categoria WHERE id_categoria = ?;";
			PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, id_categoria);
            st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
    }
    
}
