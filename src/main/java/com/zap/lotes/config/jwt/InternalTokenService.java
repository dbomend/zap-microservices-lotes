package com.zap.lotes.config.jwt;

import com.zap.lotes.config.jwt.InternalTokenCreator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class InternalTokenService {

    private final InternalTokenCreator tokenCreator;

    /**
     * Responsável por gerar um token de uso interno
     *
     * @return token utilizado entre os microserviços
     */
    public String getBearer() {
        return "Bearer " + tokenCreator.generateInternalToken();
    }

}
