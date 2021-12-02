package app;

import static spark.Spark.*;

import java.util.*;
import spark.ModelAndView;
import service.CategoriaService;
import service.EmpresaService;
import service.EstoqueService;
import service.FornecedorService;
import service.ItemService;
import service.LoteService;
import service.UsuarioService;

public class Main {

    private static CategoriaService categoriaService = new CategoriaService();           
	private static EmpresaService empresaService = new EmpresaService();
	private static EstoqueService estoqueService = new EstoqueService();
	private static FornecedorService fornecedorService = new FornecedorService();
    private static ItemService itemService = new ItemService();
    private static LoteService loteService = new LoteService();
    private static UsuarioService usuarioService = new UsuarioService();

    public static void main(String[] args) {
        staticFiles.location("/");

        get("/hello", (req, res) -> "Hello World");


        //precisamos mudar o caminho de referencia o que esta entre aspas ""
        //por exemplo: "/produto/deletar_produto"

        //metodos para Categoria
        post("/categoria/add", (request, response) -> categoriaService.add(request, response));
        get("/categoria/get/:id", (request, response) -> categoriaService.get(request, response));
        get("/categoria/update/:id", (request, response) -> categoriaService.update(request, response));
        get("/categoria/delete/:id", (request, response) -> categoriaService.delete(request, response));

        //metodo para Empresa
        post("/empresa/add", (request, response) -> empresaService.add(request, response));
        get("/empresa/get/:id", (request, response) -> empresaService.get(request, response));
        get("/empresa/update/:id", (request, response) -> empresaService.update(request, response));
        get("/empresa/delete/:id", (request, response) -> empresaService.delete(request, response));

        //metodos para Estoque
        post("/estoque/add", (request, response) -> estoqueService.add(request, response));
        get("/estoque/get/:id", (request, response) -> estoqueService.get(request, response));
        get("/estoque/update/:id", (request, response) -> estoqueService.update(request, response));
        get("/estoque/delete/:id", (request, response) -> estoqueService.delete(request, response));

        //metodos para Fornecedor
        post("/fornecedor/add", (request, response) -> fornecedorService.add(request, response));
        get("/fornecedor/get/:id", (request, response) -> fornecedorService.get(request, response));
        get("/fornecedor/update/:id", (request, response) -> fornecedorService.update(request, response));
        get("/fornecedor/delete/:id", (request, response) -> fornecedorService.delete(request, response));
        
        //metodos para Item
        post("/item/add", (request, response) -> itemService.add(request, response));
        get("/item/get/:id", (request, response) -> itemService.get(request, response));
        get("/item/update/:id", (request, response) -> itemService.update(request, response));
        get("/item/delete/:id", (request, response) -> itemService.delete(request, response));

        //metodos para Lote
        post("/lote/add", (request, response) -> loteService.add(request, response));
        get("/lote/get/:id", (request, response) -> loteService.get(request, response));
        get("/lote/update/:id", (request, response) -> loteService.update(request, response));
        get("/lote/delete/:id", (request, response) -> loteService.delete(request, response));

        //metodos para Usuario
        post("/usuario/add", (request, response) -> usuarioService.add(request, response));
        get("/usuario/get/:id", (request, response) -> usuarioService.get(request, response));
        get("/usuario/update/:id", (request, response) -> usuarioService.update(request, response));
        get("/usuario/delete/:id", (request, response) -> usuarioService.delete(request, response));
    }
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}