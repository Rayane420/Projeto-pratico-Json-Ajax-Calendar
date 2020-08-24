package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoUser;
import entidades.Usuario;


@WebServlet("/pages/carregarDadosDataTable")
public class CarregarDadosDataTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private DaoUser daoUser = new DaoUser();
	
    public CarregarDadosDataTable() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			List<Usuario> usuarios = daoUser.getUsuarios();

		if (!usuarios.isEmpty()) {
			
			String data = "";
			int totalUsuarios = usuarios.size();
			int index = 1;
			
			for (Usuario usuario : usuarios) {
				
				data +="["+ "\"" +usuario.getId() + "\"," + "\"" +usuario.getLogin() + "\"" +  "]";
				
				if(index < totalUsuarios) {
					data += ",";
				}
				index++;
			}
		String json = "{" +
				  "\"draw\": 1,"+
				  "\"recordsTotal\": "+usuarios.size()+","+
				  "\"recordsFiltered\": "+usuarios.size()+","+
				  "\"data\": ["+data + "]"+ //Fechamento do data
				"}"; //Fechamento do json
				
		response.setStatus(200); // resposta completa de OK para o HTML
		response.getWriter().write(json); // Json de resposta (escreve a resposta http)
	}

} catch (Exception e) {
	e.printStackTrace();
	response.setStatus(500);
}

}
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
