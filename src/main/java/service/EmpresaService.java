package service;
import dao.*;
import model.*;
import spark.Request;
import spark.Response;
import org.json.simple.JSONObject;


import java.util.HashMap;
import java.util.Map;

public class EmpresaService {
    private EmpresaDAO empresaDAO;

    public EmpresaService() {
        empresaDAO = new EmpresaDAO();
    }

    public Object add(Request request, Response response) {
        int cnpj = Integer.valueOf(request.queryParams("cnpj"));
        String nome = request.queryParams("nome");
        String usuario_email = request.queryParams("usuario_email");

        EmpresaModel empresa = new EmpresaModel(cnpj, nome, usuario_email);

        if (empresaDAO.createEmpresa(empresa)) {
            response.status(201);
        } else {
            response.status(500);
        }
        return nome;
    }

    public Object get(Request request, Response response) {
        int cnpj = Integer.valueOf(request.params(":cnpj"));

        EmpresaModel empresa = (EmpresaModel) empresaDAO.getEmpresa(cnpj);

        if (empresa != null) {
            response.header("Content-Type", "application/json");
            response.header("Content-Encoding", "UTF-8");
            
          
            Map<String, Object> json = new HashMap<String, Object>();
            json.put("cnpj", empresa.getCnpj());
            json.put("nome", empresa.getNome());
            json.put("usuario_email", empresa.getUsuario_email());

            return new JSONObject(json);
        } else {
            response.status(404); // 404 Not found
            return "estoque " + cnpj + " nao encontrado.";
        }

    }

    public Object update(Request request, Response response) {
        int cnpj = Integer.valueOf(request.params(":cnpj"));
		EmpresaModel empresa = (EmpresaModel) empresaDAO.getEmpresa(cnpj);

        if (empresa != null) {
            

            empresa.setNome(request.queryParams("nome").equals("") ? empresa.getNome() : request.queryParams("nome"));
            empresa.setUsuario_email(request.queryParams("usuario_email").equals("") ? empresa.getUsuario_email() : request.queryParams("usuario_email"));
            empresaDAO.updateEmpresa(empresa);
        	
            return cnpj;
        } else {
            response.status(404); // 404 Not found
            return "estoque nao encontrado.";
        }

	}

    public Object delete(Request request, Response response) {
        int cnpj = Integer.parseInt(request.params(":cnpj"));

        EmpresaModel empresa = (EmpresaModel) empresaDAO.getEmpresa(cnpj);

        if (empresa != null) {

            empresaDAO.deleteEmpresa(cnpj);

            response.status(200); // success
        	return cnpj;
        } else {
            response.status(404); // 404 Not found
            return "estoque nao encontrado.";
        }
	}
}
