package br.com.wollygator.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wollygator.main.models.User;
import br.com.wollygator.main.repositories.UserRepository;
import br.com.wollygator.main.services.exceptions.NotFoundException;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findById(Long id) {
        return repository.findById(id).orElseThrow(()->new NotFoundException("Usuário de id "+id+" não foi localizado!"));
    }

    // Listar os usuários com email (ou parte)
    public List<User> findByEmail(String email) {
        return repository.findByEmailContainingIgnoreCase(email);
    }
    // Listar usuários com matricula (ou parte)
    public List<User> findByMatricula(String matricula) {
        return repository.findByCarteiraMatriculaContainingIgnoreCase(matricula);
    }

    public User create(User obj) {
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
        if(obj.getName()!=null) {
            newObj.setName(obj.getName());
        }
        if(obj.getEmail()!=null) {
            newObj.setEmail(obj.getEmail());
        }
        if(obj.getPassword()!=null) {
            newObj.setPassword(obj.getPassword());
        }
        
    }

    
}
