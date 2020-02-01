package basePackage.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        String targetUrl = determineTargetUrl(request,authentication);
        if (response.isCommitted()) {
            System.out.println("Can't redirect");
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
    /*
     * This method extracts the roles of currently logged-in user and returns
     * appropriate URL according to his/her role.
     */
    private String determineTargetUrl(HttpServletRequest request, Authentication authentication) {
        String url = "";

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List<String> roles = new ArrayList<>();

        for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }
        Pattern pattern = Pattern.compile("^/(admin)/$");
        Matcher matcher = pattern.matcher(request.getServletPath());
        if (matcher.group().equals("admin")) {
            if (isAdmin(roles)) {
                url = request.getServletPath();
            }else {
                url = "/accessDenied";
            }
        }else{
            if (isUser(roles)){
                url = request.getServletPath();
            }else {
                url = "/accessDenied";
            }
        }


        return url;
    }
    private boolean isUser(List<String> roles) {
        if (roles.contains("ROLE_USER")) {
            return true;
        }
        return false;
    }

    private boolean isAdmin(List<String> roles) {
        if (roles.contains("ROLE_ADMIN")) {
            return true;
        }
        return false;
    }



    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
}
