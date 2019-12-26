'''
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Given an array and an integer k, find the maximum for each and every 
contiguous subarray of size k.
---------------------------------------------------------------------------
'''

from collections import deque 

def maxInSubArray(arr, k):

    n = len(arr)
    #corner case
    if n == 0:
        return

    #first k elements
    q = deque()
    for i in range(k):
        while len(q) > 0 and arr[i] >= arr[q[-1]]:
            q.pop()
        q.append(i)

    #for all sub-arrays
    for i in range(k, n):
        print(arr[q[0]])
        if q[0] == i-k:
            q.popleft()
        while len(q) > 0 and arr[i] >= arr[q[-1]]:
            q.pop()
        q.append(i)

    print(arr[q[0]])

arr = [8, 5, 10, 7, 9, 4, 15, 12, 90, 13]
maxInSubArray(arr, 4)