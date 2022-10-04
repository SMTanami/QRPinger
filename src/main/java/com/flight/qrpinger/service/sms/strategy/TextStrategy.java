package com.flight.qrpinger.service.sms.strategy;

import com.flight.qrpinger.domain.User;

public interface TextStrategy {
    public void sendText(User user);
    public String getStrategyName();
}

