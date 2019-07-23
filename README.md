# Knight's Tour
Open Knight's Tour implementation, in both C and TypeScript.

This problem was my first real programming assignment, in 2011. I thought it would be interesting to redo it to see how it would turn out.

I originally only meant to implement it in TypeScript, but I thought it was a tad too slow, so I also wrote a quick C implementation.

This implementation is by no means the most efficient, nor necessarily particularly efficient at all, it was just good enough for me to be satisfied with this quick project.

# Running
To run the TypeScript implementation, node is necessary. Just run an npm install on TypeScript/ to install the few dependencies necessary and an npm run test to run the code. On index.ts you can alter the size of the board.

To run the c implementation, you'll need to compile the code yourself. It was developed using gcc, a simple gcc knights-tour.c to create the output program is enough. The size of rows and columns is a macro in the code, so recompiling is necessary to change it.
