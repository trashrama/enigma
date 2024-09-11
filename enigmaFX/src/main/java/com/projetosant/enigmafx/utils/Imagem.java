package com.projetosant.enigmafx.utils;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Imagem {

    public static WritableImage bytesToImg(byte[] img) {
        try{
            ByteArrayInputStream by = new ByteArrayInputStream(img);
            return SwingFXUtils.toFXImage(ImageIO.read(by), null);

        } catch (NullPointerException | IOException np) {
            return null;

        }

    }

    public static byte[] imgToBytes(File fileImg) throws IOException {

        if (fileImg != null){
            return Files.readAllBytes(fileImg.toPath());
        }
        return null;
    }
}
