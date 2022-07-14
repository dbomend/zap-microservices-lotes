package com.zap.lotes.application.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.zap.lotes.application.enums.StatusLote;
import com.zap.lotes.application.enums.TipoLote;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lote")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Lote implements Serializable {

    @ApiModelProperty(value = "Identificação do lote", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ApiModelProperty(value = "Tipo do lote", example = "PEGAMENTO_BETS")
    @NotNull(message = "Informe o tipo do lote")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoLote tipo;

    @ApiModelProperty(value = "Descrição do lote", example = "TIME A X TIME B")
    @NotNull(message = "Informe a descrição do lote")
    @Column(name = "descricao", nullable = false)
    private String descricao;

    @ApiModelProperty(value = "Json com os dados específicos  do tipo do lote", example = " ")
    @NotNull(message = "Informe os dados adicionais do lote")
    @Column(name = "dados_adicionais", nullable = false, columnDefinition = "jsonb")
    @Type(type = "jsonb")
    private String dadosAdicionais;

    @ApiModelProperty(value = "Data e hora em que o lote foi criado", example = "2022-05-04T11:15:26.988909")
    @Column(name = "data_hora_criacao", nullable = false)
    private LocalDateTime dataHoraCriacao;

    @ApiModelProperty(value = "Status do lote", example = "CRIANDO")
    @NotNull(message = "Informe o status do lote")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusLote status;

    @ApiModelProperty(value = "Data e hora em que o lote foi atualizado", example = "2022-05-04T11:15:26.988909")
    @Column(name = "data_hora_atualizacao")
    private LocalDateTime dataHoraAtualizacao;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lote")
    private List<LoteItem> itens = new ArrayList<>();

}
