# Huffman_coding_projetct
Data Structure project to compress and decompress text files using Binary Tree and Heap

This Data Structure project is based on Huffman Codiding algorithm to compress and decompress files.
On this project we implemented our own Binary Tree mixed with a Heap Tree.

## Process
**1 -** First i needed to count every character in a file, and then add this characters and its repetitions in a Map structure.


**2 -** After this i had to create a Coding Tree, and to do this i used my implementation of a binary tree mixed with a heap.
   To create a coding tree, firstly i had to put every single repeated character with his repetition number in Min Heap, and      when all this repeated characters are inside this Heap, you start to take the first two(those who have the highest priority)    elements of this Min Heap, sum the two character´s repetitions and with this value you create a Binary Tree Node, and this      node will have the value of the sum you made, and their children will be the characters you removed and made this sum, and      their repetitions. After this you have to put the binary tree Node again inside the heap, and repeat this proceedment          until all the heap will have 1 element, and this element will be the Root of the binray tree. 
   
   It will be something like this images:
 
 ![heap](https://user-images.githubusercontent.com/44793167/67442294-9437c480-f5d6-11e9-8191-4fdff707dac5.png)
 ![heap2](https://user-images.githubusercontent.com/44793167/67442459-41aad800-f5d7-11e9-8b55-dcc43fd3525d.png)
 ![heap3](https://user-images.githubusercontent.com/44793167/67442470-4a031300-f5d7-11e9-9337-69eff8a3799e.png)
 ![heap4](https://user-images.githubusercontent.com/44793167/67442533-90587200-f5d7-11e9-9d0d-d00a8d0856d4.png)

 ![heap5](https://user-images.githubusercontent.com/44793167/67442538-93536280-f5d7-11e9-88d5-04503fb69b67.png)

 
 
**3 -** After creating a Coding Tree, you have to create a coding table. You can see that each leaf of your coding tree is a character and its repetition you stored, so to create a coding table you will have to assign value 0(zero) to children´s left edges, and assing the value 1 to children´s right edges of every Node. After this, each character code is the path from the    Root node to the character leaf.

![path](https://user-images.githubusercontent.com/44793167/67443066-b8e16b80-f5d9-11e9-9e09-82364a7f1a00.png)

For example:

z character code is --> 001

l character code is --> 11

and so on...

Note: After finding each code for each character, you have to put all this information in a .edt file
with the character and then his codidification in each diferent line. You will use this after to decompress the files.

**4 -** Coding the text file

   with the generated coding table, we can code the text file by comparing each character in the text file with his              generated code(inide the coding table), and putting this sequence codes in a binary file( .edz). Actually, i think this is where    the compression happens.
   
   

**5-** Recovering the file
   
   With the compressed file(.edz) and the coding table file(.edt), its possible to recover the original message!
   
   First you have to read the coding table and put in a Map like data structure, after this you have to read every single 
   Bit you retrieved from the .edz file and concatenate this values until you find a matching code in your coding table, and    put this character in the out buffer. You will repeat this procedure until you find the End Of File(EOF) Bit sequence.
   
   Note: I used Bitset java class to manipulate bits and i created an especial character to be my EOF.
   
   
   
