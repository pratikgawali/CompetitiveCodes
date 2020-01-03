package com.datastructure;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;


/*
---------------------------------------------------------------------------
Trie DataStructure
---------------------------------------------------------------------------
A trie is a tree-like data structure whose nodes store the letters of an 
alphabet. By structuring the nodes in a particular way, words and strings 
can be retrieved from the structure by traversing down a branch path of 
the tree.
---------------------------------------------------------------------------
*/
public class Trie {
	
	private TrieNode root;
	
	public Trie() {
		root = new TrieNode();
	}
	
	public TrieNode getRoot() {
		return root;
	}

	public void addWord(String word) {
			
		TrieNode currNode = root;
		for(Character c: word.toCharArray()) {
			TrieNode nextNode = currNode.nextTrieNode(c);
			if (nextNode == null) {
				nextNode = currNode.addChild(c);
		 	}
			currNode = nextNode;
		}
		currNode.isFinal = true;
	}

	public void deleteWord(String word) {

		Deque<TrieNode> stack = new ArrayDeque<>();
		TrieNode currNode = root;
		char[] charArray = word.toCharArray();
		
		for (int i=0; i<charArray.length; i++) {
			if (currNode == null) {
				return;
			}
			stack.push(currNode);
			currNode = currNode.nextTrieNode(charArray[i]);
		}
		
		if (currNode == null || !currNode.isFinal) {
			return;
		}
		
		for(int i = charArray.length-1; i>=0; i--) {
			TrieNode trieNode = stack.pop();
			TrieNode nextTrieNode = trieNode.nextTrieNode(charArray[i]);
			if (!nextTrieNode.hasChild()) {
				trieNode.removeChild(charArray[i]);
			}
		}
	}

	public boolean hasPrefix(String prefix) {
		
		TrieNode currNode = root;
		for(Character c: prefix.toCharArray()) {
			TrieNode nextNode = currNode.nextTrieNode(c);
			if (nextNode == null) {
				return false;
			}
			currNode = nextNode;
		}
		
		return true;
	}

	public boolean hasWord(String word) {

		TrieNode currNode = root;
		for(Character c: word.toCharArray()) {
			TrieNode nextNode = currNode.nextTrieNode(c);
			if (nextNode == null) {
				return false;
			}
			currNode = nextNode;
		}
		
		return currNode.isFinal;
	}

	public TrieNode lookUp(String lookUpString) {
		
		TrieNode currNode = root;
		for(Character c: lookUpString.toCharArray()) {
			TrieNode nextNode = currNode.nextTrieNode(c);
			if (nextNode == null) {
				return null;
			}
			currNode = nextNode;
		}
		return currNode;
	}

	private class TrieNode {

		private Map<Character, TrieNode> charMap;
		private boolean isFinal;
		
		public TrieNode() {
			charMap = new HashMap<>();
			isFinal = false;
		}

		public TrieNode nextTrieNode(Character c) {
			return charMap.get(c);
		}
		
		public TrieNode addChild(Character c) {
			
			TrieNode childNode = new TrieNode();
			charMap.put(c, childNode);
			return childNode;
		}
		
		public void removeChild(Character c) {
			charMap.remove(c);
		}
		
		public boolean hasChild() {
			return !charMap.isEmpty();
		}
	}
	
	public static void main(String[] args) {
		
		Trie trie = new Trie();
		
		System.out.println("Adding words: \"pratik\" and \"prachi\"");
		trie.addWord("pratik");
		trie.addWord("prachi");
		System.out.println();
		
		System.out.println("Does \"pra\" prefix exists?: " + trie.hasPrefix("pra"));
		System.out.println("Does \"prw\" prefix exists?: " + trie.hasPrefix("prw"));
		System.out.println();
		
		System.out.println("Does \"prachi\" word exists?: " + trie.hasWord("prachi"));
		System.out.println("Does \"praful\" word exists?: " + trie.hasWord("praful"));
		System.out.println();
		
		System.out.println("Deleting word \"prachi\"");
		trie.deleteWord("prachi");
		System.out.println("Does \"prachi\" word exists?: " + trie.hasPrefix("prachi"));
	}
}