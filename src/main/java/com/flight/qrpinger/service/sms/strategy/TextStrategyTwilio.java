package com.flight.qrpinger.service.sms.strategy;

import com.flight.qrpinger.domain.User;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

@Log
@Component("StrategyTwilio")
public class TextStrategyTwilio implements TextStrategy {
    private static final String FROM = System.getenv("FROM_NUM");

    @Override
    public void init() {
        Twilio.init(System.getenv("TWILIO_SID"), System.getenv("TWILIO_TOKEN"));
        log.info("Init Twilio");
    }

    
    @Override
    public StrategyName getStrategyName() {
        return StrategyName.StrategyNone;
    }

    @Override
    public void sendText(User user) {
        Message.creator(
                        new PhoneNumber(user.getPhoneNumber()),
                        new PhoneNumber(FROM),
                        "You've been pinged!")
                .create();
    }
}
