package dao;
import java.sql.*;
import model.LoteModel;
import util.ConexaoBd;

public class LoteDAO {
    protected Connection conn;

    public LoteDAO() {
        conn = null;
    }

    //CONECTAR LOTE PELO ID -----------------------------------------------------------------------------------------------------------------------------------
    protected boolean connect() {
        boolean status = false;

        if (conn == null) {
            try {
                conn = ConexaoBd.getConnection();
                status = true;
            } catch (Exception e) {
                System.out.println("Algo inesperado aconteceu.");
            }
        } else { status = true; }

        return status;
    }

    //DESCONECTAR BD ------------------------------------------------------------------------------------------------------------------------------------------
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

    //CRIAR LOTE ----------------------------------------------------------------------------------------------------------------------------------------------
    public boolean createLote(LoteModel loteModel) {
        connect();
        boolean status = false;

        try {
            String query = "INSERT INTO public.lote "
                +"(descricao,estoque_id_estoque,categoria_id_categoria)"
                +" VALUES (ARRAY[?], ?, ?)";
            PreparedStatement st = conn.prepareStatement(query);
            //st.setInt(1, loteModel.getIdLote());
            st.setString(1, loteModel.getDescricao());
            st.setInt(2, loteModel.getEstoqueIdEstoque());
            st.setInt(3, loteModel.getCategoriaIdCategoria());
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    //CONSULTAR LOTE PELO ID ----------------------------------------------------------------------------------------------------------------------------------
    public LoteModel getIdLote(int idLote){
        connect();
        LoteModel lote = null;

        try{
            String query = "SELECT * FROM public.lote WHERE id_lote = ?;";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1,idLote);
            
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                lote = new LoteModel(idLote,rs.getString("descricao"),rs.getInt("estoque_id_estoque"),rs.getInt("categoria_id_categoria"));
            }
            st.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return lote;
    }

    //ATUALIZAR LOTE PELO ID ----------------------------------------------------------------------------------------------------------------------------------
    public boolean updateLote(LoteModel loteModel) {
        connect();
        boolean status = false;

        try {
            String query = "UPDATE public.lote"
            +" SET descricao=ARRAY[?], estoque_id_estoque=?, categoria_id_categoria=?"
            +" WHERE id_lote=?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, loteModel.getDescricao());
            st.setInt(2, loteModel.getEstoqueIdEstoque());
            st.setInt(3, loteModel.getCategoriaIdCategoria());
            st.setInt(4, loteModel.getIdLote());

            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }


    //DELETAR LOTE PELO ID ------------------------------------------------------------------------------------------------------------------------------------
    public boolean deleteLote(int idLote){
        connect();
        boolean status = false;

		try {  
			String query = "DELETE FROM public.lote WHERE id_lote = ?;";
			PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, idLote);
            st.executeUpdate();
			st.close();

			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}

		return status;
    }
}
