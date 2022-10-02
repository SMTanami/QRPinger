package com.flight.qrpinger.service.sms;

import com.flight.qrpinger.domain.User;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class TextServiceImpl implements TextService {

    private static final String FROM = System.getenv("FROM_NUM");
    private final Logger logger;

    public TextServiceImpl() {
        this.logger = LogManager.getLogger(TextServiceImpl.class);
        Twilio.init(System.getenv("TWILIO_SID"), System.getenv("TWILIO_TOKEN"));
    }

    @Override
    public void sendText(User user) {
        Message.creator(
                        new PhoneNumber(user.getPhoneNumber()),
                        new PhoneNumber(FROM),
                        "You've been pinged!")
                .create();

        logger.log(Level.INFO, "Successfully pinged " + user);
    }
}
