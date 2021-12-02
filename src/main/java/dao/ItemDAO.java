package dao;
import java.sql.*;
import model.ItemModel;
import util.ConexaoBd;


public class ItemDAO {
    protected Connection conn;
    public ItemDAO(){
        conn = null;
    }
    public boolean connect() {
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

    public boolean createItem(ItemModel itemModel) {
        connect();
        boolean status = false;

        try {
            String query = "INSERT INTO public.item "
                +"(data_de_aquisicao,data_de_validade,valor_venda,quantidade,lote_id_lote,fornecedor_cnpj,valor_compra)"
                +" VALUES (?, ?, ?, ?, ?, ARRAY[?], ?);";
            PreparedStatement st = conn.prepareStatement(query);
            //st.setInt(1, itemModel.getIdItem());
            st.setDate(1, Date.valueOf(itemModel.getDataDeAquisicao()));
            st.setDate(2, Date.valueOf(itemModel.getDataDeValidade()));
            st.setDouble(3, itemModel.getValorVenda());
            st.setInt(4, itemModel.getQuantidade());
            st.setInt(5, itemModel.getLote_idLote());
            st.setInt(6, itemModel.getFornecedor_cnpj());
            st.setDouble(7, itemModel.getValorCompra());

            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public ItemModel getItem(int idItem){
        connect();
        ItemModel itemModel = null;
        try{
            String query = "SELECT * FROM public.item WHERE id_item = ?;";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, idItem);
            
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                itemModel = new ItemModel(idItem,((Date) rs.getObject("data_de_aquisicao")).toLocalDate(),
                            ((Date) rs.getObject("data_de_validade")).toLocalDate(),rs.getDouble("valor_venda"), 
                            rs.getInt("quantidade"), rs.getInt("lote_id_lote"), rs.getInt("fornecedor_cnpj"),
                             rs.getDouble("valor_compra"));
            }
            st.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return itemModel;
    }

    public boolean updateItem(ItemModel itemModel) {
        connect();
        boolean status = false;

        try {
            String query = "UPDATE public.item"
            +" SET data_de_aquisicao=?, data_de_validade=?, valor_venda=?, quantidade=?,"
            +" lote_id_lote=?, fornecedor_cnpj=ARRAY[?], valor_compra=?"
            +" WHERE id_item=?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(8, itemModel.getIdItem());
            st.setDate(1, Date.valueOf(itemModel.getDataDeAquisicao()));
            st.setDate(2, Date.valueOf(itemModel.getDataDeValidade()));
            st.setDouble(3, itemModel.getValorVenda());
            st.setInt(4, itemModel.getQuantidade());
            st.setInt(5, itemModel.getLote_idLote());
            st.setInt(6, itemModel.getFornecedor_cnpj());
            st.setDouble(7, itemModel.getValorCompra());
            
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public boolean deleteItem(int idItem){
        connect();
        boolean status = false;
		try {  
			String query = "DELETE FROM public.item WHERE id_item = ?;";
			PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, idItem);
            st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
    }

     public int getMaxId() {
        int id = 0;
       
        try {
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);;
            ResultSet rs = st.executeQuery("select max id_item as max_id from item");
            rs.next();
            id = rs.getInt("max_id");
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
       
        return id;
    }

    public ItemModel[] getAll() {
        ItemModel[] itens = null;

        try {
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM item ORDER BY id_item");        
             if(rs.next()){
                 rs.last();
                 itens = new ItemModel[rs.getRow()];
                 rs.beforeFirst();

                 for(int i = 0; rs.next(); i++) {
                    itens[i] = new ItemModel(rs.getInt("id_item"),((Date) rs.getObject("data_de_aquisicao")).toLocalDate(),
                            ((Date) rs.getObject("data_de_validade")).toLocalDate(),rs.getDouble("valor_venda"), 
                            rs.getInt("quantidade"), rs.getInt("lote_id_lote"), rs.getInt("fornecedor_cnpj"),
                             rs.getDouble("valor_compra"));
                 }
             } 
              st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return itens;
    }    

}
