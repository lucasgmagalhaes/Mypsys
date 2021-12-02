package service;


import model.ItemModel;
import spark.Request;
import spark.Response;
import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import dao.ItemDAO;

public class ItemService {

    private ItemDAO itemDAO;

    public ItemService() {
        itemDAO = new ItemDAO();
        itemDAO.connect();
    }

    public Object add(Request request, Response response) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        //int idItem =  Integer.valueOf(request.queryParams("idItem"));
        String dataDeAquisicao = request.queryParams("data_de_aquisicao");
        LocalDate dataDeAquisicaoFormatado = LocalDate.parse(dataDeAquisicao, dtf);
        
        String dataDeValidade = request.queryParams("data_de_validade");
        LocalDate dataDeValidadeFormatado = LocalDate.parse(dataDeValidade, dtf);

        double valorVenda = Double.valueOf(request.queryParams("valor_venda"));
        int quantidade = Integer.valueOf(request.queryParams("quantidade"));
        int lote_idLote = Integer.valueOf(request.queryParams("lote_id_lote"));
        int fornecedor_cnpj = Integer.valueOf(request.queryParams("fornecedor_cnpj"));
        double valorCompra = Double.valueOf(request.queryParams("valor_compra"));
      
        int idItem = itemDAO.getMaxId();
        ItemModel itemModel = new ItemModel(idItem,dataDeAquisicaoFormatado,dataDeValidadeFormatado,valorVenda,quantidade,lote_idLote, fornecedor_cnpj, valorCompra);

        if (itemDAO.createItem(itemModel)) {
        response.status(201);
        } else {
            response.status(500);
        }
        return idItem;
    }

    public Object get(Request request, Response response) {
        int idItem = Integer.valueOf(request.params(":id_item"));

        ItemModel itemModel = (ItemModel) itemDAO.getItem(idItem);

        if (itemModel != null) {
            response.header("Content-Type", "application/json");
            response.header("Content-Encoding", "UTF-8");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            Map<String, Object> json = new HashMap<String, Object>();

            json.put("id_item",itemModel.getIdItem());
            json.put("data_de_aquisicao",(itemModel.getDataDeAquisicao()).format(formatter));
            json.put("data_de_validade",(itemModel.getDataDeValidade()).format(formatter));
            json.put("valor_venda",itemModel.getValorVenda());
            json.put("quantidade",itemModel.getQuantidade());
            json.put("lote_id_lote",itemModel.getLote_idLote());
            json.put("fornecedor_cnpj",itemModel.getFornecedor_cnpj());
            json.put("valor_compra",itemModel.getValorCompra());
            
            return new JSONObject(json);
        } else {
            response.status(404); // 404 Not found
            return "item " +idItem + " nao encontrado.";
        }

    }

    public Object update(Request request, Response response) {
        int idItem = Integer.valueOf(request.params(":id_item"));
		ItemModel itemModel = (ItemModel) itemDAO.getItem(idItem);

        if (itemModel != null) {
            String strDataAquisicao = request.queryParams("data_de_aquisicao");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dataAquisicao = LocalDate.parse(strDataAquisicao, dtf);

            String strDataValidade = request.queryParams("data_de_validade");
            LocalDate dataValidade = LocalDate.parse(strDataValidade, dtf);

            itemModel.setDataDeAquisicao(strDataAquisicao.equals("") ? itemModel.getDataDeAquisicao() : dataAquisicao);
            itemModel.setDataDeAquisicao(strDataValidade.equals("") ? itemModel.getDataDeAquisicao() : dataValidade);
            //itemModel.setNome(request.queryParams("nome").equals("") ? itemModel.getNome() : request.queryParams("nome"));
            itemModel.setValorCompra(request.queryParams("valor_compra").equals("") ? itemModel.getValorCompra() : Double.valueOf(request.queryParams("valor_compra")));
            itemModel.setValorVenda(request.queryParams("valor_venda").equals("") ? itemModel.getValorVenda() : Double.valueOf(request.queryParams("valor_venda")));
            itemModel.setQuantidade(request.queryParams("quantidade").equals("") ? itemModel.getQuantidade() : Integer.valueOf(request.queryParams("quantidade")));
            itemModel.setLote_idLote(request.queryParams("lote_id_lote").equals("") ? itemModel.getQuantidade() : Integer.valueOf(request.queryParams("lote_id_lote")));
            itemModel.setFornecedor_cnpj(request.queryParams("fornecedor_cnpj").equals("") ? itemModel.getQuantidade() : Integer.valueOf(request.queryParams("fornecedor_cnpj")));
        	
            itemDAO.updateItem(itemModel);
        	
            return idItem;
        } else {
            response.status(404); // 404 Not found
            return "item nao encontrado.";
        }

	}

    public Object delete(Request request, Response response) {
        int idItem = Integer.valueOf(request.params(":id_item"));
        ItemModel itemModel = (ItemModel) itemDAO.getItem(idItem);

        if (itemModel != null) {

            itemDAO.deleteItem(idItem);

            response.status(200); // success
        	return idItem;
        } else {
            response.status(404); // 404 Not found
            return "item nao encontrado.";
        }
	}
}