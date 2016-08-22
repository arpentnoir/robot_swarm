# Robot Swarm

Robot Swarm models the movements of a flock of autonomous robots within a search grid by calculating and displaying
their final position based on a set of movement instructions.

## To Run

There is a jar file located at robot_swarm/out

You can run by executing the following from the command line, then entering your input.

``` 
java -jar RobotSwarmManager.jar 
```

Alternatively, if you have your input data properly formatted in a txt file, you can just run the following: 

```
java -jar RobotSwarmManager.jar < input.txt 
```


## Input Format

RobotSwarmManager expects input in the following format: 

* The first line should be two integers representing the width and height of the landing grid, for example:

```
5 5
```

* Subsequent lines should be grouped in pairs, each pair representing a single robot. The first line gives the initial position and heading of the robot, in the form of two integers seperated by a space, followed by a single character - either N, S, E or W, representing the heading. 
For example, the follwing represents a Robot facing north, with initial grid coordinates of (2, 4)

```
2 4 N
```
* The next line in each pair represents the robots instruction set. This should consist of a string of characters 'L' or 'R' representing a 90 degree left or right hand turn, or an 'M' representing an instruction to move one grid position in the direction the robot is currently facing. For example, the following list of instructions tell the robot to move one unit in the direction it is currently facing, turn left, turn right twice, then move once more. 

```
MLRRM
```

* Putting these together, we get an example of a complete, correctly formatted input. Take note that inputs should end with an end of file character. 
```
5 5
2 4 N
MLRRM
```

* Multiple robots can be included in one input, but adding additional pairs of lines, for example:
```
5 5
2 4 N
MLRRM
3 5 W
MRLRMMRL
2 1 E
MMRMMRLM
```

## Output

RobotSwarmManager outputs the final position of each robot given in the input. For example, the output for the above
sample input is:

```
3 5 E
2 5 N
4 0 S
```