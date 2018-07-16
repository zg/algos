/**
 * Basic trie implementation.
 * 
 * @author Zack Zatkin-Gold
 */
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Trie {
    private node root;

    public Trie() {
        root = new node(Character.MIN_VALUE);
    }

    public void insert(String str) {
        char[] chars = str.toCharArray();
        int idx = -1;
        int size = str.length();

        node n = root;
        while(true) {
            if(++idx >= size) break;
            char c = chars[idx];
            node newNode = new node(c);
            boolean f = false;
            for(node child : n.children) {
                if(child.c == c) {
                    n = child;
                    f = true;
                }
            }
            if(!f) {
                n.children.add(newNode);
                if(idx + 1 == size)
                    newNode.isWord = true;
                n = newNode;
            }
        }
    }

    public boolean contains(String word) {
        return getWords().contains(word);
    }

    private HashSet<String> getWords(String prefix, node n) {
        HashSet<String> words = new HashSet<>();
        if(n.isWord) words.add((prefix + n.c).trim());
        for(node child : n.children) {
            words.addAll(getWords(prefix + n.c, child));
        }
        return words;
    }

    public HashSet<String> getWords() {
        return getWords("", root);
    }

    private String toString(node n, int depth) {
        String indent = "";
        for(int i = 0; i < depth; i += 1) {
            indent = indent + "  ";
        }
        String ret = indent + n.c + "\n";
        for(node child : n.children) {
            ret = ret + toString(child, depth + 1);
        }
        return ret;
    }

    public String toString() {
        return toString(root, 0);
    }

    private class node {
        public Character c;
        public LinkedList<node> children = new LinkedList<>();
        public boolean isWord = false;

        public node(Character c) {
            this.c = c;
        }

        public node(String str) {
            this.c = str.toCharArray()[0];
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        System.out.println(trie.containsWord("foo"));
        trie.insert("tear");
        trie.insert("tell");
        trie.insert("television");
        trie.insert("foam");
        trie.insert("foo");
        trie.insert("oath");
        trie.insert("oats");
//        System.out.println(trie);
//        System.out.println(trie.getWords());
        System.out.println(trie.containsWord("tear"));
        System.out.println(trie.containsWord("v"));
    }
}
