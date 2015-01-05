Monopoly
========



Compiler les sources et lancer le jeu sans le jar
-------------------------------------------------

Ouvrir un terminal, se placer dans le repertoire souhaité, entrer les commandes :


git clone https://github.com/AymericJoubert/Monopoly.git

cd Monopoly

javac -sourcepath src -d classes src/monopoly/csv/*.java

javac -sourcepath src -d classes src/monopoly/evenements/*.java

javac -sourcepath src -d classes src/monopoly/gui/*.java

javac -sourcepath src -d classes src/monopoly/jeu/*.java

javac -sourcepath src -d classes src/monopoly/proprietes/*.java

java -classpath classes monopoly.gui.Lancement config/monopoly.csv config/cartes.csv


Compiler les sources et lancer le jeu avec le jar
-------------------------------------------------

Ouvrir un terminal, se placer dans le repertoire souhaité, entrer les commandes :


git clone https://github.com/AymericJoubert/Monopoly.git

cd Monopoly

java -jar Monopoly.jar
