import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Project {

    public static void main(String[] args) {
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

        JPanel leftPanel = new JPanel();
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

        JLabel admin = new JLabel("Administration", adminIcon, JLabel.LEFT);
        admin.setForeground(Color.WHITE);
        admin.setFont(new Font("Roboto", Font.BOLD, 15));
        admin.setBorder(BorderFactory.createEmptyBorder(40, 30, 10, 10));

        JLabel create = new JLabel("Creation", createIcon, JLabel.LEFT);
        create.setFont(new Font("Roboto", Font.BOLD, 15));
        create.setForeground(Color.WHITE);
        create.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 10));

        JLabel lecture = new JLabel("Lecture", lectureIcon, JLabel.LEFT);
        lecture.setFont(new Font("Roboto", Font.BOLD, 15));
        lecture.setForeground(Color.WHITE);
        lecture.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 10));

        JLabel search = new JLabel("Search", searchIcon, JLabel.LEFT);
        search.setForeground(Color.WHITE);
        search.setFont(new Font("Roboto", Font.BOLD, 15));
        search.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 10));

        leftPanel.add(label);
        leftPanel.add(admin);
        leftPanel.add(search);
        leftPanel.add(create);
        leftPanel.add(lecture);
        leftPanel.setBackground(new Color(51, 40, 102));

        JPanel rightPanel = new JPanel(new CardLayout());
        rightPanel.add(createSearchPanel(), "search"); // Search panel as the default panel
        rightPanel.add(createCreatePanel(), "create");
        rightPanel.add(createAdminPanel(), "admin");
        rightPanel.add(createLecturePanel(), "lecture");

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

        lecture.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(rightPanel, "lecture");
            }
        });

        frame.setLayout(new BorderLayout());
        frame.add(rightPanel, BorderLayout.CENTER);
        frame.add(leftPanel, BorderLayout.WEST);

        frame.setSize(1000, 700);
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

        JTextField searchBar = new JTextField(40);

        JLabel label3 = new JLabel(
                "<html> <font size='8'>Search</font>  <br/> You can search by code, title, or framer. </html>");
        label3.setForeground(Color.WHITE);
        label3.setBorder(BorderFactory.createEmptyBorder(25, 36, 25, 10));
        label3.setOpaque(true);
        label3.setBackground(new Color(51, 40, 102));

        searchBar.setPreferredSize(new Dimension(searchBar.getPreferredSize().width, 50));

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

    private static JPanel createCreatePanel() {
        JPanel createPanel = new JPanel();

        createPanel.setBackground(Color.GREEN);
        return createPanel;
    }

    private static JPanel createLecturePanel() {
        JPanel lecturePanel = new JPanel();

        lecturePanel.setBackground(Color.RED);
        return lecturePanel;
    }

    private static JPanel createAdminPanel() {
        JPanel adminpanel = new JPanel(new BorderLayout());
        JPanel adminp = new JPanel(new FlowLayout());
        adminp.setBorder(BorderFactory.createEmptyBorder(55, 0, 5, 0));
        adminpanel.setBackground(Color.WHITE);

        JLabel label2 = new JLabel("Here you can find the thesis archives ");
        label2.setFont(new Font("Roboto", Font.ITALIC, 20));
        label2.setBorder(BorderFactory.createEmptyBorder(5, 140, 5, 0));

        PlaceholderTextField log = new PlaceholderTextField("User Name", 40);
        PlaceholderPasswordField password = new PlaceholderPasswordField("Password", 40); // Adjusted width
        JLabel label3 = new JLabel(
                "<html> <font size='8'>Administartion</font>  <br/> Administartion access. </html>");
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

        adminp.add(log);
        adminp.add(password);
        adminp.add(connect);
        adminpanel.add(adminp);

        return adminpanel;
    }

    static class PlaceholderTextField extends JTextField implements FocusListener {
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

    static class PlaceholderPasswordField extends JPasswordField implements FocusListener {
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
}
