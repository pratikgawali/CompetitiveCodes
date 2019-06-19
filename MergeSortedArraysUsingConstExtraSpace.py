'''
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
We are given two sorted array. We need to merge these two arrays such that 
the initial numbers (after complete sorting) are in the first array and the 
remaining numbers are in the second array. Extra space allowed in O(1).
---------------------------------------------------------------------------
'''

def merge(arr1, arr2):
    """Merges the given two sorted arrays with O(1) extra space."""

    i, n = 0, len(arr1)
    while i < n:
        if arr1[i] > arr2[0]:
            val = arr1[i]
            arr1[i] = arr2[0]
            insertSorted(arr2, val)
        i += 1


def insertSorted(arr, val):
    """
    Inserts the given value into the given sorted array.
    
    It assumes the first space in the array is blank.
    """

    i, n = 1, len(arr)
    while i < n and arr[i] < val:
        arr[i-1] = arr[i]
        i+=1

    arr[i-1] = val


if __name__ == "__main__":
    
    arr1 = [1,3,4,8,9,13,16,19]
    arr2 = [0,2,5,10,11,14,15,18]
    merge(arr1, arr2)
    print(arr1, arr2)