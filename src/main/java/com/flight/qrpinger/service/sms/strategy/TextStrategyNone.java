package com.flight.qrpinger.service.sms.strategy;
import com.flight.qrpinger.domain.User;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;


@Component("StrategyNone")
@Log
public class TextStrategyNone implements TextStrategy {
    @Override
    public void init() {}
    @Override
    public void sendText(User user) {
        log.warning("Sending fake message for user: "+user.toString());
    }

    @Override
    public StrategyName getStrategyName() {
        return StrategyName.StrategyNone;
    }

}
