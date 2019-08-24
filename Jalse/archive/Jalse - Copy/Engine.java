import java.util.List;

// manages entities and their interactions
public class Engine {

  // E:\Resources\jdk-12.0.1\bin\javac.exe Jalse.java & E:\Resources\jdk-12.0.1\bin\java.exe Jalse.java
  // E:\Resources\jdk-12.0.1\bin\java.exe -jar E:\violetumleditor-2.1.0.jar

  private List<Entity> entities;
  private int deltaTime = 20;

  // private void Collide(Entity one, Entity two) {
  //   Vector normal = one.pos.sub(two.pos);
  //   Vector relVel = one.vel.add(two.vel.neg());
  // }

  private void update() {
    while (true) {

      // update entities
      for (int i = 0; i < entities.size(); ++i) {
        entities.get(i).update(deltaTime);
      }

      // check for collisions
      // for each player, check collisions against each wall and each other player
      // wall optimization: remove its collider if all four sides are covered

      try{ Thread.sleep(deltaTime); } catch (Exception exc) {}
    }
  }

  public Engine(List<Entity> entities) {
    this.entities = entities;
    Thread updater = new Thread(new Runnable() { public void run() { update(); } } );
    updater.start();
  }
}
