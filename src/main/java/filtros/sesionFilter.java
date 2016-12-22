/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filtros;

import controller.SesionController;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ramiro
 */
@WebFilter(filterName = "sesionFilter", urlPatterns = {"/faces/usuario/*"})
public class sesionFilter implements Filter {

    @Inject
    SesionController sesionController;

    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
//        SesionController sesionController = (SesionController)((HttpServletRequest)request).getSession().getAttribute("sesionController");
         
        
        if(sesionController==null || sesionController.getUsuarioLogueado()==null){
            String contextPath = ((HttpServletRequest)request).getContextPath();
            ((HttpServletResponse)response).sendRedirect(contextPath + "/faces/index.xhtml");
        }
        
        chain.doFilter(request, response);
        
        
    }

    @Override
    public void destroy() {

    }
    
    
    
}
