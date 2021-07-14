import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;


public class Main {
    public static void main(String args[]){

        //creating an object of Gui class and then call its createAWindow method
        Gui g=new Gui();

        g.createAWindow();
    }
}

/*
 * @author
 * Java Swing assignment
 * Basic event handling
 */

//Gui class
class Gui extends JFrame {
    //here few button, textarea, textfield and panel are created
    private JButton add;
    private JButton display;
    private JButton remove;
    private JButton sort;
    private JButton clear;

    JTextField idField;
    JTextField redField;
    JTextField greenField;
    JTextField blueField;
    JPanel panel;
    JPanel panel1;
    JPanel centerPanel;
    JLabel idLabel;
    JTextArea area;
    JLabel rgbRed;
    JLabel rgbGreen;
    JLabel rgbBlue;
    JLabel Welcome;
    int red = 000;
    int green = 000;
    int blue = 000;
    //Arraylist of ID it will holds all the IDs
    ArrayList<ID> list = new ArrayList<>();

    //This method will create a user interface with all its properties
    public void createAWindow() {
        //Frame that will hold all the stuff
        JFrame frame = new JFrame("CE203_1801459_ASS1");

        //it will close on clicking close
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //initializing  buttons textfield and textareas
        panel = new JPanel();
        panel1 = new JPanel();
        add = new JButton("Add");
        display = new JButton("display");
        remove = new JButton("remove");
        sort = new JButton("sort");
        clear = new JButton("clear");
        idField = new JTextField(6);
        idLabel = new JLabel("ID :");

        //RGB EXTENSION
        rgbRed = new JLabel("RED: ");
        redField = new JTextField(3);

        rgbGreen = new JLabel("GREEN: ");
        greenField = new JTextField(3);

        rgbBlue = new JLabel("BLUE :");
        blueField = new JTextField(3);


        //applying action listener on add button
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                //here i validate the length of the ID's ensuring the length
                boolean match = onlyDigits(id, id.length());
                //if valid then add it
                if (match) {
                    list.add(new ID(Integer.parseInt(id)));
                    area.setText("");
                    area.setText("ID " + id + " has been added to the list");
                } else {
                    area.setText("");
                    area.setText("The ID " + id + " was not added to the list as it is not a valid ID.");
                }
                idField.setText("");

            }
        });

        //applying  an action listener on display ID function
        display.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //more validation
                if (list.isEmpty()) {
                    area.setText("");
                    area.setText(" List is Empty ");
                } else {

                    area.setText("");
                    for (ID id : list) {
                        area.append(id.toString());//filling the area with the ID's currently inputted into the application
                        red = valCheck(redField.getText(),redField); //implementing my value Check class for red, green and blue
                        green = valCheck(greenField.getText(),greenField);
                        blue = valCheck(blueField.getText(),blueField);
                        if (red != -1 && green != -1 && blue != -1) { //validating the inputs in the text fields.
                            Color textColor = new Color(red, green, blue);
                            area.setForeground(textColor);
                            area.repaint();

                        }

                    }
                }
            }
        });

        //action listener on remove ID button
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //here i first validate it
                if (list.isEmpty()) {
                    area.setText("");
                    area.setText(" List is Empty ");
                } else {
                    //getting the text in the text field
                    String id = idField.getText();
                    //validating
                    boolean match = onlyDigits(id, id.length());
                    idField.setText("");
                    area.setText("");
                    if (match) {
                        int Id = Integer.parseInt(id);

                        //here I use a for loop to iterate over the ID list and remove any ID that matches the input
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getId() == Id)
                                list.remove(i);
                                area.setText("ID " + Id + " was removed");

                        }
                    } else {
                        area.setText("");
                        area.setText("The ID " + id + " is not valid");
                    }
                }
            }
        });


        //action listener on sort button
        sort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (list.isEmpty()) {
                    area.setText("");
                    area.setText(" You haven't inputted any IDs! ");
                } else {
                    //here i use collection to sort the ids
                    Collections.sort(list);
                    area.setText("Sorted in numerical order successfully!");
                }
            }
        });

        //action listener on clear that will clear the data from list
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //check if ID list is empty
                if (list.isEmpty()) {
                    area.setText("");
                    area.setText(" ID list is empty ");
                } else {
                    list.clear();
                    area.setText("");
                    area.setText("Every ID has been cleared from the list! ");
                }
            }
        });
        redField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //check if list is empty

            }
        });
        //adding buttons, label and text fields into their appropriate panel

        //TOP OF THE APP
        panel1.add(idLabel);
        panel1.add(idField);

        //PART 2 RGB EXTENSION
        panel1.add(rgbRed);
        panel1.add(redField);

        panel1.add(rgbGreen);
        panel1.add(greenField);

        panel1.add(rgbBlue);
        panel1.add(blueField);

        //BOTTOM BUTTONS OF APP
        panel.add(add);
        panel.add(display);
        panel.add(remove);
        panel.add(sort);
        panel.add(clear);
        area = new JTextArea(50, 35);
        //using scrollbar in text area
        JScrollPane jScrollPane = new JScrollPane(area);
        centerPanel = new JPanel();
        centerPanel.add(jScrollPane);

        //adding main panel to the frame
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(panel1, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(panel, BorderLayout.SOUTH);

        frame.add(mainPanel);

        //set dimensions of Frame
        frame.setPreferredSize(new Dimension(500, 300));
        frame.pack();
        frame.setVisible(true);
    }

    //function that will validate the Ids
    public boolean onlyDigits(String str, int n) {
        if (n < 6 || n > 6)//ensures length of id is exactly 6 not less or more than 6
            return false;

        for (int i = 0; i < n; i++) {


            if (str.charAt(i) >= '0'  //check if char in range 0
                    && str.charAt(i) <= '9') { // to 9
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    private int valCheck(String value,JTextField textField){
        int notInt = 0;
        int valNew = -1;

        try {
            //input validation
            valNew = Integer.parseInt(value);

            if (valNew > 255){ //if the value of the code goes over 255 the validation will set the rgb value to 255
                valNew = 255;
            }
            else if (valNew < 0){
                valNew= 0; // if the value of rgb entered is less than 0 it will be set to 0 by default
            }

            // sets textfield to color inputted by user (rgb)

            textField.setText(Integer.toString(valNew));
        }
        catch(NumberFormatException e) {
            // in the vent no integer is entered into the text field.
            if (!textField.getText().equals("")) {
                notInt += 1;
            }
            textField.setText("");

        }
        finally {
            return valNew;
        }
    }

}

//Id Class As Provided
 class ID implements Comparable<ID> {
    int id = 000000;
    public ID(int id){
        this.id=id;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(ID o) {

        return this.getId()-o.getId();
    }

    @Override
    public String toString() {
        return ("ID = "+this.id+"\n");
    }
}
