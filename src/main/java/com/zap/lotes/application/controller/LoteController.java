package com.zap.lotes.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize("hasAnyRole('ADMIN','API','INTERNAL')")
@RestController
@RequestMapping("/v1/lote")
@Validated
@RequiredArgsConstructor
public class LoteController {

}
