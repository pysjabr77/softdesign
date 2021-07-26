package br.com.pedroyodasaito.softdesign.service;

import br.com.pedroyodasaito.softdesign.api.v1.dto.voto.VotarDTO;
import br.com.pedroyodasaito.softdesign.entity.Voto;

public interface VotoService {

    void votar(VotarDTO dto);

    void consolidarVoto(Voto voto);

}
