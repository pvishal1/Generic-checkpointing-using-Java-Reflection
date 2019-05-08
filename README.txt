Run following command:

ant -buildfile genericCheckpointing/src/build.xml clean
ant -buildfile genericCheckpointing/src/build.xml all
ant -buildfile genericCheckpointing/src/build.xml run -Darg0=mode -Darg1=N -Darg2=fullPathOfFile

command to generate javadoc:
ant -buildfile genericCheckpointing/src/build.xml javadoc

The javadoc generated is stored in "doc" folder at src level. The "index.html" is the file which takes from first page of Javadoc.

