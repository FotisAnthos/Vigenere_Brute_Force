import java.util.concurrent.TimeUnit;

public class VigenereBF {
	private String ciphertext;
	private Decryption d;
	private int tries;
	private KeyRing kr;
	private String plaintext;
	
	private Helper h1, h2, h3;
	private Thread[] threads;
	private int numThreads;
	private Helper[] allHelpers;
	private gui g;
	private long totalTime;
	


	public VigenereBF (String ciphertext, String plaintext) {
		this.tries = 0;
		this.ciphertext = ciphertext;
		this.d = new Decryption(ciphertext);
		this.kr = new KeyRing();
		//this.kr.debug();to reach turing faster 
		this.plaintext = plaintext;
		this.numThreads = 3; //for this program, for no real reason
		this.threads = new Thread[3];

		this.g = new gui();

	}



	public void decode() {
		long startTime = System.currentTimeMillis();
		//Create Helpers
		this.allHelpers = new Helper[4];
		this.h1 = new Helper("h1", plaintext, ciphertext, kr, d, this);
		allHelpers[0] = this.h1;
		this.h2 = new Helper("h2", plaintext, ciphertext, kr, d, this);
		allHelpers[1] = this.h2;
		this.h3 = new Helper("h3", plaintext, ciphertext, kr, d, this);
		allHelpers[2] = this.h3;
		
		/* create threads */

		threads[0] = new Thread(h1, "h1");
		threads[1] = new Thread(h2, "h2");
		threads[2] = new Thread(h3, "h3");
		

		/* start them up */

		System.out.println("starting threads");

		for (int i = 0; i < numThreads; ++i) {
			threads[i].setPriority(Thread.MIN_PRIORITY);
			threads[i].start();
		}

		/* wait for them to finish */

		for (int i = 0; i < numThreads; ++i) {
			try {
				threads[i].join();
			}
			catch (InterruptedException e) {
				System.err.println("this should not happen");
			}
		}

		System.out.println("threads all done");
		long endTime   = System.currentTimeMillis();
		totalTime = endTime - startTime;


	}

	public void updateTextField(String akey, String name) {//beep beep
		tries++;
		g.setText(akey, name);
	}
	
	public void decryptionComplete(String key, String name) {
		key = "Key Found: " + key + " at " + tries + " tries.";
		g.setText(key, name);
		
		System.out.println("Key Found: " + key + " at " + tries + " tries. Time: " + TimeUnit.MILLISECONDS.toMinutes(totalTime) + "mins");
		
		for (int i = 0; i < numThreads; ++i) {
			allHelpers[i].stops();
		}
	}

	public void helperEnd(Helper helper) {
		helper.stops();
	}





}

class Helper implements Runnable{

	private String name;
	private String plaintext;;
	private KeyRing kr;
	private Decryption d;
	private VigenereBF vigi;
	private boolean flag;
	

	public Helper(String name, String plaintext, String ciphertext, KeyRing kr, Decryption d, VigenereBF vigi) {
		super();
		flag = false;
		this.name = name;
		this.plaintext = plaintext;
		this.kr = kr;
		this.d = d;
		this.vigi = vigi;

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
		vigi.updateTextField(akey, name);
		if(akey.equals("TURING")) System.out.println("TURING reached");
		if(akey.equals("End")) flag = true;//to end thread
		//if(akey.equals("Done")) vigi.decryptionComplete(akey, name);
		if(d.decrypt(akey).equals(plaintext)){
			vigi.decryptionComplete(akey, name);
			flag = true;
		}
	}	
}



