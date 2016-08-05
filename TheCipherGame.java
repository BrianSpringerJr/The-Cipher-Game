/*********************************************************************
 *
 *  This file is part of TheCipherGame.
 * 
 *
 *  TheCipherGame was developed by Brian Springer
 *
 *  The CipherGame was made simply for fun. So give it a try. 
 *
 **********************************************************************/
package the.cipher.game;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class TheCipherGame extends JFrame  implements ActionListener {
    
    // Program Fields
    boolean isEncoded;
    
    // GUI Fields
    
    Container content = this.getContentPane();
    
    JPanel pnl = new JPanel();
    JPanel mainPnl = new JPanel();
    
    JTextArea txtFld = new JTextArea();
    
    JButton toCAESAR = new JButton("Hide Your Message");
    JButton fromCAESAR = new JButton("Uncover a Secret");
    JButton ABTASH = new JButton("Flip it Around");
    JButton SUB = new JButton("14-21-13-2-5-8");
    
    /****************************************************************************
    * THECIPHERGAME TOCAESAR FUNCTION
    * DESCRIPTION: Converts message using the Caesar Cipher.
    * PASSED TO THIS FUNCTION: 
        *    String message: The message to be encoded
        *    int key:        The amount of times each character is to be shifted 
        *                    to the right.     
    */
    String toCaesar(String message, int key) {
        String str = "";
        for(int i = 0; i < message.length(); i++) {
            char ch = message.charAt(i);
            if(Character.isLetter(ch)) {
                if(Character.isUpperCase(ch)) {
                    
                    int tmp = (int)ch + key;
                    while(tmp > (int)'Z') { 
                        int remainder = tmp - (int)'Z' - 1;
                        tmp = (int)'A' + remainder;
                    }
                    ch = (char)tmp;
                    
                } else {
                    int tmp = (int)ch + key;
                    while(tmp > (int)'z') { 
                        int remainder = tmp - (int)'z' - 1;
                        tmp = (int)'a' + remainder;
                    }
                    ch = (char)tmp;
                }
                str += ch;
            }
            else {
                str += ch;
            }
            
        }
        return str;
    }
    
    /****************************************************************************
    * THECIPHERGAME FROMCAESAR FUNCTION
    * DESCRIPTION: Decrypts an encoded message using the Caesarean cipher.
    * PASSED TO THIS FUNCTION: 
        *    String message: The message to be decoded
        *    int key:        The amount of times each character is to be shifted
        *                    to the left.     
    */
    String fromCaesar(String msg, int key) {
        String str = "";
        for(int i = 0; i < msg.length(); i++) {
            char ch = msg.charAt(i);
            if(Character.isLetter(ch)) {
                if(Character.isUpperCase(ch)) {
                    int tmp = (int)ch - key;
                    while(tmp < (int) 'A') {
                        int remainder = (int)'A' - tmp;
                        tmp = (int) 'Z' - remainder + 1;
                    }
                    ch = (char)tmp;
                } else {
                   int tmp = (int)ch - key;
                    while(tmp < (int) 'a') {
                        int remainder = (int)'a' - tmp;
                        tmp = (int) 'z' - remainder + 1;
                    }   
                    ch = (char)tmp; 
                }
                str += ch;
                
            } else {
                str += ch;
            }
        }
        return str;
    }
    
    /****************************************************************************
    * THECIPHERGAME TOABTASH FUNCTION
    * DESCRIPTION: Encodes a message using the Abtash cipher by flipping the 
    *              letters in the alphabet.
    * PASSED TO THIS FUNCTION: 
        *    String message: The message to be encoded.      
    */
    String toAbtash(String msg) {
        String str = "";
        for(int i = 0; i < msg.length(); i++) {
            char ch = msg.charAt(i);
            if(Character.isLetter(ch)) {
                if(Character.isUpperCase(ch)) {
                    
                    int tmp = (int)ch + 25;
                    while(tmp > 90) { 
                        int remainder = tmp - 90;
                        tmp = 90 - remainder;
                    }
                    ch = (char)tmp;
                    
                } else {
                    int tmp = (int)ch + 25;
                    while(tmp > 122) { 
                        int remainder = tmp - 122;
                        tmp = 122 - remainder;
                    }
                    ch = (char)tmp;
                }
                str += ch;
            }
            else {
                str += ch;
            }
            
        }
        return str;
    }
    
    /****************************************************************************
    * THECIPHERGAME TOAIZ26 FUNCTION
    * DESCRIPTION: Encodes message by substituting letters with numbers (where
    *              they appear in the alphabet).
    * PASSED TO THIS FUNCTION: 
        *    String message: The message to be encoded     
    */
    String toAIZ26(String msg) {
        String str = "";
        String[] arr = msg.split("\\s+");
        for (String iter : arr) {
            for(int i = 0; i < iter.length(); i++) {
                char ch = iter.charAt(i);
                if(Character.isLetter(ch)) {
                    int tmp = Character.isUpperCase(ch) 
                        ? (int)ch - 64 
                        : (int)ch - 96;
                        str += tmp + "-";        
                }
                else {
                    char tmp = str.charAt(str.length() - 1);
                    if(tmp == '-') {
                        str = str.substring(0, str.length() - 1);
                    }
                    str += ch;
                }
            }
            char tmp = str.charAt(str.length() - 1);
                if(tmp == '-') {
                    str = str.substring(0, str.length() - 1);
                }
            
            str += " ";
        }
        return str;
    }
    
    /****************************************************************************
    * THECIPHERGAME FROMAIZ26 FUNCTION
    * DESCRIPTION: Decrypts an encoded message using the AIZ26 cipher.
    * PASSED TO THIS FUNCTION: 
        *    String message: The message to be decoded      
    */
    String fromAIZ26(String msg) {
        String str = "";
        for(int i = 0; i < msg.length(); i++) {
            String tmp  = "";
            char ch = msg.charAt(i);
            if(Character.isDigit(ch)) {
                while(Character.isDigit(ch)) {
                    tmp += ch;
                    i++;
                    ch = msg.charAt(i);
                }
                int num = Integer.parseInt(tmp);
                num += 64;
                char c = (char) num;
                str += c;
                
                if(ch == '-') {
                    
                } 
                
                else {
                    str += ch;
                    
                }
            }
            else {
                if(ch == '-') {
                    
                } 
                
                else {
                    str += ch;
                    
                }
            }
        }
        return str;
    }
    
    public TheCipherGame() {
        setTitle("The Cipher Game");
        setSize(650,240);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        pnl.setLayout(new GridLayout(1,4,5,5));
        pnl.add(toCAESAR);
        pnl.add(fromCAESAR);
        pnl.add(ABTASH);
        pnl.add(SUB);
        
        
        mainPnl.setLayout(new GridLayout(2,1,5,5));
        mainPnl.add(txtFld);
        mainPnl.add(pnl);
        
        content.setLayout(new FlowLayout());
        content.add(mainPnl, BorderLayout.CENTER);
        
        toCAESAR.addActionListener(this);
        fromCAESAR.addActionListener(this);
        ABTASH.addActionListener(this);
        SUB.addActionListener(this);
        
        txtFld.setSize(WIDTH, HEIGHT);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        try {
            String msg = txtFld.getText();
            JButton btn = (JButton) e.getSource();
            if(btn == toCAESAR) {
                txtFld.setText(toCaesar(msg,3));
            }
            else if(btn == fromCAESAR) {
                txtFld.setText(fromCaesar(msg,3));
            }
            else if(btn == ABTASH ){
                txtFld.setText(toAbtash(msg));
            }
            else if (btn == SUB) {
                if(isEncoded) {
                    txtFld.setText(fromAIZ26(msg));
                    isEncoded = false;
                } else {
                    txtFld.setText(toAIZ26(msg));
                    isEncoded = true;
                }
                
            } 
            else {
                
            }
        } 
        catch(Exception ex) {
            
        } 
    }
    
    public static void main(String[] args) {
        TheCipherGame app = new TheCipherGame();
    } 
}