package ninja.lars.gameoflife;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        int size = 15;
        int generations = 1000;

        World world = new World(size);
        world.init(0.5F);

        System.out.println("====== WORLD GENERATED ======");
        world.show();

        for (int i = 0; i < generations; i++) {
            world = world.getNextGeneration();
            System.out.println("====== NEXT GENERATION "+i+" ======");
            world.show();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("====== FINAL RESULT ======");
        world.show();

    }
}
