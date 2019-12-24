'''
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Given a binary matrix. Find minimum steps to reach from the start to end
cell. 1 means that cell is open whereas 0 means that the cell is blocked.
---------------------------------------------------------------------------
'''

from collections import deque

class Node:
    def __init__(self, row, col, dist):
        self.row = row
        self.col = col
        self.dist = dist


def minDist(matrix, start, end):

    n = len(matrix)
    visited = []

    # init visited matrix
    for r in range(n):
        visited.append([])
        for c in range(n):
            visited[r].append(matrix[r][c] != 1)

    #BFS
    q = deque()
    q.append(start)
    while len(q) > 0:
        cell = q.popleft()
        if cell.row < 0 or cell.row >= n or cell.col < 0 or cell.col >=n or visited[cell.row][cell.col]:
            continue
        if cell.row == end.row and cell.col == end.col:
            return cell.dist
        q.append(Node(cell.row+1, cell.col, cell.dist+1))
        q.append(Node(cell.row-1, cell.col, cell.dist+1))
        q.append(Node(cell.row, cell.col+1, cell.dist+1))
        q.append(Node(cell.row, cell.col-1, cell.dist+1))
        visited[cell.row][cell.col] = True 


matrix = [[1,1,1,1,1],[1,1,1,0,1],[1,1,1,0,1],[1,1,0,1,1],[1,0,1,1,1]]
start = Node(1,1,0)
end = Node(3,3,0)
print("Minimum distance is:", minDist(matrix, start, end))