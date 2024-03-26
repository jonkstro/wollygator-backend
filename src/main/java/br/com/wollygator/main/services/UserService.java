package br.com.wollygator.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wollygator.main.models.User;
import br.com.wollygator.main.models.enums.TipoUsuario;
import br.com.wollygator.main.repositories.UserRepository;
import br.com.wollygator.main.services.exceptions.DataBaseException;
import br.com.wollygator.main.services.exceptions.NotFoundException;
import br.com.wollygator.main.services.utils.UserServiceUtil;
import jakarta.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired // Injeção de dependencia de CarteiraService
    private CarteiraService carteiraService;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário de id " + id + " não foi localizado!"));
    }

    // Listar os usuários com email (ou parte)
    public List<User> findByEmail(String email) {
        return repository.findByEmailContainingIgnoreCase(email);
    }

    // Listar usuários com matricula (ou parte)
    public List<User> findByMatricula(String matricula) {
        return repository.findByCarteiraMatriculaContainingIgnoreCase(matricula);
    }

    // Transactional quer dizer que vai ser executado junto do create do UserService
    @Transactional
    public User create(User obj) {

        if (!UserServiceUtil.isPasswordValid(obj.getPassword())) {
            // TODO: Adicionar Security para Hashear a senha
            return null;
        }

        if (findByEmail(obj.getEmail()).size() > 0) {
            throw new DataBaseException("Já existe um usuário com esse email cadastrado!");
        }

        // Padronizar o usuario como USUARIO COMUM ao criar o mesmo
        obj.setTipoUsuario(TipoUsuario.COMUM);

        // Criar a carteira com os dados vindo do Usuario.
        // Se tiver que estourar algo estoura na service de carteira
        carteiraService.create(obj.getCarteira());

        return repository.save(obj);
    }

    public void deleteById(Long id) {
        // se não achar nada vai estourar NotFoundException
        User obj = findById(id);
        repository.deleteById(obj.getId());
    }

    public User update(Long id, User obj) {
        // se não achar nada vai estourar NotFoundException
        User newObj = findById(id);
        updateDate(newObj, obj);
        return repository.save(newObj);
    }

    private void updateDate(User newObj, User obj) {
        if (obj.getName() != null) {
            newObj.setName(obj.getName());
        }
        if (obj.getEmail() != null) {
            newObj.setEmail(obj.getEmail());
        }
        if (obj.getPassword() != null) {
            newObj.setPassword(obj.getPassword());
        }

    }

}
