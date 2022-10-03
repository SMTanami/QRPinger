package com.flight.qrpinger.service.sms;

import com.flight.qrpinger.domain.User;
import com.flight.qrpinger.service.sms.strategy.*;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Log
@Service
public class TextServiceImpl implements TextService {
    @Autowired
    private StrategyFactory strategyFactory;

    private TextStrategy strategy;

    @Value("${sms.strategy}")
    private String strategyNameFromConfig;

    @Autowired
    public TextServiceImpl() {
    }

    @Override
    public void sendText(User user) {
        if (strategy==null) {
            this.strategy = strategyFactory.findStrategy(strategyNameFromConfig);
        }

        this.strategy.sendText(user);
        log.info("Successfully pinged " + user);
    }
}
