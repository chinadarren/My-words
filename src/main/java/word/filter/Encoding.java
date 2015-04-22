package word.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by Administrator on 2015/4/18.
 */
@WebFilter(value = "/*")
public class Encoding implements Filter {

    private static final String ENCODE = "UTF-8";

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(ENCODE);
        response.setCharacterEncoding(ENCODE);
        chain.doFilter(request, response);//过滤器链 doFilter
    }

    public void destroy() {

    }
}
