/*******************************************************************************
 *
 *  This file is part of TheCipherGame.
 * 
 *
 *  TheCipherGame was developed by Brian Springer
 *
 *  The CipherGame was made simply for fun. So give it a try. 
 *
 ******************************************************************************/
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
import javax.swing.JTextField;


public class TheCipherGame extends JFrame  implements ActionListener {
    
    // Program Fields
    boolean isEncoded;
    
    // GUI Fields
    
    Container content = this.getContentPane();
    
    JPanel pnl = new JPanel();
    JPanel mainPnl = new JPanel();
    JPanel vigenerePnl = new JPanel();
    
    JTextArea txtFld = new JTextArea();
    JTextField cipherKeyTxtFld = new JTextField();
    
    JButton toCAESAR = new JButton("Hide Your Message");
    JButton fromCAESAR = new JButton("Uncover a Secret");
    JButton ABTASH = new JButton("Flip it Around");
    JButton SUB = new JButton("14-21-13-2-5-8");
    JButton ENCODEVIGENERE = new JButton("Vigenere Encode");
    JButton DECODEVIGENERE = new JButton("Vigenere Decode");
    
    /***************************************************************************
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
    
    /***************************************************************************
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
    
    /***************************************************************************
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
    
    /***************************************************************************
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
    
    /***************************************************************************
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
    
    /***************************************************************************
    * THECIPHERGAME FORMATKEY FUNCTION
    * DESCRIPTION: Expands the length of the cipher key so that it contains
    *              the same amount of alphabetical characters as the message to
    *              be encoded.
    * PASSED TO THIS FUNCTION: 
        *    @param msg: The message whose length the key needs to be.
        *    @param  key: The cipher key.     
        
    */
    public String formatKey(String msg, String key) {
        String tmp = "";
        int count = 0;
        for(int i = 0; i < msg.length(); i++) {
            char ch = msg.charAt(i);
            if(Character.isAlphabetic(ch)) {
                if(count < key.length()) {
                    tmp += key.charAt(count);
                    count++;
                }
                else {
                    count = 0;
                    tmp += key.charAt(count);
                    count++;
                }
            }
        }
        return tmp;
    }
    
    /***************************************************************************
    * THECIPHERGAME CREATEVIGENERESQUARE FUNCTION
    * DESCRIPTION: Creates a 2D char array where the initial character of each
    *              row is offset by one, before it loops around to the 
    *              beginning.
    */
    public char[][] createVigenereSquare() {
        char[][] arr = new char[26][26];
        int row = 0;
        
        for(char i = 'A'; i <= 'Z'; i++) {
            
            char ch = i;
            
            for(int col = 0; col < 26; col++) {
              arr[row][col] = ch;
              ch++;
              if(ch > 'Z') {
                  ch = 'A';
              }
            }
            row++;
        }
        return arr;
    }
    
    /***************************************************************************
    * THECIPHERGAME GETALPHABETINDEX FUNCTION
    * DESCRIPTION: Returns the place in the alphabet the character is located.
    * PASSED TO THIS FUNCTION: 
        *    @param ch: The character who index needs to be found.
    */
    public int getAlphabetIndex(char ch) {
        int count = 0;
        for(char i = 'A'; i < ch; i++ ) {
            count++;
        }
        return count;
    }
    
    /***************************************************************************
    * THECIPHERGAME TOVIGENERE FUNCTION
    * DESCRIPTION: Encodes a message using the Vigenere cipher.
    * PASSED TO THIS FUNCTION: 
        *    @param msg: The message to be encoded.
        *    @param key: The key that encodes it.
    */
    public String toVigenere(String msg, String key) {
        String formattedKey = formatKey(msg, key);
        char[][] vigenereSquare = createVigenereSquare();
        String tmp = "";
        int i = 0;
        int count = 0;
        while(count < formattedKey.length()) {
            int row  = getAlphabetIndex(msg.charAt(i));
            int col = getAlphabetIndex(formattedKey.charAt(count));
            
            if(Character.isAlphabetic(msg.charAt(i))) {
                tmp += vigenereSquare[row][col];
                i++;
                count++;
            }
            else {
                tmp += msg.charAt(i);
                i++;
            }
        } 
        return tmp;
    }
    
    /***************************************************************************
    * THECIPHERGAME FROMVIGENERE FUNCTION
    * DESCRIPTION: Decodes a message using the Vigenere cipher.
    * PASSED TO THIS FUNCTION: 
        *    @param msg: The message to be decoded.
        *    @param key: The key that decodes it.
    */
    public String fromVigenere(String msg, String key) {
        String formattedKey = formatKey(msg, key);
        char[][] vigenereSquare = createVigenereSquare();
        String tmp = "";
        int i = 0;
        int j = 0;
        
        while(j < formattedKey.length()) {
            
            if(Character.isAlphabetic(msg.charAt(i))) {
                int col = getAlphabetIndex(formattedKey.charAt(j));
                int count = 0;
            
                char ch = msg.charAt(i);
                char var = vigenereSquare[count][col];
            
                while(var != ch) {
                    count++;
                    var = vigenereSquare[count][col];
                }
            
                tmp += vigenereSquare[count][0];
                j++;
            }
            else {
                tmp += msg.charAt(i);
            }
            i++;
        }
        
        return tmp;
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
                } 
                else {
                    txtFld.setText(toAIZ26(msg));
                    isEncoded = true;
                }
                
            } 
            else if (btn == ENCODEVIGENERE) {
                String key = cipherKeyTxtFld.getText();
                txtFld.setText(toVigenere(msg, key));
            }
            
            else if (btn == DECODEVIGENERE) {
                String key = cipherKeyTxtFld.getText();
                txtFld.setText(fromVigenere(msg, key));
            }
            else {
                
            }
        } 
        catch(Exception ex) {
            
        } 
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
        
        vigenerePnl.setLayout(new GridLayout(1,3,5,5));
        vigenerePnl.add(ENCODEVIGENERE);
        vigenerePnl.add(DECODEVIGENERE);
        vigenerePnl.add(cipherKeyTxtFld);
        
        mainPnl.setLayout(new GridLayout(3,1,5,5));
        mainPnl.add(txtFld);
        mainPnl.add(vigenerePnl);
        mainPnl.add(pnl);
        
        content.setLayout(new FlowLayout());
        content.add(mainPnl, BorderLayout.CENTER);
        
        toCAESAR.addActionListener(this);
        fromCAESAR.addActionListener(this);
        ABTASH.addActionListener(this);
        SUB.addActionListener(this);
        ENCODEVIGENERE.addActionListener(this);
        DECODEVIGENERE.addActionListener(this);
        
        txtFld.setSize(WIDTH, HEIGHT);
    }
     
    
    public static void main(String[] args) {
        TheCipherGame app = new TheCipherGame();
    } 
}
