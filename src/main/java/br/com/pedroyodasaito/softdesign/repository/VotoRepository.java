package br.com.pedroyodasaito.softdesign.repository;

import br.com.pedroyodasaito.softdesign.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoRepository extends JpaRepository<Voto, Integer> {
}
