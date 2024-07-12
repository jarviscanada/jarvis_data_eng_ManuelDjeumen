# Introduction
(50-100 words)
The grep app is an implementation of the grep linux command but done with java. For acheiving this purpose, it was needed to know some basic concepts with OOP (Object Orienting Programming), Java, and class and methods handling. Also concept like Streams and lambda functions for the second implementation of the app with those.  

# Quick Start
First of all, it is important to explain how the grep command works. That command is meant to search certains words based on a specific pattern provided by the user. The search is meant to be done in a specific folder. The command scans the folder and all the files inside to check all the lines of all the files inside the folder and if there are matches that are found, it is meant to output the value of the line where the match is found inside a specific file provided by the usser via a path to the file. 
So the app will do the same thing by taking the folder path, the regular expression pattern that we have to search and the output file path where the results will be stored.   

#Implemenation
## Pseudocode
```java
public void process() throws IOException {
        for (File i : listFiles(getRootPath())){
            List<String> lines = readLines(i);
            writeToFile(lines);
        }
    }
```
This is the implementation in Java of the process. The method checks in the folder path provided all the files inside and read all the lines inside each files. Then for each line, it verify if the pattern provided is present and if it is the case, it writes the line inside the output file. The verification process is done inside the 'writeToFile()' method. 

## Performance Issue
(30-60 words)
Discuss the memory issue and how would you fix it
The performace for the project was pretty standart. There were some improvements in terms of the coding part that helped to improve it a little bit like the fact that we can import leess classes, just the ones that will be used for the programm but nothing to special about it in general

# Test
How did you test your application manually? (e.g. prepare sample data, run some test cases manually, compare result)
The tests were done in a simple folder that contains multiple file instances in my local machine. Regex pattern were provided to find words such as 'Juliet' or 'Romeo' for example inside a series of txt files that contains multiples poems and other stuff like that and it delivered pretty well. 


# Deployment
How you dockerize your app for easier distribution?

# Improvement
List three things you can improve in this project.
