package com.hello.demo.captcha;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.util.Config;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.Properties;

public class CaptchaGenerator {

    private final Producer kaptchaProducer;

    public CaptchaGenerator() {
        Properties properties = new Properties();
        properties.setProperty("kaptcha.image.width", "200");
        properties.setProperty("kaptcha.image.height", "50");
        properties.setProperty("kaptcha.textproducer.char.length", "6");
        properties.setProperty("kaptcha.textproducer.font.color", "black");
        properties.setProperty("kaptcha.textproducer.char.string", "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
        properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
        Config config = new Config(properties);
        kaptchaProducer = config.getProducerImpl();
    }

    public String generateBase64Captcha() throws Exception {
        String text = kaptchaProducer.createText();
        BufferedImage image = kaptchaProducer.createImage(text);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", outputStream);
        byte[] imageBytes = outputStream.toByteArray();

        // Base64编码
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);

        // 可以带上 data URI 头，直接用于 HTML <img src="..." />
        return base64Image;
//        String text = kaptchaProducer.createText();
//        BufferedImage image = kaptchaProducer.createImage(text);
//
//        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
//            // 改成 jpg
//            ImageIO.write(image, "jpg", outputStream);
//            byte[] imageBytes = outputStream.toByteArray();
//            return Base64.getEncoder().encodeToString(imageBytes);
//        }
    }

}

