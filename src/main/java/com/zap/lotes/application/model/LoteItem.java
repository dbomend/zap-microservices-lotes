package com.zap.lotes.application.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.zap.lotes.application.enums.StatusLote;
import com.zap.lotes.application.enums.StatusLoteItem;
import com.zap.lotes.application.enums.TipoLote;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "lote_item")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class LoteItem implements Serializable {

    @ApiModelProperty(value = "Identificação do item do lote", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ApiModelProperty(value = "Lote que o item pertence", example = "1")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lote_id", referencedColumnName = "id", nullable = false)
    private Lote lote;

    @ApiModelProperty(value = "Identificação da origem do pagamento do item do lote", example = "ee77ec4f-58f5-496e-83ee-e9abdae3e726")
    @NotNull(message = "Informe o id unico do item do lote")
    @Column(name = "id_unico", nullable = false)
    private String idUnico;

    @ApiModelProperty(value = "Pessoa que o item do lote pertence", example = "1")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contato_id", referencedColumnName = "id", nullable = false)
    private Contato contato;

    @ApiModelProperty(value = "Controla se o item do lote deve ser processado", example = "true")
    @NotNull(message = "Informe se o item do lote deve ser processado")
    @Column(name = "processar", nullable = false)
    private boolean processar;

    @ApiModelProperty(value = "Status do item do lote", example = "PENDENTE")
    @NotNull(message = "Informe o status do item do lote")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusLoteItem status;

    @ApiModelProperty(value = "Conta de origem do pagamento do item do lote", example = "983948B1-6508-A185-A561-DDEC32FCBFD4")
    @NotNull(message = "Informe o id da conta de origem do pagamento do item do lote")
    @Column(name = "origem_conta_id", nullable = false)
    private String origemContaId;

    @ApiModelProperty(value = "Código de histórico da origem do pagamento do item do lote", example = "1502")
    @NotNull(message = "Informe o código de histórico da origem do pagamento do item do lote")
    @Column(name = "origem_codigo_historico", nullable = false)
    private String origemCodigoHistorico;

    @ApiModelProperty(value = "Mensagem de origem do pagamento do item do lote", example = "Débito para o pagamento do prêmio da ZapBets")
    @NotNull(message = "Informe a mensagem de origem do pagamento do item do lote")
    @Column(name = "origem_mensagem", nullable = false)
    private String origemMensagem;

    @ApiModelProperty(value = "Conta de destino do pagamento do item do lote", example = "449")
    @NotNull(message = "Informe o id da conta de destino do pagamento do item do lote")
    @Column(name = "destino_conta_id", nullable = false)
    private String destinoContaId;

    @ApiModelProperty(value = "Código de histórico do destino do pagamento do item do lote", example = "1707")
    @NotNull(message = "Informe o código de histórico do destino do pagamento do item do lote")
    @Column(name = "destino_codigo_historico", nullable = false)
    private String destinoCodigoHistorico;

    @ApiModelProperty(value = "Mensagem de destino do pagamento do item do lote", example = "Crédito referente ao pagamento do prêmio da ZapBets")
    @NotNull(message = "Informe a mensagem de destino do pagamento do item do lote")
    @Column(name = "destino_mensagem", nullable = false)
    private String destinoMensagem;

    @ApiModelProperty(value = "Valor do pagamento do item do lote", required = true, example = "15.00")
    @NotNull(message = "Informe o valor do pagamento do item do lote")
    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @ApiModelProperty(value = "Json com os dados específicos  do item do lote", example = " ")
    @NotNull(message = "Informe os dados adicionais do item do lote")
    @Column(name = "dados_adicionais", nullable = false, columnDefinition = "jsonb")
    @Type(type = "jsonb")
    private String dadosAdicionais;

    @ApiModelProperty(value = "Data e hora em que o item do lote foi atualizado", example = "2022-05-04T11:15:26.988909")
    @Column(name = "data_hora_atualizacao")
    private LocalDateTime dataHoraAtualizacao;

    @ApiModelProperty(value = "Mensagem de erro amigável para o usuário", example = " ")
    @Column(name = "erro_motivo")
    private String erroMotivo;

    @ApiModelProperty(value = "Mensagem de exceção do erro", example = " ")
    @Column(name = "erro_exception")
    private String erroException;

}
