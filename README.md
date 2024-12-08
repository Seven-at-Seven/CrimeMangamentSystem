# CrimeManagmentSystem

## Donot Forget to install jdk 19 instead of your current version 
1. file -> Project Structure -> SDK -> Choose 19 -> Apply ->Ok


## If you have intelij
1. new -> project from version control -> choose git if not choosen
2. paste `https://github.com/Seven-at-Seven/CrimeMangamentSystem`

## If you are on linux
1. `git clone https://github.com/Seven-at-Seven/CrimeMangamentSystem`
2. Run `mvn clean install` (to install the depenceies)
3. Run `mvn javafx:run` (to run the program)
6. Run `git checkout <your-branch-name>`

## Development
1. Create a branch named after the feature that you work on (you can create branch using Github)
2. Do your magic (add your code to the project)

3. Run `git add .`to stage your changes
   (it's prefered to commit each change alone with diffrante commit message for better readability)  

4. Run `git commit -m "<Add-your-message-here>"`

5. Run `git push` to render changes on Github
