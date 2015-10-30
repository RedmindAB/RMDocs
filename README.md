# RMDocs


Step 1: Clone the project to your preferred directory with the given link: https://github.com/RedmindAB/RMDocs.git

Step 2: Import the project to an IDE as a Maven project.

Step 3: The default path for the output formats will be the working directory of the app. If you wish to change the directory, go to the resources folder and create a file named 'config.properties' and add following: path='/path/'.

Step 4: Export the project as a runnable JAR file.

Step 5: Run the JAR from the Terminal/CMD with the needed arguments.

        example: java -jar ProjectName.jar -p '/Users/username/Documents/TestProject/' -o .json
        
Mandatory arguments: 

-p - Path to the project. Note that path should be inside quotation marks.

-o - Output format. It is possible to choose multiple formats. Valid formats: .txt, .json, .html, .xml, .con
        
Optional arguments: 

-f - The type of file to be searched. Current valid formats: .java

-a - The type of annotation to be read. Default is '@rm'

-s - Search function to find methods that is missing a given comment. Search string should be the comment key without the annotation. Displays in the report file.
