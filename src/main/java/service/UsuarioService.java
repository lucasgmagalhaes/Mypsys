package service;
import dao.*;
import model.*;
import spark.Request;
import spark.Response;
import org.json.simple.JSONObject;

import java.util.HashMap;

import java.util.Map;

public class UsuarioService {
    private UsuarioDAO usuarioDAO;

    public UsuarioService() {
        usuarioDAO = new UsuarioDAO();
    }

    public Object add(Request request, Response response) {
        String email = request.queryParams("email");
        String nome = request.queryParams("nome");
        String senha = request.queryParams("senha");
        UsuarioModel usuarioModel = new UsuarioModel(email,nome,senha);

        if (usuarioDAO.createUsuario(usuarioModel)) {
            response.status(201);
        } else {
            response.status(500);
        }
        return email;
    }

    public Object get(Request request, Response response) {
        String email = (request.params(":email"));

        UsuarioModel usuarioModel = (UsuarioModel) usuarioDAO.getUsuario(email);

        if (usuarioModel != null) {
            response.header("Content-Type", "application/json");
            response.header("Content-Encoding", "UTF-8");
            Map<String, Object> json = new HashMap<String, Object>();
            json.put("email", usuarioModel.getEmail());
            json.put("nome", usuarioModel.getNome());
            json.put("senha", usuarioModel.getSenha());

            return new JSONObject(json);
        } else {
            response.status(404); // 404 Not found
            return "User " + email + " no encontrado.";
        }

    }

    public Object update(Request request, Response response) {
        String email = (request.params(":email"));
		UsuarioModel usuarioModel = (UsuarioModel) usuarioDAO.getUsuario(email);

        if (usuarioModel != null) {
            usuarioModel.setEmail(request.queryParams("email").equals("") ? usuarioModel.getNome() : request.queryParams("email"));
            usuarioModel.setNome(request.queryParams("nome").equals("") ? usuarioModel.getNome() : request.queryParams("nome"));
            usuarioModel.setSenha(request.queryParams("senha").equals("") ? usuarioModel.getNome() : request.queryParams("senha"));
        	
            usuarioDAO.updateUsuario(usuarioModel);
        	
            return email;
        } else {
            response.status(404); // 404 Not found
            return "usuario nao encontrado.";
        }

	}

    public Object delete(Request request, Response response) {
        String email = (request.params(":email"));

        UsuarioModel usuarioModel = (UsuarioModel) usuarioDAO.getUsuario(email);

        if (usuarioModel != null) {

            usuarioDAO.deleteUsuario(email);

            response.status(200); // success
        	return email;
        } else {
            response.status(404); // 404 Not found
            return "usuario nao encontrado.";
        }
	}
}
