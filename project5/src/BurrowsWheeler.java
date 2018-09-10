import java.util.*;

public class BurrowsWheeler {

    // alphabet size of extended ASCII
    private static final int R = 256;

    // apply Burrows-Wheeler encoding, reading from standard input and writing to standard output
    public static void encode() {
        String encoded = "";
        String original = BinaryStdIn.readString();
//        String original = "ABRACADABRA!";
        String s = original;
        String[] strings = new String[s.length()];
        strings[0] = s;
        for(int i = 1; i < s.length(); i++) {
            s = s.substring(1, s.length()) + s.charAt(0);
            strings[i] = s;
        }
        Arrays.sort(strings);

        for(int i = 0; i < s.length(); i++) {
            encoded += strings[i].charAt(s.length()-1);
        }
        //StdOut.println((int)'A');
//        for(int i = 0; i < s.length(); i++) {
//            StdOut.println(strings[i]);
//        }
        int index = Arrays.binarySearch(strings, original);
        BinaryStdOut.write(index);
        BinaryStdOut.write(encoded);
        BinaryStdOut.flush();
        BinaryStdOut.close();
        BinaryStdIn.close();
    }
    // apply Burrows-Wheeler decoding, reading from standard input and writing to standard output
    public static void decode() {
        int index = BinaryStdIn.readInt();
        String encoded = BinaryStdIn.readString();
//        int index = 3;
//        String encoded = "ARD!RCAAAABB";
        int[] next = new int[encoded.length()];
        HashMap<Character, Queue<Integer>> map = new HashMap<Character, Queue<Integer>>();
        char[] chars = new char[encoded.length()];
        char[] sorted;

        for(int i = 0; i < encoded.length();i++) {
            chars[i] = encoded.charAt(i);
        }
        sorted = Arrays.copyOf(chars,chars.length);
        Arrays.sort(sorted);


        for (int i = 0; i < chars.length; i++) {
            if(!map.containsKey(chars[i])) {
                map.put(chars[i], new Queue<Integer>());
            }
            map.get(chars[i]).enqueue(i);
        }
        for (int i = 0; i < sorted.length; i++) {
            next[i] = map.get(sorted[i]).dequeue();
        }
        int nextI = index;
        for (int i = 0; i < sorted.length; i++) {
            //StdOut.print(sorted[nextI]);
            BinaryStdOut.write(sorted[nextI]);
            nextI = next[nextI];
        }
        //BinaryStdOut.write('\n');
        BinaryStdOut.flush();
        //StdOut.println();
        BinaryStdOut.close();
        BinaryStdIn.close();

    }


    // if args[0] is '-', apply Burrows-Wheeler encoding
    // if args[0] is '+', apply Burrows-Wheeler decoding
    public static void main(String[] args)
    {
//        Scanner s = new Scanner(System.in);
//        StdOut.printf("Enter input : ");
//        String a = s.next();
//        if(a.equals("-")) {
//            encode();
//        }else {
//            decode();
//        }
        if (args[0].equals("-")) {
            encode();
        }else if (args[0].equals("+")) {
            decode();
        }
    }
}