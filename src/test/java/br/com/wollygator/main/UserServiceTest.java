package br.com.wollygator.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.wollygator.main.models.Carteira;
import br.com.wollygator.main.models.User;
import br.com.wollygator.main.models.enums.TipoUsuario;
import br.com.wollygator.main.repositories.UserRepository;
import br.com.wollygator.main.services.UserService;

/*
 * TODO: Testes que deverão ser validados
 * 1 - Validar se já tem email cadastrado
 * 2 - Validar se já tem matricula cadastrada
 * 3 - Validar se a data de validade é menor que a de expedição
 * 4 - Criar por padrão o usuario COMUM
 * 
 * Adicionar mais testes, se necessário
 */

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        user1 = createUser1();
        user2 = createUser2();
        user3 = createUser3();
    }

    @Test
    @DisplayName("Deve buscar por email, ignorando case sensitive")
    void deveBuscarUserPorEmailComSucesso() {
        // Crie uma lista contendo o usuário que você espera encontrar
        List<User> usuariosEncontrados = new ArrayList<>();
        usuariosEncontrados.add(user1);

        // Simular o comportamento do repository retornando a lista de usuários
        when(repository.findByEmailContainingIgnoreCase(user1.getEmail())).thenReturn(usuariosEncontrados);
        User result = service.findByEmail(user1.getEmail()).get(0);
        
        assertEquals(usuariosEncontrados.get(0), result);
        // Ver se o repository foi chamado alguma vez
        verify(repository).findByEmailContainingIgnoreCase(user1.getEmail());
        // Ver se o repository foi chamado mais de uma vez
        verifyNoMoreInteractions(repository);

    }

    public static User createUser1() {
        Carteira carteira = new Carteira();
        carteira.setMatricula("123456");
        carteira.setUrlFoto("https://exemplo.com/foto1.jpg");

        User user = new User();
        user.setId(1L);
        user.setName("João");
        user.setEmail("joao@example.com");
        user.setPassword("senha123");
        user.setCarteira(carteira);
        user.setTipoUsuario(TipoUsuario.COMUM);
        user.setCreatedAt(Instant.now());
        
        return user;
    }

    public static User createUser2() {
        Carteira carteira = new Carteira();
        carteira.setMatricula("654321");
        carteira.setUrlFoto("https://exemplo.com/foto2.jpg");

        User user = new User();
        user.setId(2L);
        user.setName("Maria");
        user.setEmail("maria@example.com");
        user.setPassword("senha456");
        user.setCarteira(carteira);
        user.setTipoUsuario(TipoUsuario.ADMIN);
        user.setCreatedAt(Instant.now());
        
        return user;
    }

    public static User createUser3() {
        Carteira carteira = new Carteira();
        carteira.setMatricula("987654");
        carteira.setUrlFoto("https://exemplo.com/foto3.jpg");

        User user = new User();
        user.setId(3L);
        user.setName("Pedro");
        user.setEmail("pedro@example.com");
        user.setPassword("senha789");
        user.setCarteira(carteira);
        user.setTipoUsuario(TipoUsuario.COMUM);
        user.setCreatedAt(Instant.now());
        
        return user;
    }

}
/*
 * @Test
 * 
 * @DisplayName("Não deve cadastrar usuario com CPF com menos de 8 e mais de 11 caracteres"
 * )
 * void naoDeveCadastrarUserComCpfInvalido() {
 * user.setCpf("123456");
 * final IllegalArgumentException e =
 * assertThrows(IllegalArgumentException.class, () -> service.create(user));
 * 
 * // Ver se a variavel e não tá vazia
 * assertNotNull(e);
 * // Ver se a causa não tá vazia
 * // assertNotNull(e.getCause());
 * // Ver se a mensagem é a mesma da exceção
 * assertEquals("CPF deve ter 11 dígitos", e.getMessage());
 * // Verificar se o repositório foi chamado, não deve ser chamado se deu
 * exception
 * // OBS.: Tá quebrando, não sei pq
 * // verifyNoInteractions(repository);
 * 
 * }
 * 
 * @Test
 * 
 * @DisplayName("Deve formatar o CPF corretamente")
 * void deveFormatarCpfCorretamenteSoComNumeros() {
 * // Quando chamar o save do repository vai retornar o user
 * when(repository.save(user)).thenReturn(user);
 * user.setCpf("017.558.430-38");
 * 
 * User result = service.create(user);
 * 
 * assertEquals("01755843038", result.getCpf());
 * }
 * 
 * }
 * 
 */