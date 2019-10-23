# Huffman_coding_projetct
Data Structure project to compress and decompress text files using Binary Tree and Heap

This Data Structure project is based on Huffman Codiding algorithm to compress and decompress files.
On this project we implemented our own Binary Tree mixed with a Heap Tree.

## Process
1 - First i needed to count every character in a file, and then add this characters and its repetitions in a Map structure.


2 - After this i had to create a Coding Tree, and to do this i used my implementation of a binary tree mixed with a heap.
   To create a coding tree, firstly i had to put every single repeated character with his repetition number in Min Heap, and      when all this repeated characters are inside this Heap, you start to take the first two(those who have the highest priority)    elements of this Min Heap, sum the two character´s repetitions and with this value you create a Binary Tree Node, and this      node will have the value of the sum you made, and their children will be the characters you removed and made this sum, and      their repetitions . After this you have to put the binary tree Node again inside the heap, and repeat this procedment          until all the heap will have 1 element, and this element will be the Root of the binray tree. 
   
   It will be something like this images:
 
 ![heap](https://user-images.githubusercontent.com/44793167/67442294-9437c480-f5d6-11e9-8191-4fdff707dac5.png)
 ![heap2](https://user-images.githubusercontent.com/44793167/67442459-41aad800-f5d7-11e9-8b55-dcc43fd3525d.png)
 ![heap3](https://user-images.githubusercontent.com/44793167/67442470-4a031300-f5d7-11e9-9337-69eff8a3799e.png)
 ![heap4](https://user-images.githubusercontent.com/44793167/67442533-90587200-f5d7-11e9-9d0d-d00a8d0856d4.png)

 ![heap5](https://user-images.githubusercontent.com/44793167/67442538-93536280-f5d7-11e9-88d5-04503fb69b67.png)
