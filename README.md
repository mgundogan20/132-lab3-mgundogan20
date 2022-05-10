### Collaboration Rules
This lab  is an indivual work. Everyone should submit their own work. You can use any material from the course, or any other resource on Java programming language. You can discuss lecture realted topics, the problem at the concept level with other students, but not the solution. You can reuse (copy-paste) code from the lecture notes, textbook, or code given in the lab, but not from others (internet sources, otehr students etc.)

### Honor Code - by submitting lab work I accept:
- This code is my own work. 
- I did not consult to any program written other students. 
- I did not search for the solution of the problem in the internet.
- I did not copy-paste code written by others.
- This code is my own work.

### IMPORTANT

DON'T COPY PASTE code from anybody or any other resource. The code should be written by you. The code similarity tools will find such codes and disciplinary action will be taken.

# Knights Tour
An interesting puzzler for chess buffs is the Knight's Tour problem, originally proposed by the mathematician Euler. Can the knight piece move around an empty chess-board and touch each of the 64 squares once and only once? Here is more information an animation demonstrating the problem: [Knight's Tour in Wikipedia](https://en.wikipedia.org/wiki/Knight%27s_tour)

We study this intriguing problem in depth here. The Prelab has three parts. You are given only the main driver method which calls the three scenarios:

1. Single tour and print board 
2. 1000 tours and print histogram of tour lenghts
3. Full tour and print the histogram of tour lengths, number of trials.


You need to write  all of your code in a class called ***KnightsTour***.


## PreLab A - Single Tour
Let's develop an application that will move the knight around a chessboard until it can not move anymore. Represent the board  by an n-by-n two-dimensional int array. Each square is initialized to zero. The knight makes only L-shaped moves (two spaces in one direction and one space in a perpendicular direction). We describe each of the eight possible moves in terms of its horizontal and vertical components. Horizontal moves to the left and vertical moves upward are indicated with negative numbers. The eight move types may be described by two one-dimensional arrays, horizontal and vertical, as follows:
```java
int[] horizontal = {2, 1, -1, -2, -2, -1, 1, 2};
int[] vertical = {-1, -2, -2, -1, 1, 2, 2, 1};
```

Let the variables `currentRow` and `currentColumn` indicate the row and column, respectively, of the knight's current position. To make a move of type `moveNumber`, where `moveNumber` is between 0 and 7, your program should use the statements
```java
currentRow += vertical[moveNumber];
currentCol += horizontal[moveNumber];
```
to move to a new position.

Write a program to move the knight around the chess-board, visiting board squares only once. Keep a counter that varies from 1 to n^2. Start from a square on the board (chosen randomly). Put a 1 there. Then, choose a move type (one of the possible 8 moves). Not every move type can be possible (out of board, previously visited). Try always in the following order find the possible move. First try move type 0 (then 1,2,...,7)  until you find a valid move type Record the latest count in each square the knight moves to. Test each potential move to see if the knight has already visited that square. Test every potential move to ensure that the knight does not land off the chess-board. The tour ends when either each square is visited, or the knight cannot move further. The starting square is also determined ***randomly***. Print the board at the end when the tour ends.
Example Single Tour Print Output: (each square number is printed in a space of 5, right justified).
```
The tour ended with 46 moves.
This was not a full tour.
     0   1   2   3   4   5   6   7

 0  46   9  22  29   2   7  20   5
 1  23  30   1   8  21   4  17  14
 2  10  45  28   3  16  13   6  19
 3  31  24  43  12  27  18  15  40
 4  44  11  26  33  42  39  36   0
 5  25  32   0   0  35   0  41  38
 6   0   0  34   0   0  37   0   0
 7   0   0   0   0   0   0   0   0
```
Your program should work for any board of size n. You will get the value of n from the command line. In addition to n, you will get another integer for setting initialing the random number generator (see java.util.Random.setSeed())).

Example for running an 8x8 board, and seed value 213123:

1. java LabMain3 8 213123      (command line)

2. go to run configurations, put 8 and 213123 in the arguments tab  (eclipse)

The two arguments are
1. First argument:  integer  (that is, boardsize n)
2. Second argument:  integer seed for random number generator. You program shoudl call setSeed() method of the java.util.Random to set the seed. This is useful to repeat the same random sequence in different runs for testing.  A -1 value means  no seed.


## PreLab B - 1000 thousand tour
In this part, you will change your single tour algorithm slighly. You will choose move type randomly. That is, instead of trying  move type in the order  0,1,..., you will start from a random move type, say k, then you will try k,k+1,k+2 which wraps around and tries all 8 moves until you find a valid move. You run this modified single tour algorithm 1000 times. For each tour, keep the number of moves that the knight made (tour length). Print a histogram that shows the number of tours for each tour length.

Example Print Output: (#tours printed in a space of 10 left justified, tour length in space of 3, left justified)
```
#tours   tour length

0         1
0         2
0         3
1         4
0         5
2         6
0         7
4         8
1         9
2         10
3         11
5         12
5         13
13        14
6         15
8         16
11        17
3         18
11        19
10        20
8         21
15        22
15        23
10        24
14        25
19        26
16        27
22        28
18        29
20        30
20        31
20        32
36        33
25        34
38        35
34        36
31        37
40        38
27        39
38        40
45        41
36        42
33        43
42        44
29        45
50        46
34        47
34        48
30        49
26        50
16        51
22        52
10        53
12        54
13        55
6         56
4         57
5         58
2         59
0         60
0         61
0         62
0         63
0         64
```
## PreLab C - Full tour
In this part, you should run the experiments until the knight makes a full tour (modified single tour). That is, you will run single tour as many as required so that you find a tour which visits all the squares. Print the number of tours, tour lenght histogram. Also, print the number tries.

Example Output: (#tours printed in a space of 10 left justified, tour length in space of 3, left justified)

```
#tours   tour length

0         1
0         2
0         3
87        4
69        5
271       6
195       7
354       8
326       9
517       10
435       11
783       12
616       13
967       14
836       15
1150      16
999       17
1455      18
1285      19
1667      20
1560      21
2120      22
1799      23
2408      24
2264      25
2761      26
2595      27
3037      28
2855      29
3559      30
3157      31
4028      32
3586      33
4398      34
4010      35
4747      36
4338      37
5035      38
4280      39
5181      40
4538      41
5332      42
4446      43
5106      44
4281      45
4852      46
3978      47
4412      48
3362      49
3663      50
2825      51
2836      52
2031      53
2045      54
1379      55
1199      56
702       57
576       58
301       59
215       60
79        61
48        62
6         63
1         64

It took 137943 tries to get a full tour
```

### IMPORTANT

***You should solve the problem the way we described. By not following instructions, you may lose points.***

***Check Blackboard LAB3 for possible corrections***

***Print out in the same format, points will be taken off you don't print in the same format***

***DON'T COPY PASTE code from anybody or any other resource. The code should be written by you.***

