Using the command prompt
1) Extract the zip folder "fallingsticks.zip"
2) Open the command prompt terminal in the directory where the folder has been extracted (e.g. C:\Users\dines\Documents\Assignments\CE812\1901098_MUNIANDY\Source Code\fallingsticks) 
3) Before compiling we need to copy some resources to source folder, so write in command prompt: xcopy .\res\audio .\src\audio /s
4) Run the command
5) Some audio file has been copied to the source folder
6) Then, change the directory to src folder, write in command prompt: cd src
7) Run the command
8) The directory of command prompt changed to src folder (e.g. C:\Users\dines\Documents\Assignments\CE812\1901098_MUNIANDY\Source Code\fallingsticks\src)
9) If jdk path has been set then go to step 10, otherwise follow the instructions below:
    To set the jdk path:
    - Copy the path of the JDK/bin directory (e.g. D:\Programs\Java\jdk-13\bin)
    - Write in command prompt: set path=jdk_copied_path (e.g. set path=D:\Programs\Java\jdk-13\bin)
    - Run the command
10) Now copy the command line below to the command prompt
    javac ./audio/*.java ./game_src/Box2DPhysicsEngine.java ./game_src/*.java ./org/jbox2d/callbacks/*.java ./org/jbox2d/collision/*.java ./org/jbox2d/collision/broadphase/*.java ./org/jbox2d/collision/shapes/*.java ./org/jbox2d/common/*.java ./org/jbox2d/dynamics/*.java ./org/jbox2d/dynamics/contacts/*.java ./org/jbox2d/dynamics/joints/*.java ./org/jbox2d/particle/*.java ./org/jbox2d/pooling/*.java ./org/jbox2d/pooling/arrays/*.java ./org/jbox2d/pooling/normal/*.java ./org/jbox2d/pooling/stacks/*.java ./settings/*.java
11) Run the command
12) After compilation succeed, run the below command line in the prompt (Ignore the warnings from jbox2d library)
    java game_src/Box2DPhysicsEngine

NOTE: The directory path may differ from various system, please check before using it 

**********************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************

Using IDE
1) Extract the zip folder "fallingsticks.zip"
2) Open your Java IDE
3) Open the extracted project file in your IDE as follows:

For IntelliJ IDE
- Click on Open Project option
- Select the fallingsticks project file i.e. E:\CE 812\fallingsticks
- Click on OK
- After the project opened
- In the project explorer, expand the project and go to src -> game_src -> Box2DPhysicsEngine.java
- Run Box2DPhysicsEngine.java class via its "main" method

For Eclipse IDE
- Click on File -> Open Project from File System option
- In the import source column, select the fallingsticks project file i.e. E:\CE 812\fallingsticks as Directory
- Click on Finish
- After the project opened
- In the project explorer, expand the project and right click on res folder select Build Path -> Use as Source Folder
- Then, go to src -> game_src -> Box2DPhysicsEngine.java
- Run Box2DPhysicsEngine.java class

For other IDE
- Import the extracted folder
- Import the src folder as source root/folder 
- Modify the res folder as resource root/folder
- In the project explorer, expand the project and go to src -> game_src -> Box2DPhysicsEngine.java
- Run Box2DPhysicsEngine.java class