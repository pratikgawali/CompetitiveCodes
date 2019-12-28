'''
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Given two strings str1 and str2 and below operations that can performed on 
str1. Find minimum number of edits (operations) required to convert ‘str1’ 
into ‘str2’.
    1. Insert
    2. Remove
    3. Replace
All of the above operations are of equal cost.
---------------------------------------------------------------------------
'''
def minEdits(str1, str2, m, n, editsCount):
    if editsCount[m][n] != -1:
        return editsCount[m][n]
    if str1[m] == str2[n]:
        editsCount[m][n] = minEdits(str1, str2, m-1, n-1, editsCount)
    else:
        editsCount[m][n] = 1 + min(
            minEdits(str1, str2, m, n-1, editsCount),    #insert
            minEdits(str1, str2, m-1, n-1, editsCount),  #replace
            minEdits(str1, str2, m-1, n, editsCount))    #remove
    return editsCount[m][n]


str1, str2 = "sunday", "saturday"
str1, str2 = "geek", "gesek"

m,n = len(str1), len(str2)
editsCount = [[-1 for _ in range(n)] for _ in range(m)]
for i in range(m): editsCount[i][0] = i
for i in range(n): editsCount[0][i] = i

print("Minimum Edits required:", minEdits(str1, str2, m-1, n-1, editsCount))