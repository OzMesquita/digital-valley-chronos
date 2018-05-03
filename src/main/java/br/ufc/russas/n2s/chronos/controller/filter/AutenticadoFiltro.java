package br.ufc.russas.n2s.chronos.controller.filter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import br.ufc.russas.n2s.chronos.beans.UsuarioBeans;
import br.ufc.russas.n2s.chronos.model.EnumPermissao;
import br.ufc.russas.n2s.chronos.service.UsuarioServiceIfc;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.UsuarioDAO;
import model.Pessoa;
import model.Usuario;
import dao.DAOFactory;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import util.Facade;
/**
 *
 * @author N2S
 */
public class AutenticadoFiltro implements Filter {
        
        private UsuarioServiceIfc usuarioServiceIfc;

        public UsuarioServiceIfc getUsuarioServiceIfc() {
            return usuarioServiceIfc;
        }

        @Autowired(required = true)
        public void setUsuarioServiceIfc(@Qualifier("usuarioServiceIfc")UsuarioServiceIfc usuarioServiceIfc){
            this.usuarioServiceIfc = usuarioServiceIfc;
        }
        
    
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
            String path = ((HttpServletRequest) request).getServletPath();
            if (path.endsWith("/autentica")){
                    chain.doFilter(request, response);
            }else{
            	 System.out.println("foi\n\n");
                    HttpSession session = ((HttpServletRequest) request).getSession();
                    if(request.getParameter("token") != null && request.getParameter("id") != null){
                            String token = request.getParameter("token");
                            int id = Integer.parseInt(request.getParameter("id"));
                            Pessoa user = Facade.buscarPessoaPorId(id);
                            if (token.equals(user.getUsuario().getTokenUsuario()) && id == user.getId() && !token.equals("null")) {
                                    UsuarioDAO userDAO = DAOFactory.criarUsuarioDAO();
                                    session.setAttribute("usuario", user.getUsuario());
                                    UsuarioBeans u = new UsuarioBeans();
                                    if(this.getUsuarioServiceIfc().getUsuarioControleDeAcesso(user.getId()) == null){
                                        u.setCodUsuarioControleDeAcesso(user.getId());
                                        u.setNome(user.getNome());
                                        ArrayList<EnumPermissao> permissoes = new ArrayList<>();
                                        permissoes.add(EnumPermissao.ADMINISTRADOR);
                                        u.setPermissoes(permissoes);
                                        this.getUsuarioServiceIfc().adicionaUsuario(u);
                                    }
                                    session.setAttribute("usuarioChronos", this.getUsuarioServiceIfc().getUsuarioControleDeAcesso(user.getId()));
                                    chain.doFilter(request, response);
                            }else {
                                ((HttpServletResponse) response).sendRedirect("http://192.169.1.2:8080/guardiao/");
                            }
                    }else if(session.getAttribute("usuario")!= null && DAOFactory.criarUsuarioDAO().buscarTokenTemp(((Usuario)session.getAttribute("usuario")).getPessoa().getId())!=null && ((Usuario)session.getAttribute("usuario")).getTokenUsuario().equals(DAOFactory.criarUsuarioDAO().buscarTokenTemp(((Usuario)session.getAttribute("usuario")).getPessoa().getId()))){
                    	chain.doFilter(request, response);
                    }else {
                            ((HttpServletResponse) response).sendRedirect("http://192.169.1.2:8080/guardiao/");
                    }
            }				
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
            SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
            arg0.getServletContext());		
	}


}