AlgorithmsJIQ
=============

Solutions to Job Interview Questions listed in [Algorithms course](https://www.coursera.org/course/algs4partII
) in coursera. 

This project uses two libraries from the [Algorithms 4th Edition booksite](http://algs4.cs.princeton.edu/code/). As these are not available in a maven repo, it is included in repo/ folder.

In order to add these libraries in to your local repo, use the following commands,

    $ mvn org.apache.maven.plugins:maven-install-plugin:2.3.1:install-file \
        -Dfile=repo/stdlib-package.jar -DgroupId=edu.princeton.cs.algs4 \
        -DartifactId=stdlib-package -Dversion=1.0.0 \
        -Dpackaging=jar -DlocalRepositoryPath=repo

and,

    $ mvn org.apache.maven.plugins:maven-install-plugin:2.3.1:install-file \
        -Dfile=repo/algs4-package.jar -DgroupId=edu.princeton.cs.algs4 \
        -DartifactId=algs4-package -Dversion=1.0.0 \
        -Dpackaging=jar -DlocalRepositoryPath=repo

An easy way out from doing this is to import it as an eclipse project.
