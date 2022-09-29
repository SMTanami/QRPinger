package com.flight.qrpinger.domain;

import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class QRCode {

    private String fileName;
    private BitMatrix matrix;
    private String filepath;
    private String data;


    public BufferedImage toImage() {
        return MatrixToImageWriter.toBufferedImage(matrix);
    }

}
