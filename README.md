# RMDocs

Step 1: Clone the project to your preferred directory with the given link: https://github.com/RedmindAB/RMDocs.git

Step 2: Import the project to an IDE as a Maven project.

Step 3: The default path for the output formats will be the Home-directoy. If you wish to change the directory, go to the resources folder and create a file named 'config.properties' and add following: path=/path/

Step 4: Export the project as a runnable JAR file.

Step 5: Run the JAR from the Terminal/CMD with the needed arguments.

        example: java -jar ProjectName.jar -p /Users/username/Documents/TestProject/ -o .json
        
Mandatory arguments: -p (path), -o (output)
        
Other arguments: -f (read format), -a (annotation)
