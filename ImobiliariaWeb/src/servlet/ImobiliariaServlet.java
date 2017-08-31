package servlet;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;


@WebServlet("/ImobiliariaServlet")
public class ImobiliariaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		InputStreamReader isr = 
				new InputStreamReader(request.getInputStream());
		BufferedReader br = new BufferedReader(isr);
		StringBuffer json = new StringBuffer();
		String line = br.readLine();
		while (line != null) {
			json.append(line);
			line = br.readLine();
		}
		
		JSONObject obj = new JSONObject(json.toString());
		String img = obj.getString("imagem");
		
		byte[] imgByte = Base64.decodeBase64(img);
		
		FileOutputStream fos = 
				new FileOutputStream("C:\\temp\\imgImovel.jpg");
		fos.write(imgByte);
		fos.close();
		
		response.getWriter().print("{\"status\":\"OK\"}");
		
	}

}
