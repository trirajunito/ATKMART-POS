package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame {

    public Dashboard() {

        setTitle("ATKMart POS");
        setSize(1200,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // PANEL BACKGROUND ANIMASI
        AnimatedBackground bgPanel = new AnimatedBackground();
        bgPanel.setBounds(0,0,1200,700);
        bgPanel.setLayout(null);

        // SIDEBAR
        JPanel sidebar = new JPanel();
        sidebar.setLayout(null);
        sidebar.setBounds(0,0,250,700);
        sidebar.setBackground(new Color(15,23,42));

        // LOGO
        JLabel logo = new JLabel("ATKMart POS");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("SansSerif",Font.BOLD,28));
        logo.setBounds(25,40,220,40);

        JLabel subtitle = new JLabel("Stationery & Office System");
        subtitle.setForeground(new Color(148,163,184));
        subtitle.setFont(new Font("SansSerif",Font.PLAIN,14));
        subtitle.setBounds(28,80,220,20);

        // BUTTON
        JButton btnBarang = createButton("📦  Data Barang ATK");
        btnBarang.setBounds(20,160,210,50);

        JButton btnCustomer = createButton("👤  Customer");
        btnCustomer.setBounds(20,225,210,50);

        JButton btnSupplier = createButton("🚚  Supplier");
        btnSupplier.setBounds(20,290,210,50);

        JButton btnTransaksi = createButton("💳  Transaksi");
        btnTransaksi.setBounds(20,355,210,50);

        JButton btnLaporan = createButton("📊  Laporan Transaksi");
        btnLaporan.setBounds(20,420,210,50);

        // BUTTON INVENTORY
        JButton btnInventory = createButton("📦 Laporan Inventory");
        btnInventory.setBounds(20,485,210,50);

        JButton btnLogout = createButton("🚪  Logout");
        btnLogout.setBounds(20,600,210,50);

        sidebar.add(logo);
        sidebar.add(subtitle);

        sidebar.add(btnBarang);
        sidebar.add(btnCustomer);
        sidebar.add(btnSupplier);
        sidebar.add(btnTransaksi);
        sidebar.add(btnLaporan);
        sidebar.add(btnInventory);
        sidebar.add(btnLogout);

        // CONTENT
        JPanel content = new JPanel();
        content.setLayout(null);
        content.setBounds(250,0,950,700);
        content.setOpaque(false);

        // TITLE
        JLabel title = new JLabel("Dashboard");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif",Font.BOLD,38));
        title.setBounds(50,40,300,40);

        JLabel desc = new JLabel("Welcome back, Admin 👋");
        desc.setForeground(new Color(203,213,225));
        desc.setFont(new Font("SansSerif",Font.PLAIN,18));
        desc.setBounds(50,90,300,30);

        // CARD BARANG
        JPanel card1 = createCard(
                "📚",
                "Data ATK",
                "Kelola stok alat tulis"
        );

        card1.setBounds(50,170,250,160);

        // CARD CUSTOMER
        JPanel card2 = createCard(
                "👤",
                "Customer",
                "Kelola data customer"
        );

        card2.setBounds(350,170,250,160);

        // CARD SUPPLIER
        JPanel card3 = createCard(
                "🚚",
                "Supplier",
                "Kelola data supplier"
        );

        card3.setBounds(650,170,250,160);

        // CARD TRANSAKSI
        JPanel card4 = createCard(
                "🛒",
                "Transaksi",
                "Kelola penjualan barang"
        );

        card4.setBounds(50,360,250,160);

        // CARD INVENTORY
        JPanel card6 = createCard(
                "📦",
                "Inventory",
                "Lihat stok barang"
        );

        card6.setBounds(350,360,250,160);

        // CARD LAPORAN
        JPanel card5 = createCard(
                "📊",
                "Laporan",
                "Lihat laporan penjualan"
        );

        card5.setBounds(650,360,250,160);

        // INFO PANEL
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(null);
        infoPanel.setBackground(new Color(30,41,59));
        infoPanel.setBounds(50,550,850,100);

        JLabel infoTitle = new JLabel("ATKMart POS Dashboard");
        infoTitle.setForeground(Color.WHITE);
        infoTitle.setFont(new Font("SansSerif",Font.BOLD,22));
        infoTitle.setBounds(30,15,400,30);

        JLabel infoDesc = new JLabel("Sistem kasir modern untuk penjualan ATK dan perlengkapan kantor.");
        infoDesc.setForeground(new Color(203,213,225));
        infoDesc.setFont(new Font("SansSerif",Font.PLAIN,15));
        infoDesc.setBounds(30,50,700,20);

        infoPanel.add(infoTitle);
        infoPanel.add(infoDesc);

        // ADD CONTENT
        content.add(title);
        content.add(desc);

        content.add(card1);
        content.add(card2);
        content.add(card3);
        content.add(card4);
        content.add(card5);
        content.add(card6);

        content.add(infoPanel);

        bgPanel.add(sidebar);
        bgPanel.add(content);

        add(bgPanel);

        // ACTION
        btnBarang.addActionListener(e -> {

            new BarangForm();
        });

        btnCustomer.addActionListener(e -> {

            new CustomerForm();
        });

        btnSupplier.addActionListener(e -> {

            new SupplierForm();
        });

        btnTransaksi.addActionListener(e -> {

            new TransaksiForm();
        });

        btnLaporan.addActionListener(e -> {

            new LaporanForm();
        });

        // ACTION INVENTORY
        btnInventory.addActionListener(e -> {

            new LaporanInventoryForm();
        });

        btnLogout.addActionListener(e -> {

            new LoginForm();

            dispose();
        });

        setVisible(true);
    }

    // BUTTON STYLE
    private JButton createButton(String text){

        JButton button = new JButton(text);

        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBackground(new Color(51,65,85));
        button.setForeground(Color.WHITE);

        button.setFont(new Font("SansSerif",Font.PLAIN,16));

        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    // CARD STYLE
    private JPanel createCard(String icon,String title,String desc){

        JPanel panel = new JPanel();
        panel.setLayout(null);

        panel.setBackground(new Color(30,41,59));

        JLabel lblIcon = new JLabel(icon);
        lblIcon.setFont(new Font("SansSerif",Font.PLAIN,40));
        lblIcon.setBounds(20,15,60,50);

        JLabel lblTitle = new JLabel(title);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("SansSerif",Font.BOLD,24));
        lblTitle.setBounds(20,80,200,30);

        JLabel lblDesc = new JLabel(desc);
        lblDesc.setForeground(new Color(203,213,225));
        lblDesc.setFont(new Font("SansSerif",Font.PLAIN,14));
        lblDesc.setBounds(20,115,220,20);

        panel.add(lblIcon);
        panel.add(lblTitle);
        panel.add(lblDesc);

        return panel;
    }

    // BACKGROUND ANIMATION
    class AnimatedBackground extends JPanel implements ActionListener {

        Timer timer;

        int x = 0;

        public AnimatedBackground(){

            timer = new Timer(30,this);
            timer.start();
        }

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g;

            // BACKGROUND GRADIENT
            GradientPaint gp = new GradientPaint(
                    0,0,
                    new Color(15,23,42),
                    getWidth(),getHeight(),
                    new Color(30,41,59)
            );

            g2.setPaint(gp);

            g2.fillRect(0,0,getWidth(),getHeight());

            // ANIMASI BULATAN
            g2.setColor(new Color(59,130,246,80));
            g2.fillOval(x,80,200,200);

            g2.setColor(new Color(147,51,234,60));
            g2.fillOval(800-x,400,250,250);

            g2.setColor(new Color(16,185,129,50));
            g2.fillOval(500,150+x/5,180,180);
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            x += 2;

            if(x > getWidth()){

                x = 0;
            }

            repaint();
        }
    }
}