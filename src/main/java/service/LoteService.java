package service;
import dao.*;

import spark.Request;
import spark.Response;
import org.json.simple.JSONObject;

import java.util.HashMap;

import java.util.Map;
import model.LoteModel;

public class LoteService {
    private LoteDAO loteDAO;

    public LoteService() {
        loteDAO = new LoteDAO();
    }

    public Object add (Request request, Response response) {
        int idLote = Integer.valueOf(request.queryParams("id_lote"));
        String descricao = request.queryParams("descricao");
        int estoqueIdEstoque = Integer.valueOf(request.queryParams("estoque_id_estoque"));
        int categoriaIdCategoria = Integer.valueOf(request.queryParams("categoria_id_categoria"));

        LoteModel loteModel = new LoteModel(idLote, descricao, estoqueIdEstoque, categoriaIdCategoria);

        if (loteDAO.createLote(loteModel)) {
            response.status(201);
        } else {
            response.status(500);
        }
        return descricao;
    }

    public Object get (Request request, Response response) {
        int idLote = Integer.valueOf(request.params(":id_lote"));
        LoteModel loteModel = (LoteModel) loteDAO.getIdLote(idLote);

        if (loteModel != null) {
            response.header("Content-Type", "application/json");
            response.header("Content-Encoding", "UTF-8");
            
            
            Map<String, Object> json = new HashMap<String, Object>();
            
            json.put("id_lote", loteModel.getIdLote());
            json.put("descricao", loteModel.getDescricao());
            json.put("estoque_id_estoque", loteModel.getEstoqueIdEstoque());
            json.put("categoria_id_categoria", loteModel.getCategoriaIdCategoria());

            return new JSONObject(json);
        } else {
            response.status(404); // 404 Not found
            return "Lote " + idLote + " nao encontrado.";
        }

    }

    public Object update (Request request, Response response) {
        int idLote = Integer.valueOf(request.params(":id_lote"));
		LoteModel loteModel = (LoteModel) loteDAO.getIdLote(idLote);

        if (loteModel != null) {
            

            loteModel.setIdLote(request.queryParams("id_lote").equals("") ? loteModel.getIdLote() : Integer.valueOf(request.queryParams("id_lote")));
            loteModel.setDescricao(request.queryParams("descricao").equals("") ? loteModel.getDescricao() : request.queryParams("descricao"));
            loteModel.setEstoqueIdEstoque(request.queryParams("estoque_id_estoque").equals("") ? loteModel.getEstoqueIdEstoque() : Integer.valueOf(request.queryParams("estoque_id_estoque")));
            loteModel.setCategoriaIdCategoria(request.queryParams("categoria_id_categoria").equals("") ? loteModel.getCategoriaIdCategoria() : Integer.valueOf(request.queryParams("categoria_id_categoria")));
            loteDAO.updateLote(loteModel);
        	
            return idLote;
        } else {
            response.status(404); // 404 Not found
            return "lote nao encontrado.";
        }

	}

    public Object delete (Request request, Response response) {
        int idLote = Integer.parseInt(request.params(":id_lote"));
        LoteModel lote = (LoteModel) loteDAO.getIdLote(idLote);

        if (lote != null) {
            loteDAO.deleteLote(idLote);

            response.status(200); // success
        	return idLote;
        } else {
            response.status(404); // 404 Not found
            return "lote nao encontrado.";
        }
	}

}
