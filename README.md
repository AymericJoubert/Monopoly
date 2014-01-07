Monopoly
========



COMPILER LES SOURCES ET LANCER LE JEU SANS LE JAR
-------------------------------------------------

Ouvrir un terminal, se placer dans le repertoire souhaité, entrer les commandes :


git clone https://github.com/AymericJoubert/Monopoly.git

javac -sourcepath sources -d classes sources/monopoly/csv/*.java

javac -sourcepath sources -d classes sources/monopoly/evenements/*.java

javac -sourcepath sources -d classes sources/monopoly/gui/*.java

javac -sourcepath sources -d classes sources/monopoly/jeu/*.java

javac -sourcepath sources -d classes sources/monopoly/proprietes/*.java

java -classpath classes Lancement config/monopoly.csv config/cartes.csv


COMPILER LES SOURCES ET LANCER LE JEU AVEC LE JAR
-------------------------------------------------

Ouvrir un terminal, se placer dans le repertoire souhaité, entrer les commandes :


git clone https://github.com/AymericJoubert/Monopoly.git

java -jar Monopoly.jar
