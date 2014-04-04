Edany
=====

Edany

### Credits

In lexiographic order:

* [Christoffer Niska](https://github.com/crisu83)
* [Kai Sellgren](https://github.com/kaisellgren)
* [Stanislav Palatnik](https://github.com/stanpalatnik)

### Contribute

Ideas https://docs.google.com/document/d/1Z_-pdgKMZzFPCfxX9ixZ87dHRJo_F_HWF2LCLtL9AuU/edit

* Clone.
* Install Scala, JDK, Sbt.
* Run `sbt` in the directory.
  * Run `update`.
  * Run `gen-idea`.
* Open the project in IntelliJ IDEA.
* Open `Test.scala` file and right click -> run.

##### Setting up LibGDX

Unfortunately this has to be done manually after SBT.

* Download LibGDX archive http://libgdx.badlogicgames.com/
* You need to extract a few files from there and copy under `edany/desktop/libs` and `edany/main/libs` as shown by this picture: http://goo.gl/3xGa2Y (total of 5 files has to be copied).
* Open `Project Structure -> Modules`. You need to have 2 SBT modules called `desktop` and `main` http://goo.gl/qHeQm6 so create if needed (choose SBT module). Do not worry about *-build modules, they will be automatically created by IntelliJ when you build.
* Last step is to set up our downloaded .jars as dependencies, for example: http://goo.gl/8pTvX1
  * This means, choose the 'dependencies' tab and add the .jars manually (desktop/libs/* -> Desktop module, main/libs/* -> Main module). *Note: you have to add every .jar one by one otherwise IntelliJ tries to add them as a single dep which fails.* If IntelliJ asks what type of content it is, choose 'Classes'.
* Make sure you have added "sub-module" Scala under both `desktop` and `main`: http://goo.gl/jFM0vu should be simple enough to do.
* Make sure both modules have `SBT: org.scala-lang:scala-library:2.10.2` or so added to them as a dependency. This step is done by SBT but since we are creating the modules manually you have to add it yourself.
* Make sure `main` module **exports** its dependencies as shown here http://goo.gl/d3y8Y0
  * For some reason it says Empty Library for the native .jar, I have no idea why but things work fine.
* Final step is to add a dependency for `desktop` module called Module Dependency (for `main`). You can see here http://goo.gl/Fl5u9s that `desktop` module has a dependency on `main`. The `main-build` dep will be done automatically for you by IntelliJ.
