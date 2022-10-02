package com.flight.qrpinger.domain;

import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

@Getter
@Setter
public class QRCode {

    private final BitMatrix matrix;
    private final User user;
    private final Path path;

    public QRCode(BitMatrix matrix, User user) {
        this.matrix = matrix;
        this.user = user;
        this.path = Path.of(System.getProperty("java.io.tmpdir") + "\\" + user.getId());
    }

    public File toFile() throws IOException {
        MatrixToImageWriter.writeToPath(matrix, "png", path);
        return path.toFile();
    }

    public boolean deleteFile() throws IOException {
        return Files.deleteIfExists(path);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QRCode qrCode = (QRCode) o;
        return matrix.equals(qrCode.matrix) && user.equals(qrCode.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matrix, user);
    }
}
