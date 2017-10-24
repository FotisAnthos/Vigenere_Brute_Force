import java.awt.*;
import javax.swing.*;

public class VigenereBF {
	private String ciphertext;
	private Decryption d;
	private int tries;
	private KeyRing kr;
	private String plaintext;
	private JFrame frame;
	private Container contentPane;
	private JTextField progressTextField;

	public VigenereBF (String ciphertext, String plaintext) {
		super();
		this.tries = 0;
		this.ciphertext = ciphertext;
		this.d = new Decryption(ciphertext);
		this.kr = new KeyRing();
		this.plaintext = plaintext;
		init_gui();
	}

	private void init_gui(){
		

		    frame = new JFrame();
		    frame.setBounds(200, 200, 600, 200);
		    frame.setTitle("Attack Progress");
		    
		    contentPane = frame.getContentPane();
		    contentPane.setLayout(new BorderLayout());
		    
		    progressTextField = new JTextField("");
		    contentPane.add(progressTextField);
		    

		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.setVisible(true);
		
		
	}

	public void decode(){
		
		while(true){
			if(this.oneTry() == 1) break;
		}
	}

	public int oneTry(){
		String akey = kr.getKey();
		if(akey.equals("Done")) return 1;
		if(d.decrypt(akey).equals(plaintext)){
			progressTextField.setText("Key Found: " + akey + " at " + tries + " tries.");
			System.out.println("Key Found: " + akey + " at " + tries + " tries.");
			return 1;
		}
		tries++;
		progressTextField.setText("Progress: "+tries+ "/308915776 \n"+ (308915776-tries)+ " Remaining.");

		return 0;
	}	
	
}


