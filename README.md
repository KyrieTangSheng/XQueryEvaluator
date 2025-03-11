# install antlr
curl -O https://www.antlr.org/download/antlr-4.13.2-complete.jar


# Remove all .class files

find . -name "\*.class" -type f -delete

# Remove ANTLR generated files but keep .g4

cd main/antlr
rm -f _.tokens _.interp XPath*.java XQuery*.java
cd ../..

# generate ANTLR files

cd main/antlr
java -jar ../../antlr-4.13.2-complete.jar -visitor -no-listener -package main.antlr XPath.g4
java -jar ../../antlr-4.13.2-complete.jar -visitor -no-listener -package main.antlr XQuery.g4
cd ../..

# compile and run

javac -cp .:antlr-4.13.2-complete.jar main/Main.java

for i in {1..2}; do
java -cp .:antlr-4.13.2-complete.jar main.Main data/j_caesar.xml data/query/q$i.txt data/result/q$i.xml
done

#zip
find . -name ".DS_Store" -type f -delete
zip -r main.zip main -x "*/.*" "*/__MACOSX/*"