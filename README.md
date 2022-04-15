# MapReduce

This project implemented Map() and Reduce() functions using Java.

Our model consists of two MapReduce operations, which means it includes two map functions and two reduce functions. (The model is based upon the model which introduced in section 2.3.9 of the book “Mining of Massive Datasets”. ) To compute the matrix multiplication in map and reduce fashion, we divide the computation process into two phases, each phase is handled by a single distinct MapReduce operation. To concrete our discussion, let A[i][j] denote the first matrix, let B[j][k] denote the second one. We need to compute their product matrix C[i][k]. Specifically, we have this formula:

C[i][k] = A[i][0] * B[0][k] + A[i][1] * B[1][k] + A[i][2] * B[2][k] + .... A[i][j-1] * B[j-1][k] 

Our first MapReduce operation is to compute each  A[i][n] * B[n][k] value in the above formula, where 0 <= n <= j-1. And the second MapReduce operation is to sum all A[i][n] * B[n][k] values to get the C[i][k] value. In this way, we can compute each element’s value in matrix C. 

The first MapReduce operation concludes the first map function and the first reduce function:

The first map function divide the matrix A[i][j] into different key-value pairs, which has the form (j, (A, i, A[i][j])), where ‘A’ is not the matrix itself, it just used to denote that this key-value pair comes from the matrix A, and ‘A[i][j]’ in the expression is the value of the element A[i][j]. We do the same operation to the matrix B[j][k], divide it into different key-value pairs which has the form (j, (B, j, B[j][k])), where ‘B’ is used to denote this pair comes from the matrix B, and ‘B[j][k]’ is the value of the element B[j][k].

Then we use the first reduce function to sort those key-value pairs according to their key ‘j’. After that, we can get different groups of key-value pairs, where all pairs in the same group share the same key ‘j’. Each group consists of key-value pairs coming from the matrix A and other pairs coming from the matrix B. We multiply each pair A[i][j] coming from A with each pair B[j][k] coming from B in the same group, and each product A[i][j] * B[j][k] can be denoted using the form ((i, k), A[i][j] * B[j][k]), where (i, k) is the key, ‘A[i][j] * B[j][k]’ is the value of the product. 

The second MapReduce operation concludes the second map function and the second reduce function:

The second map function actually does not need to do anything, since we already have different key-value pairs which has the form ((i, k), A[i][j] * B[j][k]) in the first reduce function. The divide process has been done.

The second reduce function grouping pairs into different groups according to their key (i, k). Then it simply adds all the values in pairs to get the result element C[i][k].
