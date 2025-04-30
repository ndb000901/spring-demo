package com.hello.demo.captcha;

public class Main {


    public static void main(String[] args) throws Exception {
        CaptchaGenerator generator = new CaptchaGenerator();
        long start = System.currentTimeMillis();
        String base64Captcha = "";
        for (int i = 0; i < 1000; i++) {
            base64Captcha = generator.generateBase64Captcha();

        }
        System.out.println("time: " + (System.currentTimeMillis() - start));
        System.out.println(base64Captcha);
    }
}