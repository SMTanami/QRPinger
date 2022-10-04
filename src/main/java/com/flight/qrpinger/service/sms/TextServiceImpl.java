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
    private TextStrategyFactory strategyFactory;

    private TextStrategy strategy;

    @Value("${text.strategy}")
    private String textStrategyConfigVal;

    public TextServiceImpl() {
        log.info("TextServiceImpl()");
    }

    @Override
    public void sendText(User user) {
        if (strategy==null)
            this.strategy = strategyFactory.findStrategy(textStrategyConfigVal);

        this.strategy.sendText(user);
        log.info("Successfully pinged " + user);
    }
}
