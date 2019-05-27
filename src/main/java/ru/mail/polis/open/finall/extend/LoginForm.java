package ru.mail.polis.open.finall.extend;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import ru.mail.polis.open.finall.LogoPanel;
import ru.mail.polis.open.finall.dao.UserDao;
import ru.mail.polis.open.finall.dao.UserDaoImpl;
import javax.swing.JPasswordField;

public class LoginForm extends JFrame {

    private JPanel contentPane;
    private JPanel imagePnl;
    private SpringLayout slContentPane;
    private JLabel companyName;
    private JLabel usernameLbl;
    private JTextField usernameTxt;
    private JLabel passwordLbl;
    private JPasswordField passwordTxt;
    private JButton loginBtn;
    private JButton cancelBtn;

    private static LoginForm frame;
    private JButton picChooser;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new LoginForm();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public LoginForm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 443, 282);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(153, 204, 204));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        slContentPane = new SpringLayout();
        contentPane.setLayout(slContentPane);
        contentPane.add(getImagePnl());
        contentPane.add(getCompanyName());
        contentPane.add(getUsernameLbl());
        contentPane.add(getUsernameTxt());
        contentPane.add(getPasswordLbl());
        contentPane.add(getPasswordTxt());
        contentPane.add(getLoginBtn());
        contentPane.add(getCancelBtn());
        contentPane.add(getPicChooser());
    }

    private JPanel getImagePnl() {
        if (imagePnl == null) {
            imagePnl = new LogoPanel();
            slContentPane.putConstraint(SpringLayout.NORTH, imagePnl, 0, SpringLayout.NORTH, contentPane);
            slContentPane.putConstraint(SpringLayout.WEST, imagePnl, 194, SpringLayout.WEST, contentPane);
            slContentPane.putConstraint(SpringLayout.EAST, imagePnl, -28, SpringLayout.EAST, contentPane);
        }
        return imagePnl;
    }

    private JLabel getCompanyName() {
        if (companyName == null) {
            companyName = new JLabel("Technopolis, 2019");
            slContentPane.putConstraint(SpringLayout.EAST, companyName, 175, SpringLayout.WEST, contentPane);
            companyName.setFont(new Font("Tahoma", Font.BOLD, 15));
            companyName.setForeground(Color.MAGENTA);
            slContentPane.putConstraint(SpringLayout.NORTH, companyName, 21, SpringLayout.NORTH, contentPane);
            slContentPane.putConstraint(SpringLayout.WEST, companyName, 10, SpringLayout.WEST, contentPane);
            slContentPane.putConstraint(SpringLayout.SOUTH, companyName, 64, SpringLayout.NORTH, contentPane);
        }
        return companyName;
    }

    private JLabel getUsernameLbl() {
        if (usernameLbl == null) {
            usernameLbl = new JLabel("User Name:");
            slContentPane.putConstraint(SpringLayout.NORTH, usernameLbl, 40, SpringLayout.SOUTH, getCompanyName());
            slContentPane.putConstraint(SpringLayout.WEST, usernameLbl, 25, SpringLayout.WEST, contentPane);
            usernameLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
        }
        return usernameLbl;
    }

    private JTextField getUsernameTxt() {
        if (usernameTxt == null) {
            usernameTxt = new JTextField();
            slContentPane.putConstraint(SpringLayout.SOUTH, getImagePnl(), -15, SpringLayout.NORTH, usernameTxt);
            slContentPane.putConstraint(SpringLayout.NORTH, usernameTxt, 104, SpringLayout.NORTH, contentPane);
            slContentPane.putConstraint(SpringLayout.EAST, getUsernameLbl(), -6, SpringLayout.WEST, usernameTxt);
            slContentPane.putConstraint(SpringLayout.WEST, usernameTxt, 163, SpringLayout.WEST, contentPane);
            usernameTxt.setColumns(10);
        }
        return usernameTxt;
    }

    private JLabel getPasswordLbl() {
        if (passwordLbl == null) {
            passwordLbl = new JLabel("Password:");
            slContentPane.putConstraint(SpringLayout.SOUTH, getUsernameLbl(), -11, SpringLayout.NORTH, passwordLbl);
            slContentPane.putConstraint(SpringLayout.EAST, passwordLbl, 0, SpringLayout.EAST, getUsernameLbl());
            slContentPane.putConstraint(SpringLayout.WEST, passwordLbl, 25, SpringLayout.WEST, contentPane);
            slContentPane.putConstraint(SpringLayout.NORTH, passwordLbl, 135, SpringLayout.NORTH, contentPane);
            slContentPane.putConstraint(SpringLayout.SOUTH, passwordLbl, -74, SpringLayout.SOUTH, contentPane);
            passwordLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
        }
        return passwordLbl;
    }

    private JPasswordField getPasswordTxt() {
        if (passwordTxt == null) {
            passwordTxt = new JPasswordField();
            slContentPane.putConstraint(SpringLayout.NORTH, passwordTxt, 135, SpringLayout.NORTH, contentPane);
            slContentPane.putConstraint(SpringLayout.SOUTH, getUsernameTxt(), -6, SpringLayout.NORTH, passwordTxt);
            slContentPane.putConstraint(SpringLayout.EAST, getUsernameTxt(), 0, SpringLayout.EAST, passwordTxt);
            slContentPane.putConstraint(SpringLayout.WEST, passwordTxt, 163, SpringLayout.WEST, contentPane);
            slContentPane.putConstraint(SpringLayout.EAST, passwordTxt, -78, SpringLayout.EAST, contentPane);
            passwordTxt.setColumns(10);
        }
        return passwordTxt;
    }

    private JButton getLoginBtn() {
        if (loginBtn == null) {
            loginBtn = new JButton("Login");
            loginBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
            slContentPane.putConstraint(SpringLayout.NORTH, loginBtn, 176, SpringLayout.NORTH, contentPane);
            slContentPane.putConstraint(SpringLayout.SOUTH, getPasswordTxt(), -16, SpringLayout.NORTH, loginBtn);
            slContentPane.putConstraint(SpringLayout.WEST, loginBtn, 160, SpringLayout.WEST, contentPane);
            slContentPane.putConstraint(SpringLayout.EAST, loginBtn, -179, SpringLayout.EAST, contentPane);
            loginBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    UserDao userDao = new UserDaoImpl();
                    boolean isValidUser = userDao.validateUser(usernameTxt.getText(),
                            new String(passwordTxt.getPassword()));
                    if (isValidUser) {
                        StudentFormWithCrud studForm = new StudentFormWithCrud();
                        studForm.setVisible(true);
                        frame.dispose();
                    } else {
                        usernameTxt.setBackground(Color.PINK);
                        passwordTxt.setBackground(Color.PINK);
                    }
                }
            });
        }
        return loginBtn;
    }

    private JButton getCancelBtn() {
        if (cancelBtn == null) {
            cancelBtn = new JButton("Cancel");
            cancelBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    System.exit(0);
                }
            });
            slContentPane.putConstraint(SpringLayout.SOUTH, cancelBtn, 25, SpringLayout.NORTH, getLoginBtn());
            cancelBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
            slContentPane.putConstraint(SpringLayout.NORTH, cancelBtn, 0, SpringLayout.NORTH, getLoginBtn());
            slContentPane.putConstraint(SpringLayout.WEST, cancelBtn, 11, SpringLayout.EAST, getLoginBtn());
            slContentPane.putConstraint(SpringLayout.EAST, cancelBtn, -79, SpringLayout.EAST, contentPane);
        }
        return cancelBtn;
    }

    private JButton getPicChooser() {
        if (picChooser == null) {
            picChooser = new JButton("Choose Image");
            slContentPane.putConstraint(SpringLayout.NORTH, picChooser, 1, SpringLayout.SOUTH, getCompanyName());
            slContentPane.putConstraint(SpringLayout.WEST, picChooser, 20, SpringLayout.WEST, getCompanyName());
            slContentPane.putConstraint(SpringLayout.SOUTH, picChooser, -11, SpringLayout.NORTH, getUsernameLbl());
            slContentPane.putConstraint(SpringLayout.EAST, picChooser, -19, SpringLayout.WEST, getImagePnl());
            picChooser.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        JFileChooser fc = new JFileChooser();
                        fc.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg"));

                        int result = fc.showOpenDialog(contentPane);
                        if (result == JFileChooser.APPROVE_OPTION) {
                            File path = fc.getSelectedFile();

                            JLabel imageLbl = new JLabel();
                            imageLbl.setIcon(new ImageIcon(new ImageIcon(ImageIO.read(path)).getImage()
                                    .getScaledInstance(200, 100, Image.SCALE_DEFAULT)));
                            imagePnl.add(imageLbl);
                            imagePnl.revalidate();
                            imagePnl.repaint();
                        }

                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                }
            });
        }
        return picChooser;
    }
}
