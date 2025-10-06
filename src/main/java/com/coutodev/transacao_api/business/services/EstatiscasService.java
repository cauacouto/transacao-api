package com.coutodev.transacao_api.business.services;

import com.coutodev.transacao_api.controller.Dtos.EstaticasResponseDto;
import com.coutodev.transacao_api.controller.Dtos.TransacaoRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@Slf4j
public class EstatiscasService {

    @Autowired
    private TransacaoService transacaoService;


    public EstaticasResponseDto calcularEstatiscaTransacoes(Integer intarvaloBusca){
        log.info("iniciada a busca de estatiscas de transações pelo perido de tempo " + intarvaloBusca);
        List<TransacaoRequestDto> transacoes = transacaoService.buscarTransacao(intarvaloBusca);
          if (transacoes.isEmpty()){
              return new EstaticasResponseDto(0L,0.0,0.0,0.0,0.0);
          }
        DoubleSummaryStatistics estatiscasTransacoes = transacoes.stream()
                .mapToDouble(TransacaoRequestDto::valor).summaryStatistics();
        log.info("estatisticas retornada com sucesso");
        return new EstaticasResponseDto(estatiscasTransacoes.getCount(),
                estatiscasTransacoes.getSum(),
                estatiscasTransacoes.getAverage(),
                estatiscasTransacoes.getMin(),
                estatiscasTransacoes.getMax());
    }
}
