

public class MoveToFront {

    private static final int R = 256;

    // apply move-to-front encoding, reading from standard input and writing to standard output
	public static void encode() {
	    String original = BinaryStdIn.readString();
        //String original = "ABRACADABRA!";
        char[] input = original.toCharArray();

        int[] unique = new int[R];
        for (int i = 0; i < R; i++) {
            unique[i] = i;
        }
        for(int i = 0; i < original.length();i++) {
            for(int j = 0; j < unique.length;j++) {
                if((char)unique[j] == input[i]) {
                    BinaryStdOut.write((char)j);
                    for(int k = j; k > 0; k--){
                        unique[k] = unique[k-1];
                    }
                    unique[0] = input[i];
                }
            }
        }
        BinaryStdOut.flush();
        BinaryStdOut.close();
        BinaryStdIn.close();

    }
	// apply move-to-front decoding, reading from standard input and writing to standard output
	public static void decode()	{
        int[] unique = new int[R];
        for (int i = 0; i < R ; i++) {
            unique[i] = i;
        }
        while (!BinaryStdIn.isEmpty()) {
            int input = BinaryStdIn.readChar(8);
            BinaryStdOut.write(unique[input],8);
            char i = (char)unique[input];
            for(int k = input; k > 0; k--){
                unique[k] = unique[k-1];
            }
            unique[0] = i;
        }
        //BinaryStdOut.write('\n');
        BinaryStdOut.flush();
        BinaryStdOut.close();
        BinaryStdIn.close();
	}
	// if args[0] is '-', apply move-to-front encoding
	// if args[0] is '+', apply move-to-front decoding
	public static void main(String[] args)
	{
        if (args[0].equals("-")) {
            encode();
        }else if (args[0].equals("+")) {
            decode();
        }
	}
}