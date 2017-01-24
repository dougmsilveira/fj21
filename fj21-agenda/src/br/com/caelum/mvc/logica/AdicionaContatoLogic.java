package br.com.caelum.mvc.logica;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.agenda.dao.ContatoDao;
import br.com.caelum.agenda.modelo.Contato;

public class AdicionaContatoLogic implements Logica {
	public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		

		if (req.getParameter("nome") == null) {
			return "WEB-INF/jsp/adiciona-contato.jsp";
		}else{
			// buscando os parametros no request
			String nome = req.getParameter("nome");
			String endereco = req.getParameter("endereco");
			String email = req.getParameter("email");
			String dataEmTexto = req.getParameter("dataNascimento");
			Calendar dataNascimento = null;

			// fazendo a conversao da data
			try {
				Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dataEmTexto);
				dataNascimento = Calendar.getInstance();
				dataNascimento.setTime(date);
			} catch (ParseException e) {
				throw new ParseException("erro ao converter a data", e.getErrorOffset());
			}

			// monta um objeto contato
			Contato contato = new Contato();
			contato.setNome(nome);
			contato.setEndereco(endereco);
			contato.setEmail(email);
			contato.setDataNascimento(dataNascimento);

			// busca a conexao pendurada na conexao
			Connection connection = (Connection) req.getAttribute("conexao");
			
			// salva contato
			ContatoDao dao = new ContatoDao(connection);
			dao.adiciona(contato);
			
			System.out.println("Contato Adicionado..");
			
			return "mvc?logica=ListaContatosLogic";
		}

	}
}
