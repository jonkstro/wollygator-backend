package br.com.wollygator.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wollygator.main.models.Carteira;
import br.com.wollygator.main.repositories.CarteiraRepository;
import br.com.wollygator.main.services.exceptions.NotFoundException;

@Service
public class CarteiraService {
    @Autowired
    private CarteiraRepository repository;

    public List<Carteira> findAll() {
        return repository.findAll();
    }

    public Carteira findById(Long id) {
        return repository.findById(id).orElseThrow(()-> new NotFoundException("Carteira de id "+id+" não foi encontrada!"));
    }

    public Carteira create(Carteira obj) {
        return repository.save(obj);
    }

    public void deleteById(Long id) {
        // se não achar nada vai estourar NotFoundException
        Carteira obj = findById(id);
        repository.deleteById(obj.getId());
    }
    
    public Carteira update(Long id, Carteira obj) {
        // se não achar nada vai estourar NotFoundException
        Carteira newObj = findById(id);
        updateDate(newObj, obj);
        return repository.save(newObj);
    }

    private void updateDate(Carteira newObj, Carteira obj) {
        if(obj.getMatricula() != null) {
            newObj.setMatricula(obj.getMatricula());
        }
        if(obj.getUrlFoto() != null) {
            newObj.setUrlFoto(obj.getUrlFoto());
        }
        if(obj.getDataValidade() != null) {
            newObj.setDataValidade(obj.getDataValidade());
        }
        
    }
}
