package br.com.wollygator.main.services;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wollygator.main.models.Carteira;
import br.com.wollygator.main.repositories.CarteiraRepository;
import br.com.wollygator.main.services.exceptions.NotFoundException;
import br.com.wollygator.main.services.utils.CarteiraServiceUtil;
import jakarta.transaction.Transactional;
import net.sf.jsqlparser.util.validation.metadata.DatabaseException;

@Service
public class CarteiraService {
    @Autowired
    private CarteiraRepository repository;

    public List<Carteira> findAll() {
        return repository.findAll();
    }

    public Carteira findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Carteira de id " + id + " não foi encontrada!"));
    }

    // Transactional quer dizer que vai ser executado junto do create do UserService
    @Transactional
    public Carteira create(Carteira obj) {

        // Alterar automatico as datas de expedição (data atual) e a data de validade
        obj.setDataExpedicao(Date.from(Instant.now()));
        obj.setDataValidade(CarteiraServiceUtil.getDataValidade(obj.getDataExpedicao()));
        if (obj.getDataExpedicao().after(obj.getDataValidade())) {
            throw new DatabaseException("A data de validade deve ser após a data de expedição");
        }
        if (repository.findByMatricula(obj.getMatricula()).size() > 0) {
            throw new DatabaseException("Já existe um associado cadastrado com essa matrícula");
        }
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
        if (obj.getMatricula() != null) {
            newObj.setMatricula(obj.getMatricula());
        }
        if (obj.getUrlFoto() != null) {
            newObj.setUrlFoto(obj.getUrlFoto());
        }
        if (obj.getDataValidade() != null) {
            newObj.setDataValidade(obj.getDataValidade());
        }

    }
}
