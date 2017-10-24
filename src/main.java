import java.util.concurrent.TimeUnit;

public class main {

	public static void main(String[] args) {
		String key = "TURING"; //
		String plaintext = "CONGRATULATIONSYOUSUCEEDINDECRYPTINGTHISMESSAGEITWASNOTTOOHARDAFTERALLKEEPUPTHEGOODWORKANDSPENDMORETIMEWITHCRYPTOOLANDSTUDYCAREFULLYTHEAVAILABLEBOOKSANDDONOTFORGETTHATHEBIGGESTBOOKISINTHEINTERNET";
        Encryption e = new Encryption(plaintext, key);
        String ciphertext = e.encrypt(plaintext, key);
        //System.out.println("Ciphertext:\n" + enc);
        System.out.println("-------------------\n");
        
        long startTime = System.currentTimeMillis();
        
        new VigenereBF(ciphertext, plaintext);
        
        
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("-------------------\nElapsed time od Decoding in milliseconds: " + totalTime);
        System.out.println("-------------------\nElapsed time od Decoding in secs: " + TimeUnit.MILLISECONDS.toMinutes(totalTime));
        System.out.println("-------------------\nElapsed time od Decoding in hours: " + TimeUnit.MILLISECONDS.toHours(totalTime));
	}
	
	
	
 
    

}
