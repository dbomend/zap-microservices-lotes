package com.zap.lotes.application.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tb_cad_contato")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "cpf_cnpj")
    private String taxId;

    @Column(name = "ddd")
    private Integer phoneArea;

    @Column(name = "telefone")
    private Long phoneNumber;

}
