package com.coutodev.transacao_api.controller;

import com.coutodev.transacao_api.business.services.TransacaoService;
import com.coutodev.transacao_api.controller.Dtos.TransacaoRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
public class TransacoesController {
    @Autowired
    private TransacaoService transacaoService;


    @PostMapping
    @Operation(description = "endepoint responsavel por adicionar transações")
    @ApiResponses(value = {
           @ApiResponse(responseCode = "201",description = "transação gravada com sucesso"),
            @ApiResponse(responseCode = "442",description = "campos não atendem os requesitos da transação"),
            @ApiResponse(responseCode = "400",description = "erro de requição "),
            @ApiResponse(responseCode = "500",description = "erro interno do servidor")
    })
    public ResponseEntity<Void> adicionarTransacoes(@RequestBody TransacaoRequestDto dto){
        transacaoService.AdicionarTransacao(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    @Operation(description = "endepoint responsavel por deletar transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "transação deletada com sucesso"),
            @ApiResponse(responseCode = "400",description = "erro de requição "),
            @ApiResponse(responseCode = "500",description = "erro interno do servidor")
    })
    public  ResponseEntity<Void> deletarTransacoes(){
        transacaoService.limparTransacao();
        return ResponseEntity.ok().build();
    }

}
