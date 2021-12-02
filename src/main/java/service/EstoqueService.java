package service;
import dao.*;
import model.*;
import spark.Request;
import spark.Response;
import org.json.simple.JSONObject;



import java.util.HashMap;

import java.util.Map;

public class EstoqueService {
    private EstoqueDAO estoqueDAO;

    public EstoqueService() {
        estoqueDAO = new EstoqueDAO();
    }

    public Object add(Request request, Response response) {
        int id_estoque = Integer.valueOf(request.queryParams("id_estoque"));
        double credito = Double.valueOf(request.queryParams("credito"));
        int empresa_cnpj = Integer.valueOf(request.queryParams("empresa_cnpj"));

        EstoqueModel estoque = new EstoqueModel(id_estoque, credito, empresa_cnpj);

        if (estoqueDAO.createEstoque(estoque)) {
            response.status(201);
        } else {
            response.status(500);
        }
        return credito;
    }

    public Object get(Request request, Response response) {
        int id_estoque = Integer.valueOf(request.params(":id_estoque"));

        EstoqueModel estoque = (EstoqueModel) estoqueDAO.getEstoque(id_estoque);

        if (estoque != null) {
            response.header("Content-Type", "application/json");
            response.header("Content-Encoding", "UTF-8");
            
           
            Map<String, Object> json = new HashMap<String, Object>();
            json.put("id_estoque", estoque.getIdEstoque());
            json.put("credito", estoque.getCredito());
            json.put("empresa_cnpj", estoque.getEmpresa_cnpj());

            return new JSONObject(json);
        } else {
            response.status(404); // 404 Not found
            return "Estoque " + id_estoque + " nao encontrado.";
        }

    }

    public Object update(Request request, Response response) {
        int id_estoque = Integer.valueOf(request.params(":id_estoque"));
		EstoqueModel estoque = (EstoqueModel) estoqueDAO.getEstoque(id_estoque);

        if (estoque != null) {
        

            estoque.setCredito(request.queryParams("credito").equals("") ? estoque.getCredito() : Double.valueOf(request.queryParams("credito")));
            estoque.setEmpresa_cnpj(request.queryParams("empresa_cnpj").equals("") ? estoque.getEmpresa_cnpj() : Integer.valueOf(request.queryParams("id_estoque")));
            estoqueDAO.updateEstoque(estoque);
        	
            return id_estoque;
        } else {
            response.status(404); // 404 Not found
            return "estoque nao encontrado.";
        }

	}

    public Object delete(Request request, Response response) {
        int id_estoque = Integer.parseInt(request.params(":id_estoque"));

        EstoqueModel estoque = (EstoqueModel) estoqueDAO.getEstoque(id_estoque);

        if (estoque != null) {

            estoqueDAO.deleteEstoque(id_estoque);

            response.status(200); // success
        	return id_estoque;
        } else {
            response.status(404); // 404 Not found
            return "estoque nao encontrado.";
        }
	}
}
