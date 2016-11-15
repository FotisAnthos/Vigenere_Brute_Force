
public class main {

	public static void main(String[] args) {
		String key = "TURING"; //
		String plaintext = "CONGRATULATIONSYOUSUCEEDINDECRYPTINGTHISMESSAGEITWASNOTTOOHARDAFTERALLKEEPUPTHEGOODWORKANDSPENDMORETIMEWITHCRYPTOOLANDSTUDYCAREFULLYTHEAVAILABLEBOOKSANDDONOTFORGETTHATHEBIGGESTBOOKISINTHEINTERNET";
        Encryption e = new Encryption(plaintext, key);
        String enc = e.encrypt(plaintext, key);
        System.out.println("Cyphertext:\n" + enc);
        System.out.println("-------------------\n");
        long startTime = System.currentTimeMillis();
        
        VigenereBF bf = new VigenereBF(enc, plaintext);
        bf.decode();
        
        
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("-------------------\nElapsed time od Decoding in milliseconds: " + totalTime);
        
	}
	
 
    

}