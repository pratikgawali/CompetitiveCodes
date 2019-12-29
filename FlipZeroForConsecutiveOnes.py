'''
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Given a binary array and an integer m, find the maximum number of 
consecutive 1’s in array formed by flipping atmost m 0s.
---------------------------------------------------------------------------
'''
from queue import Queue

def maxConsecutiveOnes(arr, m):
    n, q = len(arr), Queue()
    windowSize, maxWindowSize = 0, 0
    for i in range(n):
        if arr[i] == 1:
            windowSize += 1
            maxWindowSize = max(maxWindowSize, windowSize)
        elif m > 0:
            m -= 1
            q.put(i)
            windowSize += 1
            maxWindowSize = max(maxWindowSize, windowSize)
        else:
            windowSize = i - q.get()
            q.put(i)
        
    return maxWindowSize


arr, m = [1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1], 2
print("Max consecutive 1s:", maxConsecutiveOnes(arr, m))