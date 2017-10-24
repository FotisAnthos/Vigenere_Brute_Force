import java.util.concurrent.TimeUnit;

public class main {

	public static void main(String[] args) {
		String key = "TURING"; //
		String plaintext = "CONGRATULATIONSYOUSUCEEDINDECRYPTINGTHISMESSAGEITWASNOTTOOHARDAFTERALLKEEPUPTHEGOODWORKANDSPENDMORETIMEWITHCRYPTOOLANDSTUDYCAREFULLYTHEAVAILABLEBOOKSANDDONOTFORGETTHATHEBIGGESTBOOKISINTHEINTERNET";
        Encryption e = new Encryption(plaintext, key);
        String enc = e.encrypt(plaintext, key);
        //System.out.println("Ciphertext:\n" + enc);
        System.out.println("-------------------\n");
        
        long startTime = System.currentTimeMillis();
        //quickTest(enc, plaintext);
        
        VigenereBF bf = new VigenereBF(enc, plaintext);
        bf.decode();
        
        
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("-------------------\nElapsed time od Decoding in milliseconds: " + totalTime);
        System.out.println("-------------------\nElapsed time od Decoding in secs: " + TimeUnit.MILLISECONDS.toMinutes(totalTime));
        System.out.println("-------------------\nElapsed time od Decoding in hours: " + TimeUnit.MILLISECONDS.toHours(totalTime));
	}
	
	private static void quickTest(String enc, String plaintext) {
		
        VigenereBF bf2 = new VigenereBF(enc, plaintext);
        bf2.decodeDemo("tttttt");
        System.out.println("0");
        bf2.decodeDemo("aaaaaa");
        System.out.println("1");
        bf2.decodeDemo("turinn");
        System.out.println("2");
        bf2.decodeDemo("turing");
        System.out.println("Bingo?");
		
	}
	
 
    

}
