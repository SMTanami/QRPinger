package com.flight.qrpinger.service.qrgen;

import com.flight.qrpinger.domain.QRCode;
import com.flight.qrpinger.domain.User;
import com.google.zxing.WriterException;

public interface QRService {

    QRCode generate(User user) throws WriterException;

}
