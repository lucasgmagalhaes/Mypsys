package service;
import dao.*;
import spark.Request;
import spark.Response;
import org.json.simple.JSONObject;
import java.util.HashMap;

import java.util.Map;
import model.CategoriaModel;

public class CategoriaService {
    private CategoriaDAO categoriaDAO;

    public CategoriaService() {
        categoriaDAO = new CategoriaDAO();
    }

    public Object add (Request request, Response response) {
        int id_categoria = Integer.valueOf(request.queryParams("id_categoria"));
        boolean perecivel = (request.queryParams("perecivel") == "0" ? false: true);
        String nome = request.queryParams("nome");

        CategoriaModel categoriaModel = new CategoriaModel(id_categoria, perecivel, nome);

        if (categoriaDAO.createCategoria(categoriaModel)) {
            response.status(201);
        } else {
            response.status(500);
        }
        return id_categoria;
    }

    public Object get (Request request, Response response) {
        int id_categoria = Integer.valueOf(request.params(":id_categoria"));
        CategoriaModel categoriaModel = (CategoriaModel) categoriaDAO.getCategoria(id_categoria);

        if (categoriaModel != null) {
            response.header("Content-Type", "application/json");
            response.header("Content-Encoding", "UTF-8");
            
           
            Map<String, Object> json = new HashMap<String, Object>();

            json.put("id_categoria", categoriaModel.getIdCategoria());
            json.put("perecivel", categoriaModel.getPerecivel());
            json.put("nome", categoriaModel.getNome());

            return new JSONObject(json);
        } else {
            response.status(404); // 404 Not found
            return "categoria " + id_categoria + " nao encontrada.";
        }
    }

    public Object update (Request request, Response response) {
        int id_categoria = Integer.valueOf(request.params(":id_categoria"));
		CategoriaModel categoriaModel = (CategoriaModel) categoriaDAO.getCategoria(id_categoria);

        if (categoriaModel != null) {
          

            categoriaModel.setIdCategoria(request.queryParams("id_categoria").equals("") ? categoriaModel.getIdCategoria() : Integer.valueOf(request.queryParams("id_categoria")));
            categoriaModel.setPerecivel(request.queryParams("perecivel").equals("") ? categoriaModel.getPerecivel() : (request.queryParams("perecivel") == "0" ? false: true));
            categoriaModel.setNome(request.queryParams("nome").equals("") ? categoriaModel.getNome() : request.queryParams("nome"));
            categoriaDAO.updateCategoria(categoriaModel);
        	
            return id_categoria;
        } else {
            response.status(404); // 404 Not found
            return "categoria nao encontrada.";
        }

	}

    public Object delete (Request request, Response response) {
        int id_categoria = Integer.parseInt(request.params(":id_categoria"));
        CategoriaModel categoriaModel = (CategoriaModel) categoriaDAO.getCategoria(id_categoria);

        if (categoriaModel != null) {
            categoriaDAO.deleteCategoria(id_categoria);

            response.status(200); // success
        	return id_categoria;
        } else {
            response.status(404); // 404 Not found
            return "categoria nao encontrada.";
        }
	}

}
