package br.com.pedroyodasaito.softdesign.mensageria.contabilizavotacao;

import br.com.pedroyodasaito.softdesign.api.v1.dto.contabiliza.ContabilizacaoDTO;
import br.com.pedroyodasaito.softdesign.config.AppConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class FilaConsumidorContabilizaVotacao {

    @RabbitListener(queues = {AppConfig.FILA_NAME_CONTABILIZAR_VOTACAO})
    public void receberMsg(@Payload ContabilizacaoDTO contabilizacaoDTO) {
        System.out.println("Mensagem recebida: " + contabilizacaoDTO.toString());
    }

}
