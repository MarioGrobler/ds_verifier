A small dominating set verifier for [PACE 2025](https://pacechallenge.org/2025/).
This verifier is available as a Java/Maven project. We recommend IntelliJ to build the project.
The program expects two arguments: the first argument is a path to the `.gr`-file containing your graph and the second argument is a path to the solution file. 
Alternatively, you can add your graphs with solution files into the `testset` folder and run the test in the file `VerifierTest`.
The test reads all graphs in the `testset` folder, expects for each graph a solution file with the same name (and file extension `.sol` instead of `.gr`) and verifies the solution for the graph. The `testset` folder already contains a small test set.
Follow [this link](https://pacechallenge.org/2025/ds/) for more information on the input/output files.
