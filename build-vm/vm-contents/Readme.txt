********************
README
********************

1. The terminal will be opened automatically and will be on the "jpf-actor" directory.
2.  In order to explore the ActorFoundry subjects provided in src/examples, run Basset under JPF, additionally specifying the fully qualified class name for the subject's test driver along with any applicable arguments for the driver. For example, the following command will test the ActorFoundry program "pi" using one master actor and three worker actors. This may take a minute to run. 

bin/jpf gov.nasa.jpf.actor.Basset pi.Driver 3

Reference: http://mir.cs.illinois.edu/basset/#download
