

public class VigenereBF {
	private Decryption d;
	private int tries;
	private KeyRing kr;
	private String plaintext;
	private DisplayWindow window;

	public VigenereBF (String ciphertext, String plaintext) {
		super();
		this.tries = 0;
		this.d = new Decryption(ciphertext);
		this.kr = new KeyRing();
		this.plaintext = plaintext;
		this.window = new DisplayWindow(ciphertext, plaintext, this);
	}

	

	public void decode(){			
		while(true){
			if(this.oneTry() == 1) break;
		}
	}

	public int oneTry(){
		tries++;
		String akey = kr.getKey();
		if(akey.contentEquals("TURING")) System.out.println("Passed it");
		if(akey.contentEquals("Done")) {
			window.setTextField("Key not Found!");
			return 1;
		}
		if(d.decrypt(akey).contentEquals(plaintext)){
			String temp = "Key Found: " + akey + " at " + tries + " tries.";
			window.setTextField(temp);
			System.out.println("Key Found: " + akey + " at " + tries + " tries.");
			return 1;
		}
		String temp = "Progress: "+tries+ "/308915776 \n"+ (308915776-tries)+ " Remaining.";
		window.setTextField(temp);

		return 0;
	}

	
	
	
	
	
	public int decodeDemo(String text) {//Test Method
		tries++;
		String akey = text.toUpperCase();
		if(akey.contentEquals("TURING")) System.out.println("Passed it");
		if(akey.contentEquals("Done")) {
			window.setTextField("Key not Found!");
			return 1;
		}
		if(d.decrypt(akey).contentEquals(plaintext)){
			String temp = "Key Found: " + akey + " at " + tries + " tries.";
			window.setTextField(temp);
			System.out.println("Key Found: " + akey + " at " + tries + " tries.");
			return 1;
		}
		
		String temp = "Progress: "+tries+ "/308915776 \n"+ (308915776-tries)+ " Remaining.";
		window.setTextField(temp);

		return 0;
		
		
	}



	public int getProgress() {
		return (tries / 308915776) * 100;
		
	}	
	
}


