package view;

import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JFrame {

    JProgressBar progressBar = new JProgressBar();

    public SplashScreen() {

        setTitle("ATKMART POS");
        setSize(700,400);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setLayout(null);

        // BACKGROUND
        getContentPane().setBackground(new Color(15,23,42));

        // ICON
        JLabel icon = new JLabel("🛒");
        icon.setFont(new Font("SansSerif",Font.PLAIN,60));
        icon.setBounds(310,40,100,80);

        // TITLE
        JLabel title = new JLabel("ATKMART POS");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif",Font.BOLD,40));
        title.setBounds(220,120,300,50);

        // SUBTITLE
        JLabel subtitle = new JLabel("Stationery & Office System");
        subtitle.setForeground(new Color(148,163,184));
        subtitle.setFont(new Font("SansSerif",Font.PLAIN,18));
        subtitle.setBounds(240,175,250,30);

        // LOADING TEXT
        JLabel loading = new JLabel("Loading application...");
        loading.setForeground(Color.WHITE);
        loading.setFont(new Font("SansSerif",Font.PLAIN,14));
        loading.setBounds(270,280,200,20);

        // PROGRESS BAR
        progressBar.setBounds(100,320,500,15);
        progressBar.setBorderPainted(false);
        progressBar.setBackground(new Color(51,65,85));
        progressBar.setForeground(new Color(59,130,246));

        add(icon);
        add(title);
        add(subtitle);
        add(loading);
        add(progressBar);

        setVisible(true);

        loadingAnimation();
    }

    private void loadingAnimation(){

        new Thread(() -> {

            try {

                for(int i = 0; i <= 100; i++){

                    progressBar.setValue(i);

                    Thread.sleep(30);
                }

                new LoginForm();

                dispose();

            } catch (Exception e) {

                System.out.println(e.getMessage());
            }

        }).start();
    }
}