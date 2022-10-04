package com.diego.Sistema.gerenciamento.de.frota.util;

import com.diego.Sistema.gerenciamento.de.frota.model.service.VeiculoService;
import org.springframework.stereotype.Service;

@Service
public class NumeracaoVeiculo {

    final VeiculoService veiculoService;
    public NumeracaoVeiculo(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }


    private  final String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWYXZ";


    public String numeraVeiculo(){

        int numero = (int) Math.round(Math.random() * 10000);
        String letra = String.valueOf(alfabeto.charAt((int) Math.round(Math.random() * 25)));

        if(veiculoService.existsByNumeracaoVeiculo(numero + letra)){
            numeraVeiculo();
        }

        return numero + letra;
    }


}
