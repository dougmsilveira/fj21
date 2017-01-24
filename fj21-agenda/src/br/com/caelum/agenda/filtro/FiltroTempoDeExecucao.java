package br.com.caelum.agenda.filtro;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class FiltroTempoDeExecucao
 */
@WebFilter("/*")
public class FiltroTempoDeExecucao implements Filter {

    /**
     * Default constructor. 
     */
    public FiltroTempoDeExecucao() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		long tempoInicial = System.currentTimeMillis();
		chain.doFilter(request, response);
		
		long tempoFinal = System.currentTimeMillis();
		String uri = ((HttpServletRequest)request).getRequestURI();
		String parametros = ((HttpServletRequest) request).getParameter("logica");
		System.out.println("Tempo de requisiacao de " + uri + "?logica="
				+ parametros + " demorou (ms): " + (tempoFinal - tempoInicial));
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
