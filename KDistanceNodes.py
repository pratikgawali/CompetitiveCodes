'''
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Print all nodes at distance k from a given node.

Input: node = 3 and k = 3
           1
        /    \ 
       2      3
      / \   /   \
     4   5  6   7
                  \ 
                   8
                 /   \
                9    10

Output: 9 10 4 5
---------------------------------------------------------------------------
'''
from queue import Queue

class Node:
    def __init__(self, val, left, right):
        self.val = val
        self.left = left
        self.right = right


def findNode(root, val, q):
    #Note: q stores (fromNode, subTreeToSearch, distanceFromTargetNode)
    if root is None:
        return False, -1  
    if root.val == val:
        q.put((root, 'L', 0))
        q.put((root, 'R', 0))
        return True, 0
    
    presentInLeft, leftSubTreeDist = findNode(root.left, val, q)
    if presentInLeft:
        q.put((root, 'R', leftSubTreeDist+1))
        return True, leftSubTreeDist+1
    else:
        presentInRight, rightSubTreeDist = findNode(root.right, val, q)
        if presentInRight:
            q.put((root, 'L',rightSubTreeDist+1))
            return True, rightSubTreeDist+1
    return False, -1


def printNodeK(root, k):
    if root is None:
        return
    if k == 0:
        print(root.val)
    else:
        printNodeK(root.left, k-1)
        printNodeK(root.right, k-1)

def kDistNodes(root, val, k):
    q = Queue()
    findNode(root, val, q)

    while not q.empty():
        node, direction, dist = q.get()
        if k-dist == 0:
            print(node.val)
        elif direction == 'L':
            printNodeK(node.left, k-dist-1)
        else:
            printNodeK(node.right, k-dist-1)


# binary tree example
n9 = Node(9, None, None)
n10 = Node(10, None, None)
n8 = Node(8, n9, n10)
n7 = Node(7, None, n8)
n4 = Node(4, None, None)
n5 = Node(5, None, None)
n6 = Node(6, None, None)
n2 = Node(2, n4, n5)
n3 = Node(3, n6, n7)
n1 = Node(1, n2, n3)

kDistNodes(n1, 3, 3)