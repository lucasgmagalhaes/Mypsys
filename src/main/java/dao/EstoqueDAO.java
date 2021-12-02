package dao;
import java.sql.*;


import model.EstoqueModel;
import util.ConexaoBd;

public class EstoqueDAO {
    protected Connection conn;

    public EstoqueDAO() {
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

    public boolean createEstoque(EstoqueModel estoqueModel) {
        connect();
        boolean status = false;

        try {   
            String query = "INSERT INTO public.estoque "
                +"(credito,empresa_cnpj)"
                +" VALUES (?, ARRAY[?]);";
            PreparedStatement st = conn.prepareStatement(query);
            //st.setInt(1, estoqueModel.getIdEstoque());
            st.setDouble(1, estoqueModel.getCredito());
            st.setInt(2, estoqueModel.getEmpresa_cnpj());
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public EstoqueModel getEstoque(int id_estoque){
        connect();
        EstoqueModel estoque = null;
        try{
            String query = "SELECT * FROM public.estoque WHERE id_estoque = ?;";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1,id_estoque);
            
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                estoque = new EstoqueModel(id_estoque,(rs.getDouble("credito")),
                            rs.getInt("empresa_cnpj"));
            }
            st.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return estoque;
    }

    public boolean updateEstoque(EstoqueModel estoque) {
        connect();
        boolean status = false;

        try {
            String query = "UPDATE public.estoque"
            +" SET credito=?, empresa_cnpj=ARRAY[?]"
            +" WHERE id_estoque=?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(3, estoque.getIdEstoque());
            st.setDouble(1, estoque.getCredito());
            st.setInt(2, estoque.getEmpresa_cnpj());

            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public boolean deleteEstoque(int id_estoque){
        connect();
        boolean status = false;
		try {  
			String query = "DELETE FROM public.estoque WHERE id_estoque = ?;";
			PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, id_estoque);
            st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
    }
}
