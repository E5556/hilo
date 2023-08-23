import java.awt.*;
import java.io.*;
import java.net.URI;

public class PelotaRebotanteConMusica {

    public static void main(String[] args) {
        Thread animationThread = new Thread(new AnimationRunnable());
        Thread musicThread = new Thread(new MusicRunnable());

        animationThread.start();
        musicThread.start();
    }

    static class AnimationRunnable implements Runnable {
        @Override
        public void run() {
            int ballPosition = 0;
            int ballDirection = 1;

            while (true) {
                clearConsole();
                for (int i = 0; i < ballPosition; i++) {
                    System.out.println();
                }
                System.out.println("   o");
                for (int i = ballPosition + 1; i < 20; i++) {
                    System.out.println();
                }

                ballPosition += ballDirection;

                if (ballPosition <= 0 || ballPosition >= 20) {
                    ballDirection *= -1;
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class MusicRunnable implements Runnable {
        @Override
        public void run() {
            playMusic();
        }

        public void playMusic() {
            try {
                Desktop desktop = Desktop.getDesktop();
                File audioFile = new File("cancion.wav");

                desktop.open(audioFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
