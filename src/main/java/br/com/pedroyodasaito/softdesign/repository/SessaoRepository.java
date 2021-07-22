package br.com.pedroyodasaito.softdesign.repository;

import br.com.pedroyodasaito.softdesign.entity.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessaoRepository extends JpaRepository<Sessao, Integer> {
}
