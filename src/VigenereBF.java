import java.awt.*;
import java.util.ArrayList;

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
	private Helper h1, h2, h3;


	public VigenereBF (String ciphertext, String plaintext) {
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
		this.h1 = new Helper("h1", plaintext, ciphertext, kr, d, this);
		this.h2 = new Helper("h2", plaintext, ciphertext, kr, d, this);
		this.h3 = new Helper("h3", plaintext, ciphertext, kr, d, this);




	}

	public void updateTextField() {
		tries++;
		progressTextField.setText("Progress: "+tries+ "/308915776 \n"+ (308915776-tries)+ " Remaining.");
	}
	public void decryptionComplete(String key) {
		progressTextField.setText("Key Found: " + key + " at " + tries + " tries.");
		System.out.println("Key Found: " + key + " at " + tries + " tries.");
		try {
			h1.stops();
		}
		finally {
			try {
				h2.stops();
			}
			finally {


				try {
					h3.stops();
				}
				finally {
					progressTextField.setSelectionColor(Color.RED);
				}
			}
		}
	}

	public void helperEnd(Helper helper) {
		helper.stops();
	}





}

class Helper implements Runnable{

	private String name;
	private String plaintext;
	//private String ciphertext;
	private KeyRing kr;
	private Decryption d;
	private VigenereBF vigi;
	private boolean flag;
	private Thread t;

	public Helper(String name, String plaintext, String ciphertext, KeyRing kr, Decryption d, VigenereBF vigi) {
		super();
		flag = false;
		this.name = name;
		this.plaintext = plaintext;
		//this.ciphertext = ciphertext;
		this.kr = kr;
		this.d = d;
		this.vigi = vigi;

	}
	public void threadStart() {
		t = new Thread(this, name);
		t.start();
	}


	@Override
	public void run() {
		while(true){
			if(flag == false)
				oneTry();
			else return;
		}


	}
	public void stops() {
		flag = true;//signals when helper must stop
	}


	public void oneTry(){
		String akey = kr.getKey(this.name);
		if(akey.equals("End")) flag = true;
		if(akey.equals("Done")) vigi.decryptionComplete(akey);
		if(d.decrypt(akey).equals(plaintext)){
			vigi.decryptionComplete(akey);
			flag = true;
		}
		vigi.updateTextField();
		flag = false;
	}	

}


