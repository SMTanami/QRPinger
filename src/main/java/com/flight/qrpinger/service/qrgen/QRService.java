package com.flight.qrpinger.service.qrgen;

import com.flight.qrpinger.domain.QRCode;
import com.google.zxing.WriterException;

import java.io.IOException;

public interface QRService {

    QRCode generate(Long id) throws WriterException;

}
