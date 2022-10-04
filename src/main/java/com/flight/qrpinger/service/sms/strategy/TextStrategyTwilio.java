package com.flight.qrpinger.service.sms.strategy;

import com.flight.qrpinger.domain.User;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Log
@Component("TextStrategyTwilio")
public class TextStrategyTwilio implements TextStrategy {
    private static final String FROM = System.getenv("FROM_NUM");
    private boolean initialized;

    @Value("${text.strategy.twilio.sid}")
    private String twilioSID;

    @Value("${text.strategy.twilio.token}")
    private String twilioToken;

    private synchronized void init() {
        if (!initialized) {
            Twilio.init(twilioSID, twilioToken);
            initialized=true;
        }
    }
    
    @Override
    public String getStrategyName() {
        return "TextStrategyTwilio";
    }

    @Override
    public void sendText(User user) {
        if (!initialized)
            init();
        Message.creator(
                        new PhoneNumber(user.getPhoneNumber()),
                        new PhoneNumber(FROM),
                        "You've been pinged!")
                .create();
    }
}
