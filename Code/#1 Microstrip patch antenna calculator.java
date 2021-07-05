/*
MICROSTRIP PATCH ANTENNA DESIGN CALCULATOR
  
Buttons: Calculate, Reset, Exit
TextFields: 
FOR INPUT : freq(for frequency value) , eps(for Epsilon value)
Formatted TextFields: 
FOR OUTPUT : wl ,xz ,y ,l (various parameter values)
        wl
  ------------
 |      y     |      
 |     __     | l
 |    |  |    |
 |    |  |    |
 |____|  |____| 
   x       z
  
  { Parameter x=z }
  
  This is a diagrammatic representation of a microstrip patch antenna. The characteristics (frequency) of the antenna 
  can be determined/varied by changing the dimensions of these parameters. 
  
  However the dimensions differ for two antennae with the same frequency, but different material. 
  Thats the significance of the EPSILON R value.
  
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static java.lang.StrictMath.sqrt;


public class Main {

    public static void main(String[] args) {

        //Frame for the Main Window      
        JFrame frame = new JFrame("Patch antenna design Calculator");
      
        //Creating required buttons
        JButton  Calculatebutton,Resetbutton, Exitbutton;
        
        //Setting up each button in the frame
        Resetbutton = new JButton("RESET");
        Resetbutton.setBounds(40,320,100,30);

        Exitbutton = new JButton("EXIT");
        Exitbutton.setBounds(200,320,100,30);

        Calculatebutton = new JButton("CALCULATE");
        Calculatebutton.setBounds(120,150,130,30);

        //Adding the buttons to the frame
        frame.add(Resetbutton);
        frame.add(Exitbutton);
        frame.add(Calculatebutton);

        //Setting up the frame properties
        frame.setBackground(Color.blue);
        frame.setSize(400,400);
        frame.setLayout(null);
        frame.setVisible(true);

        //Textfield for user to type in the input
        JTextField freq,eps;
      
        //Formatted text field to show accurate results in double type
        JFormattedTextField wl,xz,y,l;

        //Setting up the textfields 
        freq= new JTextField();
        freq.setBounds(190,45, 100,30);
        frame.add(freq);
        eps= new JTextField();
        eps.setBounds(190,85, 100,30);
        frame.add(eps);

        //Setting up the formatted textfields to display results in double datatype
        wl= new JFormattedTextField(new double[1]);
        wl.setBounds(20,240, 70,30);
        frame.add(wl);
        xz= new JFormattedTextField(new double[1]);
        xz.setBounds(110,240, 70,30);
        frame.add(xz);
        l= new JFormattedTextField(new double[1]);
        l.setBounds(200,240, 70,30);
        frame.add(l);
        y= new JFormattedTextField(new double[1]);
        y.setBounds(290,240, 70,30);
        frame.add(y);

        //Creating labels for every object added to the frame
        JLabel title,freqtitle,epstitle,wltitle,xztitle,ltitle,ytitle;
        title=new JLabel("Enter the values");
        title.setBounds(200,10, 100,30);
        frame.add(title);
        freqtitle=new JLabel("Frequency (Mhz)");
        freqtitle.setBounds(90,45, 100,30);
        frame.add(freqtitle);
        epstitle=new JLabel("Epsilon r");
        epstitle.setBounds(90,85, 50,30);
        frame.add(epstitle);

        wltitle=new JLabel("W & L (mm)");
        wltitle.setBounds(20,200, 70,30);
        frame.add(wltitle);
        xztitle=new JLabel("X & Z (mm)" );
        xztitle.setBounds(110,200, 70,30);
        frame.add(xztitle);
        ytitle=new JLabel("l (mm)");
        ytitle.setBounds(220,200, 70,30);
        frame.add(ytitle);
        ltitle=new JLabel("Y (mm)");
        ltitle.setBounds(310,200, 70,30);
        frame.add(ltitle);

        //Code for Calculate button
        Calculatebutton.addActionListener(new ActionListener(){
            
            //Implementing the actionPerformed method
            public void actionPerformed(ActionEvent e){
                
                //Storing user input values in strings
                String  freqText= freq.getText();
                String  epsText= eps.getText();

                try{
                    
                    // Try to get & display the results using setText() method, using input values
                    wl.setText(String.valueOf((float) (150/(float)(Double.parseDouble(freqText) * sqrt(Double.parseDouble(epsText))))));

                    xz.setText(String.valueOf((float) (60/(float)(Double.parseDouble(freqText) * sqrt(Double.parseDouble(epsText))))));

                    y.setText(String.valueOf((float) (30/(float)(Double.parseDouble(freqText) * sqrt(Double.parseDouble(epsText))))));

                    l.setText(String.valueOf((float) (75/ Float.parseFloat(freqText))));

                }

                catch(Exception a){
                    
                    //Catch any exceptions
                    wl.setText("Invalid input");
                    xz.setText("Invalid input");
                    y.setText("Invalid input");
                    l.setText("Invalid input");

                }


            }
        });
        
        //Code for Reset button
        Resetbutton.addActionListener(new ActionListener(){
            
            //Implementing the actionPerformed method
            public void actionPerformed(ActionEvent e){
                
                //Just making all textfields empty
                freq.setText("");
                eps.setText("");
                wl.setText("");
                xz.setText("");
                y.setText("");
                l.setText("");
            }
        });
        
        //Code for Exit button
        Exitbutton.addActionListener(new ActionListener(){
            
            //Implementing the actionPerformed method
            public void actionPerformed(ActionEvent e){
              
                //Changing visibility of the frame to "false" & dispose the frame using dispose() method
                frame.setVisible(false);
                frame.dispose();
                System.exit(0);
            }
        });
    }
  
}

// THE END
