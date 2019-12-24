'''
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Given a binary tree, print it vertically. The following example illustrates
vertical order traversal.
           1
        /    \ 
       2      3
      / \   /   \
     4   5  6   7
               /  \ 
              8   9 

Output: 
4
2
1 5 6
3 8
7
9
---------------------------------------------------------------------------
'''

class Node:
    def __init__(self, val, left, right):
        self.val = val
        self.left = left
        self.right = right

def printVertBinTree(root):
    hDist = {}
    vertDist(root, 0, hDist)
    for dist in sorted(hDist.keys()):
        print(hDist[dist])

def vertDist(node, dist, hDist):
    if node is None:
        return
    if dist not in hDist:
        hDist[dist] = []
    
    hDist[dist].append(node.val)
    vertDist(node.left, dist-1, hDist)
    vertDist(node.right, dist+1, hDist)
    

# binary tree example
n4 = Node(4, None, None)
n5 = Node(5, None, None)
n6 = Node(6, None, None)
n8 = Node(8, None, None)
n7 = Node(7, n8, None)
n2 = Node(2, n4, n5)
n3 = Node(3, n6, n7)
n1 = Node(1, n2, n3)

printVertBinTree(n1)