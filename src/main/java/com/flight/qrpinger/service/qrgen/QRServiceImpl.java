package com.flight.qrpinger.service.qrgen;

import com.flight.qrpinger.domain.QRCode;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;

@NoArgsConstructor
@Service
public class QRServiceImpl implements QRService{

    private final MultiFormatWriter writer = new MultiFormatWriter();
    private String data = "http://localhost:8080/user/";
    private String path = "C:\\Users\\Michael\\Repos\\Java\\Misc\\QR-codes\\";

    @Override
    public QRCode generate(Long id, String userLastName) throws WriterException, IOException {
        String path = this.path + userLastName + "-" + id;
        BitMatrix matrix = writer.encode(data + id.toString(), BarcodeFormat.QR_CODE, 500, 500);
        MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(path));
        return new QRCode("Your Code.jpg", matrix, path);
    }
}
