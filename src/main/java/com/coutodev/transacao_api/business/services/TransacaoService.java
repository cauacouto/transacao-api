package com.coutodev.transacao_api.business.services;

import com.coutodev.transacao_api.controller.Dtos.TransacaoRequestDto;
import com.coutodev.transacao_api.infrascture.Exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoService {

    private final List<TransacaoRequestDto> listaDeTransacao = new ArrayList<>();

    public void AdicionarTransacao(TransacaoRequestDto dto) {
        log.info("iniciando processamento de gravar transação" + dto);
        if (dto.dataHora().isAfter(OffsetDateTime.now())) {
            log.error("data e hora maiores que a atual");
            throw new UnprocessableEntity("data e hora maiores que a data e hora atual");
        }
        if (dto.valor() < 0){
            log.error("valor não pode ser menor que 0");
            throw new UnprocessableEntity("valor não pode ser menor que 0 ");
        }
        log.info("transacões adicionadas com sucessso");
        listaDeTransacao.add(dto);

    }
    public void limparTransacao(){
        log.info("iniciando processamento para deletar transaçoes");
        listaDeTransacao.clear();
        log.info("transações deletadas com sucesso");
    }

    public List<TransacaoRequestDto> buscarTransacao(Integer intervaloBusca){
        log.info("iniciada busca de transaçoes por tempo" + intervaloBusca);
        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervaloBusca);
        log.info("retorno de transações com sucesso");
        return listaDeTransacao.stream().filter
                (transacao -> transacao.dataHora()
                        .isAfter(dataHoraIntervalo)).toList();
    }

}
