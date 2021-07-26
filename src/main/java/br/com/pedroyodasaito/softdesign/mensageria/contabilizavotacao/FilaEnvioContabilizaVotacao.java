package br.com.pedroyodasaito.softdesign.mensageria.contabilizavotacao;

import br.com.pedroyodasaito.softdesign.api.v1.dto.contabiliza.ContabilizacaoDTO;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class FilaEnvioContabilizaVotacao {

    private final RabbitTemplate rabbitTemplate;

    private final Queue queueContabilizar;

    public FilaEnvioContabilizaVotacao(RabbitTemplate rabbitTemplate, Queue queueContabilizar) {
        this.rabbitTemplate = rabbitTemplate;
        this.queueContabilizar = queueContabilizar;
    }

    public void enviar(ContabilizacaoDTO contabilizacaoDTO) {
        System.out.println("Enviando msg: " + contabilizacaoDTO.toString());
        rabbitTemplate.convertAndSend(queueContabilizar.getName(), contabilizacaoDTO);
    }
}
