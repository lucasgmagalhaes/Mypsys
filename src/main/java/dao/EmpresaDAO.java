package dao;

import java.sql.*;


import model.EmpresaModel;
import util.ConexaoBd;

public class EmpresaDAO {
    protected Connection conn;

    public EmpresaDAO() {
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

    public boolean createEmpresa(EmpresaModel empresaModel) {
        connect();
        boolean status = false;

        try {
            String query = "INSERT INTO public.empresa "
                +"(cnpj,nome,usuario_email)"
                +" VALUES (ARRAY[?], ARRAY[?], ARRAY[?]);";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, empresaModel.getCnpj());
            st.setString(2, empresaModel.getNome());
            st.setString(3, empresaModel.getUsuario_email());
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public EmpresaModel getEmpresa(int cnpj){
        connect();
        EmpresaModel empresa = null;
        try{
            String query = "SELECT * FROM public.empresa WHERE cnpj = ARRAY[?];";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1,cnpj);
            
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                empresa = new EmpresaModel(cnpj,(rs.getString("nome")),
                            rs.getString("usuario_email"));
            }
            st.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return empresa;
    }

    public boolean updateEmpresa(EmpresaModel empresa) {
        connect();
        boolean status = false;

        try {
            String query = "UPDATE public.empresa"
            +" SET nome=ARRAY[?], usuario_email=ARRAY[?]"
            +" WHERE cnpj=ARRAY[?]";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(3, empresa.getCnpj());
            st.setString(1, empresa.getNome());
            st.setString(2, empresa.getUsuario_email());

            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public boolean deleteEmpresa(int cnpj){
        connect();
        boolean status = false;
		try {  
			String query = "DELETE FROM public.empresa WHERE cnpj = ARRAY[?];";
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