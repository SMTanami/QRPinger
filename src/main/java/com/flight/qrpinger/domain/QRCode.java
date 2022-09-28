package com.flight.qrpinger.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;

@Getter
@Setter
@AllArgsConstructor
public class QRCode {
    private String fileName;
    private BufferedImage img;
    private String filepath;
}
