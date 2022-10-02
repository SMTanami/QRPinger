package com.flight.qrpinger.service.sms;

import com.flight.qrpinger.domain.User;

public interface TextService {
    void sendText(User user);
}
