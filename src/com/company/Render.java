package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Render {

    public static void render(BufferedImage img) {
        Render.renderTrngl(200, 200, 200, 100, 271, 129, img, new Color(300).getRGB());
        Render.renderTrngl(200, 200, 271, 129, 300, 200, img, new Color(500).getRGB());
        Render.renderTrngl(200, 200, 271, 271, 300, 200, img, new Color(700).getRGB());
        Render.renderTrngl(200, 200, 271, 271, 200, 300, img, new Color(900).getRGB());
        Render.renderTrngl(200, 200, 200, 100, 129, 129, img, new Color(600).getRGB());
        Render.renderTrngl(200, 200, 129, 129, 100, 200, img, new Color(500).getRGB());
        Render.renderTrngl(200, 200, 129, 271, 100, 200, img, new Color(700).getRGB());
        Render.renderTrngl(200, 200, 129, 271, 200, 300, img, new Color(900).getRGB());
    }

    static void renderTrngl(int x1, int y1, int x2, int y2, int x3, int y3, BufferedImage img, int color) {
        for (int i = Math.min(x1, Math.min(x2, x3)); i < Math.max(x1, Math.max(x2, x3)); i++) {
            for (int j = Math.min(y1, Math.min(y2, y3)); j < Math.max(y1, Math.max(y2, y3)); j++) {
                int p1 = (x1 - i) * (y2 - y1) - (x2 - x1) * (y1 - j);
                int p2 = (x2 - i) * (y3 - y2) - (x3 - x2) * (y2 - j);
                int p3 = (x3 - i) * (y1 - y3) - (x1 - x3) * (y3 - j);
                if (p1 > 0) {
                    p1 = 1;
                } else {
                    if (p1 < 0) {
                        p1 = -1;
                    }
                }
                if (p2 > 0) {
                    p2 = 1;
                } else {
                    if (p2 < 0) {
                        p2 = -1;

                    }
                }
                if (p3 > 0) {
                    p3 = 1;
                } else {
                    if (p3 < 0) {
                        p3 = -1;
                    }
                }
                if (p1 == p2 && p2 == p3){
                    img.setRGB(i, j, color);
                }
            }
        }
    }

            //Стоит начать с этого
    static void renderLineUnsafe ( int x1, int y1, int x2, int y2, int rgb, BufferedImage img){
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        if (dx >= dy) {
            if (x2 < x1) {
                int tx = x1;
                x1 = x2;
                x2 = tx;
                int ty = y1;
                y1 = y2;
                y2 = ty;
            }
            int dirY = (int) Math.signum(y2 - y1);
            int error = 0;
            int dError = dy;
            int y = y1;
            for (int x = x1; x <= x2; x++) {
                img.setRGB(x, y, rgb);
                error += dError;
                if ((error << 1) >= dx) {
                    y += dirY;
                    error -= dx;
                }
            }
        } else {
            if (y2 < y1) {
                int tx = x1;
                x1 = x2;
                x2 = tx;
                int ty = y1;
                y1 = y2;
                y2 = ty;
            }
            int dirX = (int) Math.signum(x2 - x1);
            int error = 0;
            int dError = dx;
            int x = x1;
            for (int y = y1; y <= y2; y++) {
                img.setRGB(x, y, rgb);
                error += dError;
                if (error << 1 >= dy) {
                    x += dirX;
                    error -= dy;
                }
            }
        }
    }
}


