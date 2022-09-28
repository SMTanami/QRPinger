package com.flight.qrpinger.service.sms;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public class TextServiceImpl implements TextService {

    private final String from = "+12053902659";

    public TextServiceImpl() {
        Twilio.init(System.getenv("TWILIO_SID"), System.getenv("TWILIO_TOKEN"));
    }

    @Override
    public void sendText(String to) {
        Message message = Message.creator(
                        new PhoneNumber(to),
                        new PhoneNumber(from),
                        "You've been pinged!")
                .create();
    }

}
