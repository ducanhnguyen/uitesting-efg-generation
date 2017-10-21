# Project GUIParser
The tool analyzes GUI Tree and event flow graph given by GUITAR to produce EFG cypher query. This cypher query is then import to Neo4j for visualization.

Input: event flow graph + gui tree (generated by GUITAR)

Output: cypher script for efg

Main class: Neo4jExporter.java

# Two samples projects
To obtain GUI tree and EFG of each sample, please follow these steps:

(This work is only performed on Ubuntu)

1. Export sample to .jar

2. Download GUITAR, then modify its configuration file

3. Execute GUITAR, then get Project.EFG.xml and Project.GUI.xml

4. Load Project.EFG.xml and Project.GUI.xml to GUIParser

5. Get cypher script and copy it to Neo4J

## 1. Project StudentManagement
The first simple version of student management software. 

Main class: GUIVIEW.java

## 2. Project StudentManagementv2
The next version of the first version of student management software. There are a few changes in the new version, e.g., layout of TAB is changed, the label of button is modified, etc.

Main class: GUIVIEW.java
