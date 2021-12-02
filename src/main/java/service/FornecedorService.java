package service;
import dao.*;
import model.*;
import spark.Request;
import spark.Response;
import org.json.simple.JSONObject;



import java.util.HashMap;
import java.util.Map;

public class FornecedorService {
    private FornecedorDAO fornecedorDAO;

    public FornecedorService() {
        fornecedorDAO = new FornecedorDAO();
    }

    public Object add(Request request, Response response) {
        int cnpj = Integer.valueOf(request.queryParams("cnpj"));
        String nome = request.queryParams("nome");
        FornecedorModel fornecedorModel = new FornecedorModel(cnpj,nome);

        if (fornecedorDAO.createFornecedor(fornecedorModel)) {
            response.status(201);
        } else {
            response.status(500);
        }
        return cnpj;
    }

    public Object get(Request request, Response response) {
        int cnpj = Integer.valueOf((request.params(":cnpj")));

        FornecedorModel fornecedorModel = (FornecedorModel) fornecedorDAO.getFornecedor(cnpj);

        if (fornecedorModel != null) {
            response.header("Content-Type", "application/json");
            response.header("Content-Encoding", "UTF-8");
            Map<String, Object> json = new HashMap<String, Object>();
            json.put("cnpj", fornecedorModel.getCnpj());
            json.put("nome", fornecedorModel.getNome());

            return new JSONObject(json);
        } else {
            response.status(404); // 404 Not found
            return "fornecedor " + cnpj + " no encontrado.";
        }

    }

    public Object update(Request request, Response response) {
        int cnpj = Integer.valueOf(request.params(":cnpj"));
		FornecedorModel fornecedorModel = (FornecedorModel) fornecedorDAO.getFornecedor(cnpj);

        if (fornecedorModel != null) {
            fornecedorModel.setCnpj(request.queryParams("cnpj").equals("") ? fornecedorModel.getCnpj() : Integer.valueOf(request.queryParams("cnpj")));
            fornecedorModel.setNome(request.queryParams("nome").equals("") ? fornecedorModel.getNome() : request.queryParams("nome"));
        	
            fornecedorDAO.updateFornecedor(fornecedorModel);
        	
            return cnpj;
        } else {
            response.status(404); // 404 Not found
            return "fornecedor nao encontrado.";
        }

	}

    public Object delete(Request request, Response response) {
        int cnpj = Integer.valueOf(request.params(":cnpj"));

        FornecedorModel fornecedorModel = (FornecedorModel) fornecedorDAO.getFornecedor(cnpj);

        if (fornecedorModel != null) {

            fornecedorDAO.deleteFornecedor(cnpj);

            response.status(200); // success
        	return cnpj;
        } else {
            response.status(404); // 404 Not found
            return "fornecedor nao encontrado.";
        }
	}
}
