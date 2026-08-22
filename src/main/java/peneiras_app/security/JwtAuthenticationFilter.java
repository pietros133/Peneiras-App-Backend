package peneiras_app.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // 1. Pega o header Authorization
        String authHeader = request.getHeader("Authorization");

        // 2. Verifica se existe o Bearer Token
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;
        }

        // 3. Remove o "Bearer " e fica somente com o JWT
        String token = authHeader.substring(7);

        try {

            // 4. Extrai o email do JWT
            String email = jwtService.extractEmail(token);

            // 5. Se conseguiu extrair o email e ainda não existe
            //    uma autenticação nesse request
            if (email != null &&
                    SecurityContextHolder.getContext().getAuthentication() == null) {

                // 6. Cria a autenticação do usuário
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                email,
                                null,
                                null
                        );

                // 7. Adiciona detalhes da requisição
                authentication.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                // 8. Coloca o usuário autenticado no SecurityContext
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authentication);
            }

        } catch (Exception e) {

            // JWT inválido/expirado
            SecurityContextHolder.clearContext();
        }

        // 9. Continua a requisição
        filterChain.doFilter(request, response);
    }
}