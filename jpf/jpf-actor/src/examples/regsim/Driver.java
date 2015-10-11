package regsim;

import osl.manager.ActorName;
import osl.manager.Actor;
import osl.manager.RemoteCodeException;
import osl.manager.annotations.message;

public class Driver extends Actor {

  public static int N = 3; // can specify 1 or higher
  ActorName master;

  /*******************************************************/
  @message
  public void setUp(String[] args) throws RemoteCodeException {
    if (args.length >= 1) {
      N = Integer.parseInt(args[0]);
    }
    System.out.println("tree bound (# of workers) = " + N);

    // create root set actors
    master = create(Master.class, N);
  }

  /*******************************************************/
  @message
  public void test(String[] args) {
    // queue initial messages
    send(master, "boot");

  }

}
