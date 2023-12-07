import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;

public class Project {

    private static boolean isAdminConnected = false;
    private static boolean isOtherConnected = false;
    private static JLabel favorite;
    private static JLabel create;
    private static JPanel leftPanel;
    private static JPanel createpanel;
    private static JPanel favoritePanel;

    public Project() {

        JFrame frame = new JFrame();
        frame.setTitle("ARCHIVES");
        ImageIcon graduationIcon = new ImageIcon("/Users/no/IHM/ProjetIHM/src/asset/graduation.png");
        Image resizedIcon = graduationIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedGraduationIcon = new ImageIcon(resizedIcon);

        JLabel label = new JLabel(
                "<html> Welcome To UniMemoire<font color='black' size='16'> <br/> Graduation</font></html>",
                resizedGraduationIcon, JLabel.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Roboto", Font.ITALIC, 20));
        label.setOpaque(false);

        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon adminIcon = new ImageIcon(new ImageIcon("/Users/no/IHM/ProjetIHM/src/asset/admin.png").getImage()
                .getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        ImageIcon searchIcon = new ImageIcon(new ImageIcon("/Users/no/IHM/ProjetIHM/src/asset/search.png").getImage()
                .getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        ImageIcon createIcon = new ImageIcon(new ImageIcon("/Users/no/IHM/ProjetIHM/src/asset/add.png").getImage()
                .getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        ImageIcon lectureIcon = new ImageIcon(new ImageIcon("/Users/no/IHM/ProjetIHM/src/asset/lecture.png").getImage()
                .getScaledInstance(30, 30, Image.SCALE_SMOOTH));

        ImageIcon otherIcon = new ImageIcon(new ImageIcon("/Users/no/IHM/ProjetIHM/src/asset/network.png").getImage()
                .getScaledInstance(30, 30, Image.SCALE_SMOOTH));

        JLabel admin = new JLabel("Administration", adminIcon, JLabel.LEFT);
        admin.setForeground(Color.WHITE);
        admin.setFont(new Font("Roboto", Font.BOLD, 15));
        admin.setBorder(BorderFactory.createEmptyBorder(40, 30, 10, 10));

        create = new JLabel("Creation", createIcon, JLabel.LEFT);
        create.setFont(new Font("Roboto", Font.BOLD, 15));
        create.setForeground(Color.WHITE);
        create.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 10));
        create.setVisible(isAdminConnected);

        JLabel lecture = new JLabel("Lecture", lectureIcon, JLabel.LEFT);
        lecture.setFont(new Font("Roboto", Font.BOLD, 15));
        lecture.setForeground(Color.WHITE);
        lecture.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 10));

        JLabel search = new JLabel("Search", searchIcon, JLabel.LEFT);
        search.setForeground(Color.WHITE);
        search.setFont(new Font("Roboto", Font.BOLD, 15));
        search.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 10));

        JLabel other = new JLabel("other", otherIcon, JLabel.LEFT);
        other.setForeground(Color.WHITE);
        other.setFont(new Font("Roboto", Font.BOLD, 15));
        other.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 10));

        favorite = new JLabel("favorite", otherIcon, JLabel.LEFT);
        favorite.setForeground(Color.WHITE);
        favorite.setFont(new Font("Roboto", Font.BOLD, 15));
        favorite.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 10));
        favorite.setVisible(isOtherConnected);

        leftPanel.add(label);
        leftPanel.add(admin);
        leftPanel.add(other);
        leftPanel.add(search);
        leftPanel.add(lecture);
        if (isAdminConnected = true) {
            leftPanel.add(create);
        }
        leftPanel.add(favorite);
        leftPanel.setBackground(new Color(51, 40, 102));

        JPanel rightPanel = new JPanel(new CardLayout());
        rightPanel.add(createSearchPanel(), "search");
        rightPanel.add(createCreatePanel(), "create");
        rightPanel.add(createAdminPanel(), "admin");
        rightPanel.add(createLecturePanel(), "lecture");
        rightPanel.add(createotherPanel(), "other");
        rightPanel.add(createfavoritePanel(), "favorite");

        CardLayout cardLayout = (CardLayout) rightPanel.getLayout();

        admin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(rightPanel, "admin");
            }
        });

        search.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(rightPanel, "search");
            }
        });

        create.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(rightPanel, "create");
            }
        });
        other.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(rightPanel, "other");
            }
        });

        lecture.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(rightPanel, "lecture");
            }
        });
        favorite.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(rightPanel, "favorite");
            }
        });

        // ################################### LA FENETRE
        // ###############################################

        frame.setLayout(new BorderLayout());
        frame.add(rightPanel, BorderLayout.CENTER);
        frame.add(leftPanel, BorderLayout.WEST);

        frame.setSize(1000, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel(new BorderLayout());
        JPanel searchp = new JPanel(new FlowLayout());
        searchPanel.setBackground(Color.WHITE);

        JLabel label2 = new JLabel("Here you can find the thesis archives");
        label2.setFont(new Font("Roboto", Font.ITALIC, 20));
        label2.setBorder(BorderFactory.createEmptyBorder(5, 140, 5, 0));

        JTextField searchBar = new PlaceholderTextField("Search by code, title, or framer", 40);
        searchBar.setPreferredSize(new Dimension(searchBar.getPreferredSize().width, 50));

        JLabel label3 = new JLabel(
                "<html> <font size='8'>Search</font>  <br/> You can search by code, title, or framer. </html>");
        label3.setForeground(Color.WHITE);
        label3.setBorder(BorderFactory.createEmptyBorder(25, 36, 25, 10));
        label3.setOpaque(true);
        label3.setBackground(new Color(51, 40, 102));

        JButton searchButton = new JButton("Search");

        Dimension buttonSize = new Dimension(searchButton.getPreferredSize().width,
                searchBar.getPreferredSize().height);
        searchButton.setPreferredSize(buttonSize);

        JPanel top = new JPanel(new GridLayout(2, 1, 5, 5));
        top.add(label2, BorderLayout.PAGE_START);
        top.add(label3, BorderLayout.CENTER);

        searchPanel.add(top, BorderLayout.PAGE_START);

        searchp.add(searchBar);
        searchp.add(searchButton);

        searchPanel.add(searchp, BorderLayout.CENTER);

        return searchPanel;
    }

    private static JPanel createotherPanel() {
        JPanel otherpanel = new JPanel(new BorderLayout());
        JPanel otherp = new JPanel(new FlowLayout());
        otherp.setBorder(BorderFactory.createEmptyBorder(55, 0, 5, 0));
        otherpanel.setBackground(Color.WHITE);

        JLabel label2 = new JLabel("Here you can find the thesis archives ");
        label2.setFont(new Font("Roboto", Font.ITALIC, 20));
        label2.setBorder(BorderFactory.createEmptyBorder(5, 140, 5, 0));

        JTextField log = new JTextField(40);
        JPasswordField password = new JPasswordField(40); // Adjusted width
        JLabel label3 = new JLabel(
                "<html> <font size='8'>Other's Acces</font>  <br/> others access. </html>");
        label3.setForeground(Color.WHITE);
        label3.setBorder(BorderFactory.createEmptyBorder(25, 36, 25, 10));
        label3.setOpaque(true);
        label3.setBackground(new Color(51, 40, 102));

        log.setPreferredSize(new Dimension(log.getPreferredSize().width, 60));
        password.setPreferredSize(new Dimension(password.getPreferredSize().width, 60));

        JButton connect = new JButton("Connect");
        connect.setPreferredSize(new Dimension(250, log.getPreferredSize().height));

        connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = log.getText();
                String enteredPassword = new String(password.getPassword());

                // a modifié apres l'ajoute de la base de donnee
                if (username.equals("soundous") && enteredPassword.equals("snds")) {
                    isOtherConnected = true;
                    SwingUtilities.invokeLater(() -> favorite.setVisible(true)); // Show the "Creation" label
                    System.out.println("welcome ");
                } else {
                    isOtherConnected = false;
                    SwingUtilities.invokeLater(() -> favorite.setVisible(false)); // Hide the "Creation" label
                    System.out.println("invalid account");
                }
            }
        });

        JPanel top = new JPanel(new GridLayout(2, 1, 5, 5));
        top.add(label2, BorderLayout.PAGE_START);
        top.add(label3, BorderLayout.CENTER);

        otherpanel.add(top, BorderLayout.PAGE_START);

        otherp.add(log);
        otherp.add(password);
        otherp.add(connect);
        otherpanel.add(otherp);

        return otherpanel;
    }

    private static JPanel createCreatePanel() {
        createpanel = new JPanel(new BorderLayout());
        JPanel adminp = new JPanel(new FlowLayout());
        adminp.setBorder(BorderFactory.createEmptyBorder(55, 0, 5, 0));
        createpanel.setBackground(Color.WHITE);

        JLabel label2 = new JLabel("Here you can find the thesis archives ");
        label2.setFont(new Font("Roboto", Font.ITALIC, 20));
        label2.setBorder(BorderFactory.createEmptyBorder(5, 140, 5, 0));

        JTextField title = new JTextField(40);
        JTextField frame = new JTextField(40);

        JFormattedTextField dateField = createDateTextField();

        JTextField code = new JTextField(40);

        JTextField resumePathField = new JTextField(33);
        resumePathField.setEditable(false);

        JButton resumeBrowseButton = new JButton("Add pdf");
        resumeBrowseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(createpanel);

                if (result == JFileChooser.APPROVE_OPTION) {
                    resumePathField.setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
            }
        });

        JLabel label3 = new JLabel(
                "<html> <font size='8'>Creation</font> <br/> You can add thesis by entering the title, framer, date of thesis, code, and resume. </html>");
        label3.setForeground(Color.WHITE);
        label3.setBorder(BorderFactory.createEmptyBorder(25, 36, 25, 10));
        label3.setOpaque(true);
        label3.setBackground(new Color(51, 40, 102));

        title.setPreferredSize(new Dimension(title.getPreferredSize().width, 60));
        frame.setPreferredSize(new Dimension(frame.getPreferredSize().width, 60));
        dateField.setPreferredSize(new Dimension(frame.getPreferredSize().width,
                60));
        code.setPreferredSize(new Dimension(frame.getPreferredSize().width, 60));
        resumePathField.setPreferredSize(new Dimension(frame.getPreferredSize().width
                - 100, 60));
        resumeBrowseButton.setPreferredSize(new Dimension(80, 60));

        JButton Add = new JButton("Add");
        Add.setPreferredSize(new Dimension(250, title.getPreferredSize().height));

        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (title.getText().isEmpty() || frame.getText().isEmpty() ||
                        dateField.getText().isEmpty()
                        || code.getText().isEmpty() || resumePathField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(createpanel, "Please enter data in all fields",
                            "Incomplete Data",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    // à modifier apres l'ajoute de bd
                    System.out.println("Title: " + title.getText());
                    System.out.println("Frame: " + frame.getText());
                    System.out.println("Date: " + dateField.getText());
                    System.out.println("Code: " + code.getText());
                    System.out.println("Resume Path: " + resumePathField.getText());
                }
            }
        });

        JPanel top = new JPanel(new GridLayout(2, 1, 5, 5));
        top.add(label2, BorderLayout.PAGE_START);
        top.add(label3, BorderLayout.CENTER);

        createpanel.add(top, BorderLayout.PAGE_START);

        adminp.add(createFieldPanel("Title:", title));
        adminp.add(createFieldPanel("Frame:", frame));
        adminp.add(createFieldPanel("Date :", dateField));
        adminp.add(createFieldPanel("Code:", code));
        adminp.add(createFieldPanel("Resume:", resumePathField));
        adminp.add(resumeBrowseButton);
        adminp.add(Add);
        createpanel.add(adminp);

        return createpanel;
    }

    private static JPanel createFieldPanel(String label, JComponent component) {
        JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 5));
        JLabel labelComponent = new JLabel(label);
        labelComponent.setPreferredSize(new Dimension(60, 30));
        fieldPanel.add(labelComponent);
        fieldPanel.add(component);
        return fieldPanel;
    }

    private static JFormattedTextField createDateTextField() {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter("##/##/####");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JFormattedTextField dateField = new JFormattedTextField(formatter);
        return dateField;
    }

    private static JPanel createLecturePanel() {
        JPanel lecturePanel = new JPanel(new BorderLayout());
        JPanel lecturep = new JPanel(new FlowLayout());
        lecturep.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));
        lecturePanel.setBackground(Color.WHITE);

        JLabel label2 = new JLabel("Here you can find the thesis archives ");
        label2.setFont(new Font("Roboto", Font.ITALIC, 20));
        label2.setBorder(BorderFactory.createEmptyBorder(5, 140, 5, 0));

        JLabel label3 = new JLabel(
                "<html> <font size='8'>Lecture</font>  <br/> here you find the list of thesis . </html>");
        label3.setForeground(Color.WHITE);
        label3.setBorder(BorderFactory.createEmptyBorder(25, 36, 25, 10));
        label3.setOpaque(true);
        label3.setBackground(new Color(51, 40, 102));

        JComboBox<String> framerComboBox = new JComboBox<>(new String[] { "", "Mr.Rezoug", "Mr.Gaceb", "Mr.Mokrani" });
        JComboBox<Integer> yearComboBox = new JComboBox<>(new Integer[] { null, 2000, 2001, 2002, 2003, 2004, 2005,
                2006,
                2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023 });
        JComboBox<String> levelComboBox = new JComboBox<>(new String[] { "", "licence", "master", "doctorat" });

        JPanel top = new JPanel(new GridLayout(2, 1, 5, 5));
        top.add(label2, BorderLayout.PAGE_START);
        top.add(label3, BorderLayout.CENTER);

        // JTabbedPane onglets = new JTabbedPane();

        lecturePanel.add(top, BorderLayout.PAGE_START);

        lecturep.add(createFieldPanel("Framer:", framerComboBox));
        lecturep.add(createFieldPanel("Year:", yearComboBox));
        lecturep.add(createFieldPanel("Level:", levelComboBox));

        lecturePanel.add(lecturep);

        return lecturePanel;
    }

    private static JPanel createfavoritePanel() {
        favoritePanel = new JPanel(new BorderLayout());
        JPanel favpanel = new JPanel(new FlowLayout());
        favpanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));
        favoritePanel.setBackground(Color.WHITE);

        JLabel label2 = new JLabel("Here you can find the thesis archives ");
        label2.setFont(new Font("Roboto", Font.ITALIC, 20));
        label2.setBorder(BorderFactory.createEmptyBorder(5, 140, 5, 0));

        JLabel label3 = new JLabel(
                "<html> <font size='8'>Favorite List</font>  <br/> here you find your favorite list . </html>");
        label3.setForeground(Color.WHITE);
        label3.setBorder(BorderFactory.createEmptyBorder(25, 36, 25, 10));
        label3.setOpaque(true);
        label3.setBackground(new Color(51, 40, 102));

        JLabel label = new JLabel("favorite list");
        JPanel top = new JPanel(new GridLayout(2, 1, 5, 5));
        top.add(label2, BorderLayout.PAGE_START);
        top.add(label3, BorderLayout.CENTER);
        favoritePanel.add(top, BorderLayout.PAGE_START);
        favpanel.add(createFieldPanel("favorite:", label));
        favoritePanel.add(favpanel);

        return favoritePanel;
    }

    private static JPanel createAdminPanel() {
        JPanel adminpanel = new JPanel(new BorderLayout());
        JPanel adminp = new JPanel(new FlowLayout());
        adminp.setBorder(BorderFactory.createEmptyBorder(55, 0, 5, 0));
        adminpanel.setBackground(Color.WHITE);

        JLabel label2 = new JLabel("Here you can find the thesis archives ");
        label2.setFont(new Font("Roboto", Font.ITALIC, 20));
        label2.setBorder(BorderFactory.createEmptyBorder(5, 140, 5, 0));

        JTextField log = new JTextField(40);
        JPasswordField password = new JPasswordField(40); // Adjusted width
        JLabel label3 = new JLabel(
                "<html> <font size='8'>Administration</font>  <br/> Administration access. </html>");
        label3.setForeground(Color.WHITE);
        label3.setBorder(BorderFactory.createEmptyBorder(25, 36, 25, 10));
        label3.setOpaque(true);
        label3.setBackground(new Color(51, 40, 102));

        log.setPreferredSize(new Dimension(log.getPreferredSize().width, 60));
        password.setPreferredSize(new Dimension(password.getPreferredSize().width, 60));

        JButton connect = new JButton("Connect");
        connect.setPreferredSize(new Dimension(250, log.getPreferredSize().height));

        JPanel top = new JPanel(new GridLayout(2, 1, 5, 5));
        top.add(label2, BorderLayout.PAGE_START);
        top.add(label3, BorderLayout.CENTER);

        adminpanel.add(top, BorderLayout.PAGE_START);

        JLabel hey = new JLabel("you are connected");
        hey.setHorizontalAlignment(SwingConstants.CENTER);
        JButton deconnect = new JButton("Deconnect");
        deconnect.setPreferredSize(new Dimension(250, log.getPreferredSize().height));

        connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = log.getText();
                String enteredPassword = new String(password.getPassword());

                // a modifié après l'ajoute de la base de donnee
                if (username.equals("soundous") && enteredPassword.equals("snds")) {
                    isAdminConnected = true;
                    SwingUtilities.invokeLater(() -> {
                        create.setVisible(true);
                        adminpanel.remove(adminp); // Remove login panel
                        adminpanel.add(hey, BorderLayout.CENTER); // Add connected label
                        adminpanel.add(deconnect);
                        adminpanel.revalidate();
                        adminpanel.repaint();
                    });
                    System.out.println("welcome ");
                } else {
                    isAdminConnected = false;
                    SwingUtilities.invokeLater(() -> {
                        create.setVisible(false);
                        adminpanel.remove(hey); // Remove connected label
                        adminpanel.remove(deconnect);
                        adminpanel.add(adminp, BorderLayout.CENTER); // Add login panel
                        adminpanel.revalidate();
                        adminpanel.repaint();
                    });
                    System.out.println("invalid account");
                }
            }
        });
        deconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isAdminConnected = false;
                SwingUtilities.invokeLater(() -> {
                    leftPanel.remove(create); // Remove "Creation" label from left panel
                    adminpanel.remove(hey); // Remove connected label
                    adminpanel.remove(deconnect);
                    adminpanel.add(adminp, BorderLayout.CENTER); // Add login panel
                    adminpanel.revalidate();
                    adminpanel.repaint();
                });
            }
        });

        adminp.add(log);
        adminp.add(password);
        adminp.add(connect);
        adminpanel.add(adminp);

        return adminpanel;
    }

    private static class PlaceholderTextField extends JTextField implements FocusListener {
        private final String placeholder;

        PlaceholderTextField(String placeholder, int columns) {
            super(columns);
            this.placeholder = placeholder;
            addFocusListener(this);
            setPlaceholder();
        }

        private void setPlaceholder() {
            if (getText().isEmpty()) {
                setText(placeholder);
                setForeground(Color.GRAY);
            }
        }

        private void movePlaceholder() {
            setText("");
            setForeground(Color.BLACK);
        }

        @Override
        public void focusGained(FocusEvent e) {
            movePlaceholder();
        }

        @Override
        public void focusLost(FocusEvent e) {
            setPlaceholder();
        }
    }

    private static class PlaceholderPasswordField extends JPasswordField implements FocusListener {
        private final String placeholder;

        PlaceholderPasswordField(String placeholder, int columns) {
            super(columns);
            this.placeholder = placeholder;
            addFocusListener(this);
            setPlaceholder();
        }

        private void setPlaceholder() {
            if (getPassword().length == 0) {
                setText(placeholder);
                setForeground(Color.GRAY);
            }
        }

        private void movePlaceholder() {
            setText("");
            setForeground(Color.BLACK);
        }

        @Override
        public void focusGained(FocusEvent e) {
            movePlaceholder();
        }

        @Override
        public void focusLost(FocusEvent e) {
            setPlaceholder();
        }
    }

    public static void main(String[] args) {
        new Project();
    }
}