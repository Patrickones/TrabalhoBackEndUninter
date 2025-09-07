package com.patrickradaelli.tarefaapi.controller;

import com.patrickradaelli.tarefaapi.model.Tarefa;
import com.patrickradaelli.tarefaapi.repository.TarefaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    private final TarefaRepository tarefaRepository;

    public TarefaController(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    // Criar
    @PostMapping
    public Tarefa criar(@RequestBody Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    // Listar todas
    @GetMapping
    public List<Tarefa> listarTodas() {
        return tarefaRepository.findAll();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarPorId(@PathVariable Long id) {
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);
        return tarefa.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @RequestBody Tarefa novaTarefa) {
        return tarefaRepository.findById(id)
                .map(tarefa -> {
                    tarefa.setNome(novaTarefa.getNome());
                    tarefa.setDataEntrega(novaTarefa.getDataEntrega());
                    tarefa.setResponsavel(novaTarefa.getResponsavel());
                    return ResponseEntity.ok(tarefaRepository.save(tarefa));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return tarefaRepository.findById(id)
                .map(tarefa -> {
                    tarefaRepository.delete(tarefa);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
