import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Given a book of words. Assume you have enough main memory to accommodate 
all words. Design a data structure to find top K maximum occurring words. 
The data structure should be dynamic so that new words can be added.
---------------------------------------------------------------------------
*/
public class KMostFrequentWords {

	/**
	 * Finds the k most frequent words in the given text content.
	 * 
	 * @param content
	 * @param k
	 */
	private static void kMostFrequentWords(String content, Integer k) {

		Trie trie = new Trie();
		KMinHeap minHeap = new KMinHeap(k);

		String[] words = content.split("\\s+|\\.\\s*");
		for (String word : words) {

			Trie.TrieNode trieNode = trie.addWord(word);
			Integer heapIndex = trieNode.getHeapIndex();
			if (heapIndex != -1) { // word already in min heap
				minHeap.heapifyDown(heapIndex);
			} else {
				KMinHeap.HeapNode node = minHeap.createHeapNode(word, trieNode);
				if (!minHeap.isFull()) { // space available in heap for new node
					minHeap.addHeapNode(node);
				} else if (node.compareTo(minHeap.peekMinHeapNode()) > 0) { // more frequent word is seen
					minHeap.updateMinHeapNode(node);
				}
			}
		}

		System.out.println(k + " most frequent words are (low to high): ");
		printMinHeapSorted(minHeap);
	}

	/**
	 * Prints the minimum heap in sorted form.
	 * 
	 * @param minHeap
	 */
	private static void printMinHeapSorted(KMinHeap minHeap) {

		while (!minHeap.isEmpty()) {
			System.out.println(minHeap.popMinHeapNode().word);
		}
	}

	public static void main(String[] args) {

		String content = "Pratik is a very good boy. Pratik is very punctual. Pratik is kind.";
		Integer k = 3;

		kMostFrequentWords(content, k);
	}
}

/**
 * Min heap of size k to store k most frequent words. Each node of the heap
 * stores a word and its reference in Trie.
 * 
 * @author Pratik
 *
 */
class KMinHeap {

	private HeapNode[] minHeap;
	private Integer size;

	public KMinHeap(Integer k) {
		minHeap = new HeapNode[k];
		size = 0;
	}

	public HeapNode createHeapNode(String word, Trie.TrieNode trieNode) {
		return new HeapNode(word, trieNode);
	}

	public HeapNode peekMinHeapNode() {
		return minHeap[0];
	}

	public HeapNode popMinHeapNode() {
		HeapNode minHeapNode = minHeap[0];
		minHeap[0] = minHeap[--size];
		minHeap[size] = null;
		heapifyDown(0);
		return minHeapNode;
	}

	public boolean isFull() {
		return size == minHeap.length;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean addHeapNode(HeapNode node) {

		if (isFull()) { // cannot add if heap is full
			return false;
		}

		minHeap[size] = node;
		minHeap[size].trieNode.setHeapIndex(size);
		heapifyUp(size++);
		return true;
	}

	public void heapifyDown(Integer rootIndex) {

		Integer swapIndex = getSwapIndex(rootIndex);
		if (rootIndex != swapIndex) {
			swap(rootIndex, swapIndex);
			heapifyDown(swapIndex);
		}
	}

	public void heapifyUp(Integer rootIndex) {

		if (rootIndex == 0) {
			minHeap[0].trieNode.setHeapIndex(0);
			return;
		}
		Integer parentIndex = rootIndex >> 1;
		if (minHeap[rootIndex].compareTo(minHeap[parentIndex]) < 0) {
			swap(rootIndex, parentIndex);
			heapifyUp(parentIndex);
		}
	}

	public void updateMinHeapNode(HeapNode withNode) {
		minHeap[0].trieNode.setHeapIndex(-1);
		minHeap[0] = withNode;
		withNode.trieNode.setHeapIndex(0);
		heapifyDown(0);
	}

	private void swap(Integer i, Integer j) {
		HeapNode temp = minHeap[i];
		minHeap[i] = minHeap[j];
		minHeap[j] = temp;

		minHeap[i].trieNode.setHeapIndex(i);
		minHeap[j].trieNode.setHeapIndex(j);
	}

	private Integer getSwapIndex(Integer rootIndex) {

		Integer swapIndex = rootIndex;
		Integer childIndex = (rootIndex << 1) + 1;
		if (childIndex < size && minHeap[childIndex].compareTo(minHeap[rootIndex]) < 0) {
			swapIndex = childIndex;
		}
		if (childIndex + 1 < size && minHeap[childIndex + 1].compareTo(minHeap[rootIndex]) < 0
				&& minHeap[childIndex + 1].compareTo(minHeap[childIndex]) < 0) {
			swapIndex = childIndex + 1;
		}

		return swapIndex;
	}

	class HeapNode implements Comparable<HeapNode> {

		String word;
		Trie.TrieNode trieNode;

		public HeapNode(String word, Trie.TrieNode trieNode) {
			this.word = word;
			this.trieNode = trieNode;
		}

		@Override
		public int compareTo(HeapNode withHeapNode) {
			return trieNode.getFrequency().compareTo(withHeapNode.trieNode.getFrequency());
		}
	}
}

/**
 * Trie to store words encountered. Each node of a Trie stores the frequency of
 * word as well as its reference in KMinHeap.
 * 
 * @author Pratik
 *
 */
class Trie {

	private TrieNode root;

	public Trie() {
		root = new TrieNode();
	}

	public TrieNode getRoot() {
		return root;
	}

	public TrieNode addWord(String word) {

		TrieNode currNode = root;
		for (Character c : word.toCharArray()) {
			TrieNode nextNode = currNode.nextTrieNode(c);
			if (nextNode == null) {
				nextNode = currNode.addChild(c);
			}
			currNode = nextNode;
		}
		currNode.isFinal = true;
		currNode.frequency++;
		return currNode;
	}

	public void deleteWord(String word) {

		Deque<TrieNode> stack = new ArrayDeque<>();
		TrieNode currNode = root;
		char[] charArray = word.toCharArray();

		for (Integer i = 0; i < charArray.length; i++) {
			if (currNode == null) {
				return;
			}
			stack.push(currNode);
			currNode = currNode.nextTrieNode(charArray[i]);
		}

		if (currNode == null || !currNode.isFinal) {
			return;
		}

		for (Integer i = charArray.length - 1; i >= 0; i--) {
			TrieNode trieNode = stack.pop();
			TrieNode nextTrieNode = trieNode.nextTrieNode(charArray[i]);
			if (!nextTrieNode.hasChild()) {
				trieNode.removeChild(charArray[i]);
			}
		}
	}

	public boolean hasPrefix(String prefix) {

		TrieNode currNode = root;
		for (Character c : prefix.toCharArray()) {
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
		for (Character c : word.toCharArray()) {
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
		for (Character c : lookUpString.toCharArray()) {
			TrieNode nextNode = currNode.nextTrieNode(c);
			if (nextNode == null) {
				return null;
			}
			currNode = nextNode;
		}
		return currNode;
	}

	class TrieNode {

		private Map<Character, TrieNode> charMap;
		private boolean isFinal;
		private Integer frequency;
		private Integer heapIndex;

		public TrieNode() {
			charMap = new HashMap<>();
			isFinal = false;
			frequency = 0;
			heapIndex = -1;
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

		public Integer getHeapIndex() {
			return heapIndex;
		}

		public void setHeapIndex(Integer heapIndex) {
			this.heapIndex = heapIndex;
		}

		public Integer getFrequency() {
			return frequency;
		}
	}
}
