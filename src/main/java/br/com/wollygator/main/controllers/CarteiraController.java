package br.com.wollygator.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wollygator.main.models.Carteira;
import br.com.wollygator.main.services.CarteiraService;

@RestController
@RequestMapping("/carteira")
public class CarteiraController {
    @Autowired
    private CarteiraService service;

    @GetMapping
    public ResponseEntity<List<Carteira>> getAll() {
        return ResponseEntity.ok().body(service.findAll());
    }
}
