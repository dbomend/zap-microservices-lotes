package com.zap.lotes.application.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusLote {

    CRIANDO,
    PRONTO,
    PROCESSANDO,
    PROCESSADO_COM_ERRO,
    PROCESSADO_COM_SUCESSO

}
