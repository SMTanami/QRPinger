package com.flight.qrpinger.service.qrgen;

import com.flight.qrpinger.domain.QRCode;
import com.flight.qrpinger.domain.User;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;


@Service
public class QRServiceImpl implements QRService {

    private static final String QR_DATA = System.getenv("QR_DATA");

    private final Logger logger;
    private final MultiFormatWriter writer;

    public QRServiceImpl() {
        this.writer = new MultiFormatWriter();
        this.logger = LogManager.getLogger(QRServiceImpl.class);
    }

    @Override
    public QRCode generate(User user) throws WriterException {
        BitMatrix matrix = writer.encode(QR_DATA + user.toString(), BarcodeFormat.QR_CODE, 500, 500);
        logger.log(Level.INFO, "QR Code successfully generated for user " + user);
        return new QRCode(matrix, user);
    }
}
