package br.com.pedroyodasaito.softdesign.repository;

import br.com.pedroyodasaito.softdesign.entity.Sessao;
import br.com.pedroyodasaito.softdesign.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VotoRepository extends JpaRepository<Voto, Integer> {

    @Query("select v from Voto v where v.sessao = :sessao")
    List<Voto> obterVotosPorPauta(@Param("sessao") Sessao sessao);

}
