package com.patrickradaelli.tarefaapi.repository;


import com.patrickradaelli.tarefaapi.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
