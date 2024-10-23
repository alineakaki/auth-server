package br.com.auth_server.config;

import br.com.auth_server.security.JWTAuthenticationFilter;
import br.com.auth_server.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class SecurityConfigTest {

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private AuthService authService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilters(springSecurityFilterChain)
                .build();
    }

    @Test
    void shouldPermitActuatorHealthRequest() throws Exception {
        mockMvc.perform(get("/actuator/health"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldPermitAssetsRequestWhenAuthenticated() throws Exception {
        mockMvc.perform(get("/v1/assets")
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldForbidAnyOtherRequest() throws Exception {
        mockMvc.perform(get("/v1/test")
                        .with(csrf()))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(post("/v1/test")
                        .with(csrf()))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(get("/v2/test")
                        .with(csrf()))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(post("/v1/jwt/test")
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }
}
