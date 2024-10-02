package menu;

import rozhranie.OknoHry;
import zakladHry.VytvorenieHry;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * trieda hlavenho menu
 * @author Ľubomír Bojda
 */
public class HlavneMenu {
    private JFrame frame;
    private JPanel panel1;
    private JButton hratButton;
    private JButton ulozHruButton;
    private JButton nacitajHruButton;
    private JButton vypniButton;
    private boolean hraSpustena;
    private VytvorenieHry vytvorenieHry;

    /**
     * konstruktor
     */
    public HlavneMenu() {
        this.vytvorenieHry = new VytvorenieHry(this);
        this.hraSpustena = false;
        this.frame = new JFrame("Menu");
        this.frame.setSize(OknoHry.SIRKA_OKNA, OknoHry.VYSKA_OKNA);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setLocationRelativeTo(null);
        this.panel1.setBackground(Color.BLACK);
        this.frame.setContentPane(this.panel1);
        this.frame.pack();
        this.frame.setVisible(true);


        this.hratButton.addActionListener(e -> {
            this.frame.setVisible(false);
            this.vytvorenieHry.zapniHru();
        });

        this.vypniButton.addActionListener(e -> {
            this.frame.setVisible(false);
            System.exit(0);
        });

        this.ulozHruButton.addActionListener(e -> {
            this.ulozHru();
        });

        this.nacitajHruButton.addActionListener(e -> {
            this.nacitajHru();
        });

    }

    /**
     * metoda na ulozenie hry
     */
    public void ulozHru() {
        var fileChooser = new JFileChooser();
        var saveDialog = fileChooser.showSaveDialog(HlavneMenu.this.frame);
        var level1 = this.vytvorenieHry.getHra().getLevel1().getLevelPrejdeny();
        var level2 = this.vytvorenieHry.getHra().getLevel2().getLevelPrejdeny();
        var level3 = this.vytvorenieHry.getHra().getLevel3().getLevelPrejdeny();

        if (saveDialog == JFileChooser.APPROVE_OPTION) {
            var subor = fileChooser.getSelectedFile();


            try {
                var suborOIS = new FileOutputStream(subor);
                try (var oIS = new ObjectOutputStream(suborOIS)) {

                    oIS.writeBoolean(level1);
                    oIS.writeBoolean(level2);
                    oIS.writeBoolean(level3);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * metoda na nacitanie hry
     */
    public void nacitajHru() {
        var fileChooser = new JFileChooser();
        var saveDialog = fileChooser.showSaveDialog(HlavneMenu.this.frame);

        if (saveDialog == JFileChooser.APPROVE_OPTION) {

            var subor = fileChooser.getSelectedFile();


            try {
                var suborOIS = new FileInputStream(subor);

                try (var oIS = new ObjectInputStream(suborOIS)) {

                    var level1 = oIS.readBoolean();
                    var level2 = oIS.readBoolean();
                    var level3 = oIS.readBoolean();

                    this.vytvorenieHry.getHra().getLevel1().setLevelPrejdeny(level1);
                    this.vytvorenieHry.getHra().getLevel2().setLevelPrejdeny(level2);
                    this.vytvorenieHry.getHra().getLevel3().setLevelPrejdeny(level3);


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * vrati frame hlavneho menu
     * @return frame hl. menu
     */
    public JFrame getFrame() {
        return this.frame;
    }
}
