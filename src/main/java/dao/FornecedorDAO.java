package dao;
import java.sql.*;


import model.FornecedorModel;
import util.ConexaoBd;

public class FornecedorDAO {
    protected Connection conn;

    public FornecedorDAO() {
        conn = null;
    }

    //CONECTAR FORNECEDOR PELO ID -----------------------------------------------------------------------------------------------------------------------------------
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
            System.out.println("Algo inesperado aconteceu.");
        }

        return status;
    }

    //CRIAR FORNECEDOR ----------------------------------------------------------------------------------------------------------------------------------------------
    public boolean createFornecedor(FornecedorModel fornecedorModel) {
        connect();
        boolean status = false;

        try {
            String query = "INSERT INTO public.fornecedor "
                +"(cnpj,nome)"
                +" VALUES (ARRAY[?], ARRAY[?]);";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, fornecedorModel.getCnpj());
            st.setString(2, fornecedorModel.getNome());
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    //CONSULTAR FORNECEDOR PELO ID ----------------------------------------------------------------------------------------------------------------------------------
    public FornecedorModel getFornecedor(int cnpj){
        connect();
        FornecedorModel forncedorModel = null;

        try{
            String query = "SELECT * FROM public.fornecedor WHERE cnpj = ARRAY[?];";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1,cnpj);
            
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                forncedorModel = new FornecedorModel(cnpj,rs.getString("nome"));
            }
            st.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return forncedorModel;
    }

    //ATUALIZAR FORNECEDOR PELO ID ----------------------------------------------------------------------------------------------------------------------------------
    public boolean updateFornecedor(FornecedorModel cnpj) {
        connect();
        boolean status = false;

        try {
            String query = "UPDATE public.fornecedor"
            +" SET nome=ARRAY[?]"
            +" WHERE cnpj=ARRAY[?]";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, cnpj.getNome());
            st.setInt(2, cnpj.getCnpj());

            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }


    //DELETAR FORNECEDOR PELO ID ------------------------------------------------------------------------------------------------------------------------------------
    public boolean deleteFornecedor(int cnpj){
        connect();
        boolean status = false;

		try {  
			String query = "DELETE FROM public.fornecedor WHERE cnpj = ARRAY[?];";
			PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, cnpj);
            st.executeUpdate();
			st.close();

			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}

		return status;
    }
}
