#0hn0 Resolver

##Instructions for running an algorithm on a board

#####1. Compile the project

```shell
mvn package
```

#####2. Run the algorithm-heuristic combination of your preference

```
java -jar targe java -jar target/0hn0Resolver.jar algorithm [heuristic] file
```

0hno Resolver currently supports the following algorithms

* DFS (dfs)
* BFS (bfs)
* IDFS (idfs)
* A* (astar)
* Greedy (greedy)

Also some heuristics are provided (more detail about the used metrics is given in the presentation paper)

* H1 (h1)
	+ Inadmissible
* H2I (h2i)
	+ Inadmissible
* H2A (h2a)
	+ Admissible

Take into account that only A* and Greedy work with heuristic, therefore it would make no sense to execute the resolver with DFS/BFS/IDFS and a heuristic.

#####3. Enjoy!

##Usage Examples 

1. Run DFS algorithm on a 4x4 board

```
java -jar targe java -jar target/0hn0Resolver.jar dfs doc/board/4x4_1
```

1. Run A* with H2I on a 6x6 board

```
java -jar targe java -jar target/0hn0Resolver.jar astar h2i doc/board/6x6_3
```



