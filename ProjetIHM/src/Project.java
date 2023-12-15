import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.Desktop.Action;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;

//import com.itexxtpdf.kernel.pdf.PdfDocument;
//import com.itextpdf.kernel.pdf.PdfWriter;

public class Project {

    private static boolean isAdminConnected = false;
    private static JLabel create;
    private static JLabel proffe;
    private static JPanel framepanel;
    private static JPanel leftPanel;
    private static JPanel createpanel;

    public Project() {

        Backend bk = new Backend();

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

        JLabel admin = new JLabel("Administration", adminIcon, JLabel.LEFT);
        admin.setForeground(Color.WHITE);
        admin.setFont(new Font("Roboto", Font.BOLD, 15));
        admin.setBorder(BorderFactory.createEmptyBorder(40, 30, 10, 10));

        create = new JLabel("Thesis creation", createIcon, JLabel.LEFT);
        create.setFont(new Font("Roboto", Font.BOLD, 15));
        create.setForeground(Color.WHITE);
        create.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 10));
        create.setVisible(isAdminConnected);

        proffe = new JLabel("Professor management", createIcon, JLabel.LEFT);
        proffe.setFont(new Font("Roboto", Font.BOLD, 15));
        proffe.setForeground(Color.WHITE);
        proffe.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 10));
        proffe.setVisible(isAdminConnected);

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
        leftPanel.add(lecture);
        if (isAdminConnected = true) {
            leftPanel.add(create);
        }
        if (isAdminConnected = true) {
            leftPanel.add(proffe);
        }

        leftPanel.setBackground(new Color(51, 40, 102));

        JPanel rightPanel = new JPanel(new CardLayout());
        rightPanel.add(SearchPanel(), "search");
        rightPanel.add(CreationPanel(), "create");
        rightPanel.add(AdminPanel(), "admin");
        rightPanel.add(LecturePanel(), "lecture");
        rightPanel.add(frameCreationPanel(), "proffe");

        CardLayout cardLayout = (CardLayout) rightPanel.getLayout();

        admin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(rightPanel, "admin");
            }
        });

        proffe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(rightPanel, "proffe");
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

        // ################################### LA FENETRE
        // ###############################################

        frame.setLayout(new BorderLayout());
        frame.add(rightPanel, BorderLayout.CENTER);
        frame.add(leftPanel, BorderLayout.WEST);

        frame.setSize(1000, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static JPanel SearchPanel() {
        JPanel searchPanel = new JPanel(new BorderLayout());
        JPanel searchp = new JPanel(new FlowLayout());
        searchPanel.setBackground(Color.WHITE);
        JLabel label2 = new JLabel("Here you can find the theses archives");
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

        JPanel top = new JPanel(new GridLayout(2, 1, 5, 0));
        top.add(label2, BorderLayout.PAGE_START);
        top.add(label3, BorderLayout.CENTER);

        searchPanel.add(top, BorderLayout.PAGE_START);

        searchp.add(searchBar);
        searchp.add(searchButton);
        searchPanel.add(searchp, BorderLayout.CENTER);
        JPanel result = new JPanel(new GridLayout(15, 1, 100, 10));
        result.removeAll();
        searchp.add(result, BorderLayout.CENTER);
        searchp.revalidate();
        searchp.repaint();
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredWord = searchBar.getText().trim().toLowerCase();
                rechercheMemoir(enteredWord, enteredWord, enteredWord, result, null);
                searchp.revalidate();
                searchp.repaint();
            }
        });
        return searchPanel;
    }

    private static JPanel CreationPanel() {

        createpanel = new JPanel(new BorderLayout());
        JPanel adminp = new JPanel(new FlowLayout());
        adminp.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        createpanel.setBackground(Color.WHITE);

        JLabel label2 = new JLabel("Here you can find the thesis archives ");
        label2.setFont(new Font("Roboto", Font.ITALIC, 20));
        label2.setBorder(BorderFactory.createEmptyBorder(5, 140, 5, 0));

        JTextField title = new JTextField(40);
        String[] listeNoms = new String[Backend.ens.size()];
        int i = 0;
        for (Enseignant enseignant : Backend.ens) {
            listeNoms[i] = (enseignant.getNom());
            i++;
        }
        JComboBox<String> Framer = new JComboBox<>(listeNoms);

        JFormattedTextField dateField = createDateTextField();

        JTextField code = new JTextField(40);
        JTextField resume = new JTextField(40);
        JTextField auteur = new JTextField(40);

        JTextField pdfPathField = new JTextField(33);
        pdfPathField.setEditable(false);
        String[] level = { "master", "licence", "ingeniorat" };

        JComboBox<String> niveau = new JComboBox<>(level);

        JButton resumeBrowseButton = new JButton("Add pdf");

        Memoir pdfByteArray = new Memoir();
        resumeBrowseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(".pdf");
                fileChooser.showOpenDialog(fileChooser);
                File selectedFile = fileChooser.getSelectedFile();
                int result = fileChooser.showOpenDialog(createpanel);

                if (result == JFileChooser.APPROVE_OPTION) {
                    pdfPathField.setText(fileChooser.getSelectedFile().getAbsolutePath());
                    try (FileInputStream fis = new FileInputStream(selectedFile)) {
                        byte[] byteArray = new byte[(int) selectedFile.length()];
                        fis.read(byteArray);
                        pdfByteArray.setPdfBytes(byteArray);
                        // Utilisez le tableau de bytes (pdfByteArray) comme nécessaire
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }

        });

        JLabel label3 = new JLabel(
                "<html> <font size='8'>Thesis creation</font> <br/> You can add thesis by entering the title, professor, date of thesis, code, and resume. </html>");
        label3.setForeground(Color.WHITE);
        label3.setBorder(BorderFactory.createEmptyBorder(25, 36, 25, 10));
        label3.setOpaque(true);
        label3.setBackground(new Color(51, 40, 102));

        title.setPreferredSize(new Dimension(title.getPreferredSize().width, 55));
        auteur.setPreferredSize(new Dimension(title.getPreferredSize().width, 55));
        Framer.setPreferredSize(new Dimension(Framer.getPreferredSize().width, title.getPreferredSize().height));
        dateField.setPreferredSize(new Dimension(70, 55));
        code.setPreferredSize(new Dimension(Framer.getPreferredSize().width, 55));
        resume.setPreferredSize(new Dimension(120, 55));
        pdfPathField.setPreferredSize(new Dimension(Framer.getPreferredSize().width
                - 100, 55));
        resumeBrowseButton.setPreferredSize(new Dimension(80, 55));

        JButton Add = new JButton("Add");
        Add.setPreferredSize(new Dimension(250, title.getPreferredSize().height));

        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (title.getText().isEmpty() ||
                        dateField.getText().isEmpty()
                        || code.getText().isEmpty() || pdfPathField.getText().isEmpty() || resume.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(createpanel, "Please enter data in all fields",
                            "Incomplete Data",
                            JOptionPane.WARNING_MESSAGE);
                } else {

                    Backend m = new Backend();
                    int i = 0;
                    for (Enseignant en : Backend.ens) {
                        if (Framer.getSelectedIndex() == i) {
                            m.insererMemoire(code.getText(), title.getText(), auteur.getText(),
                                    Integer.parseInt(dateField.getText()), resume.getText(), en.getId(),
                                    pdfByteArray.getPdfBytes(), niveau.getSelectedItem().toString());
                            break;
                        }
                        i++;
                    }
                    title.setText("");
                    auteur.setText("");
                    dateField.setText("");
                    code.setText("");
                    resume.setText("");
                    JOptionPane.showMessageDialog(framepanel, "thesis added",
                            "added succesfully",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JPanel top = new JPanel(new GridLayout(2, 1, 5, 5));
        top.add(label2, BorderLayout.PAGE_START);
        top.add(label3, BorderLayout.CENTER);

        createpanel.add(top, BorderLayout.PAGE_START);

        adminp.add(FieldPanel("Title:", title));
        adminp.add(FieldPanel("Code:", code));
        adminp.add(FieldPanel("professor:", Framer));
        adminp.add(FieldPanel("Date :", dateField));
        adminp.add(FieldPanel("Level", niveau));
        adminp.add(FieldPanel("Auteur:", auteur));
        adminp.add(FieldPanel("Resume:", resume));
        adminp.add(FieldPanel("Pdf:", pdfPathField));
        adminp.add(resumeBrowseButton);
        adminp.add(Add);
        createpanel.add(adminp);

        return createpanel;
    }

    private static JPanel frameCreationPanel() {
        framepanel = new JPanel(new BorderLayout());
        JPanel adminp = new JPanel(new FlowLayout());
        adminp.setBorder(BorderFactory.createEmptyBorder(55, 0, 5, 0));
        framepanel.setBackground(Color.WHITE);

        JLabel label2 = new JLabel("Here you can find the thesis archives ");
        label2.setFont(new Font("Roboto", Font.ITALIC, 20));
        label2.setBorder(BorderFactory.createEmptyBorder(5, 140, 5, 0));

        PlaceholderTextField Firstname = new PlaceholderTextField("First name", 40);
        PlaceholderTextField Lastname = new PlaceholderTextField("Last name", 40);
        JTextField specialite = new JTextField(40);

        JLabel label3 = new JLabel(
                "<html> <font size='8'>Professor management</font> <br/> You can add professor by entering his information, name, field ... <br/> you can remove or modify the information of the professor </html>");
        label3.setForeground(Color.WHITE);
        label3.setBorder(BorderFactory.createEmptyBorder(25, 36, 25, 10));
        label3.setOpaque(true);
        label3.setBackground(new Color(51, 40, 102));

        Firstname.setPreferredSize(new Dimension(120, 60));
        Lastname.setPreferredSize(new Dimension(120, 60));
        specialite.setPreferredSize(new Dimension(120, 60));

        JButton Add = new JButton("Add");
        Add.setPreferredSize(new Dimension(250, Firstname.getPreferredSize().height));

        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (Firstname.getText().isEmpty() || Lastname.getText().isEmpty()
                        || specialite.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(framepanel, "Please enter data in all fields",
                            "Incomplete Data",
                            JOptionPane.WARNING_MESSAGE);
                } else {

                    Backend b = new Backend();
                    b.insererEnseignant(Firstname.getText(), Lastname.getText(), specialite.getText());
                }
                Firstname.setText("");
                Lastname.setText("");
                specialite.setText("");
                JOptionPane.showMessageDialog(framepanel, "professor added",
                        "added succesfully",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        JPanel infoframe = new JPanel(new GridLayout(1, 2, 10, 10));
        JScrollPane scrollPane = new JScrollPane(infoframe);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        Enseignant[] listeNoms = new Enseignant[Backend.ens.size()];
        int i = 0;
        for (Enseignant enseignant : Backend.ens) {
            listeNoms[i] = new Enseignant(enseignant.getId(), enseignant.getNom(), enseignant.getPrenom(),
                    enseignant.getSpecialite());
            i++;
        }

        for (Enseignant enseignantInfo : listeNoms) {

            JTextField nomField = new JTextField(enseignantInfo.getNom());
            nomField.setPreferredSize(new Dimension(110, 50));
            JTextField prenomField = new JTextField(enseignantInfo.getPrenom());
            prenomField.setPreferredSize(new Dimension(80, 50));
            JTextField specialiteField = new JTextField(enseignantInfo.getSpecialite());
            specialiteField.setPreferredSize(new Dimension(240, 50));
            JButton sup = new JButton("Remove");
            sup.setPreferredSize(new Dimension(70, 50));
            JButton modify = new JButton("Modify");
            modify.setPreferredSize(new Dimension(70, 50));
            JPanel info = new JPanel();
            info.add(nomField);
            info.add(prenomField);
            info.add(specialiteField);
            info.add(sup);
            info.add(modify);
            labelPanel.add(info);
            sup.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Backend b = new Backend();
                    b.supprimerENS(enseignantInfo.getId());

                    SwingUtilities.invokeLater(() -> {
                        info.removeAll();
                        labelPanel.remove(info);
                        framepanel.revalidate();
                        framepanel.repaint();
                    });
                }
            });
            modify.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    enseignantInfo.setNom(nomField.getText());
                    enseignantInfo.setPrenom(prenomField.getText());
                    enseignantInfo.setSpecialite(specialiteField.getText());
                    Backend b = new Backend();
                    b.updateENS(enseignantInfo.getId(), enseignantInfo.getSpecialite(), enseignantInfo.getNom(),
                            enseignantInfo.getPrenom());
                    framepanel.revalidate();
                    framepanel.repaint();
                    JOptionPane.showMessageDialog(framepanel, "modification succefully",
                            "changing succesfully",
                            JOptionPane.INFORMATION_MESSAGE);

                }
            });

            labelPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        infoframe.add(labelPanel);
        JButton back = new JButton("previous");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    framepanel.remove(infoframe);
                    framepanel.add(adminp);
                });
            }
        });

        JButton framers = new JButton("List of existing professors");
        framers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    framepanel.add(scrollPane);
                    framepanel.remove(adminp);
                    adminp.removeAll();
                    framepanel.revalidate();
                    framepanel.repaint();
                });
            }
        });

        JPanel top = new JPanel(new GridLayout(2, 1, 5, 5));
        top.add(label2, BorderLayout.PAGE_START);
        top.add(label3, BorderLayout.CENTER);

        framepanel.add(top, BorderLayout.PAGE_START);

        adminp.add(FieldPanel("First Name:", Firstname));
        adminp.add(FieldPanel("Last Name:", Lastname));
        adminp.add(FieldPanel("Field :", specialite));
        adminp.add(Add);
        JPanel south = new JPanel(new GridLayout(1, 2, 10, 10));
        south.add(back);
        south.add(framers);
        framepanel.add(south, BorderLayout.PAGE_END);
        framepanel.add(adminp);

        return framepanel;
    }

    private static JPanel FieldPanel(String label, JComponent component) {
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
            formatter = new MaskFormatter("####");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JFormattedTextField dateField = new JFormattedTextField(formatter);
        return dateField;
    }

    private static JPanel createDetailPanel(Memoir memoir, Enseignant enseignant) {
        JPanel detailPanel = new JPanel(new GridLayout(3, 1));

        JPanel textfield = new JPanel(new GridLayout(4, 2));

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.add(new JLabel("Title:"));
        JTextField titles = new JTextField(memoir.getTitre());
        titles.setPreferredSize(new Dimension(150, 40));
        titlePanel.add(titles);
        textfield.add(titlePanel);

        JPanel authorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        authorPanel.add(new JLabel("Auteur:"));
        JTextField author = new JTextField(memoir.getAuteur());
        author.setPreferredSize(new Dimension(250, 40));
        authorPanel.add(author);
        textfield.add(authorPanel);

        JPanel cotePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cotePanel.add(new JLabel("Cote:"));
        JTextField cote = new JTextField(memoir.getCote());
        cote.setPreferredSize(new Dimension(150, 40));
        cotePanel.add(cote);
        textfield.add(cotePanel);

        JPanel yearsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        yearsPanel.add(new JLabel("Year:"));
        JTextField years = new JTextField(String.valueOf(memoir.getAnnes()));
        years.setPreferredSize(new Dimension(150, 40));
        yearsPanel.add(years);
        textfield.add(yearsPanel);

        JPanel niveauPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        niveauPanel.add(new JLabel("Level:"));
        JTextField niveau = new JTextField(memoir.getNiveau());
        niveau.setPreferredSize(new Dimension(150, 40));
        niveauPanel.add(niveau);
        textfield.add(niveauPanel);

        JPanel enseignantPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        enseignantPanel.add(new JLabel("professor:"));
        JTextField enseignantLabel = new JTextField(enseignant.getNom());
        enseignantLabel.setPreferredSize(new Dimension(150, 40));
        enseignantPanel.add(enseignantLabel);
        textfield.add(enseignantPanel);

        JPanel resumerPanel = new JPanel(new BorderLayout());
        resumerPanel.add(new JLabel("Resumer:"), BorderLayout.NORTH);

        JTextArea resumerss = new JTextArea(memoir.getResumer());
        resumerss.setLineWrap(true);
        resumerss.setWrapStyleWord(true);
        resumerss.setColumns(10);

        JScrollPane resumerScrollPane = new JScrollPane(resumerss);
        resumerScrollPane.setPreferredSize(new Dimension(300, 150));

        resumerPanel.add(resumerScrollPane, BorderLayout.CENTER);
        resumerPanel.add(resumerss);

        JPanel buttons = new JPanel();

        JButton sup = new JButton("Sup");
        JButton modif = new JButton("Modify");
        buttons.add(sup);
        buttons.add(modif);

        sup.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Backend bs = new Backend();
                bs.supprimerMemoir(memoir.getId_mem());
                detailPanel.removeAll();
                JLabel removed = new JLabel("These deleted");
                detailPanel.add(removed);
                detailPanel.revalidate();
                detailPanel.repaint();
            }
        });

        modif.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Memoir mr = new Memoir(memoir.getId_mem(), cote.getText(), titles.getText(), author.getText(),
                        Integer.parseInt(years.getText()), resumerss.getText(), memoir.getId_ens(),
                        memoir.getPdfBytes(), niveau.getText());
                Backend bkn = new Backend();
                bkn.modifierMemoir(mr);
                JOptionPane.showMessageDialog(framepanel, "thesis modified",
                        "modified succesfully",
                        JOptionPane.INFORMATION_MESSAGE);

                detailPanel.revalidate();
                detailPanel.repaint();
            }

        });
        if (isAdminConnected = false) {
            sup.setEnabled(false);
            modif.setEnabled(false);
        }
        detailPanel.add(textfield);
        detailPanel.add(resumerPanel);
        detailPanel.add(buttons);

        return detailPanel;
    }

    private JPanel LecturePanel() {
        JPanel lecturePanel = new JPanel(new BorderLayout());
        JPanel lecturep = new JPanel(new FlowLayout());
        lecturep.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));
        lecturePanel.setBackground(Color.WHITE);

        JLabel label2 = new JLabel("Here you can find the theses archives ");
        label2.setFont(new Font("Roboto", Font.ITALIC, 20));
        label2.setBorder(BorderFactory.createEmptyBorder(5, 140, 5, 0));

        JLabel label3 = new JLabel(
                "<html> <font size='8'>Lecture</font>  <br/> here you find the list of theses . </html>");
        label3.setForeground(Color.WHITE);
        label3.setBorder(BorderFactory.createEmptyBorder(25, 36, 25, 10));
        label3.setOpaque(true);
        label3.setBackground(new Color(51, 40, 102));

        String[] listeNoms = new String[Backend.ens.size() + 1];
        int i = 1;
        listeNoms[0] = "";
        for (Enseignant enseignant : Backend.ens) {
            listeNoms[i] = (enseignant.getNom());
            i++;
        }
        JComboBox<String> framerComboBox = new JComboBox<>(listeNoms);
        JComboBox<String> yearComboBox = new JComboBox<>(new String[] { "", "2000",
                "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008",
                "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016",
                "2017", "2018", "2019", "2020", "2021", "2022", "2023" });

        JComboBox<String> levelComboBox = new JComboBox<>(new String[] { "", "licence", "master", "ingeniorat" });

        JPanel top = new JPanel(new GridLayout(2, 1, 5, 5));
        top.add(label2, BorderLayout.PAGE_START);
        top.add(label3, BorderLayout.CENTER);

        lecturePanel.add(top, BorderLayout.PAGE_START);
        JPanel combo = new JPanel(new GridLayout(2, 2, 1, 1));

        combo.add(FieldPane("Professor:", framerComboBox));
        combo.add(FieldPane("Year:", yearComboBox));
        combo.add(FieldPane("Level:", levelComboBox));

        rechercheMemoir(levelComboBox.getSelectedItem().toString(),
                framerComboBox.getSelectedItem().toString(),
                yearComboBox.getSelectedItem().toString(), lecturep, combo);

        lecturePanel.add(lecturep);
        yearComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                rechercheMemoir(levelComboBox.getSelectedItem().toString(),
                        framerComboBox.getSelectedItem().toString(),
                        yearComboBox.getSelectedItem().toString(), lecturep, combo);
            }
        });

        framerComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                rechercheMemoir(levelComboBox.getSelectedItem().toString(),
                        framerComboBox.getSelectedItem().toString(),
                        yearComboBox.getSelectedItem().toString(), lecturep, combo);
            }
        });
        levelComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                rechercheMemoir(levelComboBox.getSelectedItem().toString(),
                        framerComboBox.getSelectedItem().toString(),
                        yearComboBox.getSelectedItem().toString(), lecturep, combo);
            }
        });
        return lecturePanel;
    }

    public static void rechercheMemoir(String lvl, String nv, String yrs, JPanel lecturep, JPanel combo) {
        lecturep.removeAll();
        if (combo != null)
            lecturep.add(combo);
        for (Memoir mrm : Backend.memr) {
            Enseignant[] en = { null };

            for (Enseignant personne : Backend.ens) {
                if (personne.getId() == mrm.getId_ens()) {
                    en[0] = personne;
                    break;
                }
            }

            if (en[0] != null) {
                JLabel info = new JLabel(
                        "<html> <ul> <li> " + mrm.getTitre() + "(" + en[0].getNom() + "," + mrm.getAnnes() + ","
                                + mrm.getNiveau()
                                + ") </li> </ul> </html>");

                String str = info.getText();
                if (str.contains(lvl) && str.contains(nv) && str.contains(yrs)) {
                    JButton click = new JButton("MORE");
                    JPanel mem = new JPanel();
                    mem.add(info);
                    mem.add(click);
                    lecturep.add(mem);

                    JPanel detailPanel = createDetailPanel(mrm, en[0]);

                    click.addActionListener(e -> {
                        lecturep.removeAll();
                        lecturep.add(detailPanel);
                        lecturep.revalidate();
                        lecturep.repaint();
                    });
                }
            }
        }

        lecturep.revalidate();
        lecturep.repaint();
    }

    private JPanel FieldPane(String label, JComponent component) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel fieldLabel = new JLabel(label);
        fieldLabel.setPreferredSize(new Dimension(100, 20));
        panel.add(fieldLabel);
        panel.add(component);
        return panel;
    }

    private static JPanel AdminPanel() {
        JPanel adminpanel = new JPanel(new BorderLayout());
        JPanel adminp = new JPanel(new FlowLayout());
        adminp.setBorder(BorderFactory.createEmptyBorder(55, 0, 5, 0));
        adminpanel.setBackground(Color.WHITE);

        JLabel label2 = new JLabel("Here you can find the thesis archives ");
        label2.setFont(new Font("Roboto", Font.ITALIC, 20));
        label2.setBorder(BorderFactory.createEmptyBorder(5, 140, 5, 0));

        PlaceholderTextField log = new PlaceholderTextField("Enter your account's name", 40);
        PlaceholderPasswordField password = new PlaceholderPasswordField("Password", 40); // Adjusted width
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
                String enteredUsername = log.getText();
                String enteredPassword = new String(password.getPassword());

                Backend backend = new Backend();
                boolean isValidCredentials = backend.checkCredentials(enteredUsername, enteredPassword);

                if (isValidCredentials) {
                    isAdminConnected = true;
                    SwingUtilities.invokeLater(() -> {
                        create.setVisible(true);
                        proffe.setVisible(true);
                        adminpanel.remove(adminp);
                        adminpanel.add(hey, BorderLayout.CENTER);
                        adminpanel.add(deconnect, BorderLayout.SOUTH);
                        adminpanel.revalidate();
                        adminpanel.repaint();
                    });
                    System.out.println("Welcome");
                } else {
                    isAdminConnected = false;
                    SwingUtilities.invokeLater(() -> {
                        create.setVisible(false);
                        proffe.setVisible(false);
                        adminpanel.remove(hey);
                        adminpanel.remove(deconnect);
                        adminpanel.add(adminp, BorderLayout.CENTER);
                        adminpanel.revalidate();
                        adminpanel.repaint();
                    });
                    JOptionPane.showMessageDialog(null, "Invalid account", "Incorrect info",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        deconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isAdminConnected = false;
                SwingUtilities.invokeLater(() -> {
                    leftPanel.remove(create);
                    adminpanel.remove(hey);
                    adminpanel.remove(deconnect);
                    adminpanel.add(adminp, BorderLayout.CENTER);
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