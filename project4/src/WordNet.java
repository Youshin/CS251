import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public final class WordNet {
    // constructor takes the name of the two input files
    private Hashtable<String, Bag<Integer>> synsetsToId;
    private ArrayList<String> synsetList;
    SAP sap;

    public WordNet(String synsets, String hypernyms) {
        synsetsToId = new Hashtable<String, Bag<Integer>>();
        synsetList = new ArrayList<String>();
        In in = new In(synsets);
        int numLine = 1;
        //StdOut.println("syn");
        while (!in.isEmpty()) {
            String[] splitString = in.readLine().split(",");
            int id = Integer.parseInt(splitString[0]) - 1;
            this.synsetList.add(id, splitString[1]);
            String[] wordsArray = splitString[1].split(" ");
            for (String w : wordsArray) {
                Bag tmp;
                if (this.synsetsToId.containsKey(w)) {
                    tmp = this.synsetsToId.get(w);
                } else {
                    tmp = new Bag<Integer>();
                }
                tmp.add(id);
                this.synsetsToId.put(w, tmp);
            }
            numLine++;
        }
        //StdOut.println("hyp");
        In in1 = new In(hypernyms);
        Digraph digraph = new Digraph(numLine);
        while (!in1.isEmpty()) {
            String s = in1.readLine();
            String[] splitString = s.split(",");
            for (int i = 1; i < splitString.length; i++) {
                digraph.addEdge(Integer.parseInt(splitString[0]) - 1, Integer.parseInt(splitString[i]) - 1);
            }
        }
        //StdOut.println("sap");
        sap = new SAP(digraph);
        //StdOut.println("sap end");
        in.close();
        in1.close();
    }

    // is the word a WordNet noun? This can be used to search for existing
    // nouns at the beginning of the printSap method
    public boolean isNoun(String word) {
        return synsetsToId.containsKey(word);
    }

    // print the synset (second field of synsets.txt) that is the common ancestor
    // of nounA and nounB in a shortest ancestral path as well as the length of the path,
    // following this format: "sap<space>=<space><number>,<space>ancestor<space>=<space><synsettext>"
    // If no such path exists the sap should contain -1 and ancestor should say "null"
    // This method should use the previously defined SAP datatype
    public void printSap(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            StdOut.println("sap = -1, ancestor = null");
        } else {

            Bag<Integer> v = this.synsetsToId.get(nounA);
            Bag<Integer> w = this.synsetsToId.get(nounB);

            //StdOut.println("v :"+ v);
            //StdOut.println("w :"+ w);

            int root = this.sap.ancestor(v, w);
            int length = this.sap.length(v, w);
            if (root == -1 || length == -1) {
                StdOut.println("sap = -1, ancestor = null");
            } else {
                StdOut.println("sap = " + length + " ancestor = " + this.synsetList.get(root));
            }
        }
    }

    public static void main(String args[]) {
        String synsets = args[0];
        String hypernyms = args[1];
        WordNet d = new WordNet(synsets, hypernyms);
        int i = 0;
        //StdOut.println("start while");
        In in = new In(args[2]);
        while (!in.isEmpty()) {
            //  StdOut.println("continue");
            String v = in.readString();
            String w = in.readString();
            //StdOut.println(v+" "+w);
            d.printSap(v, w);
            //  StdOut.println("printed");
        }
    }

}