package org.example;

public class ConsoleRenderer implements Renderer {

    private static final String EMPTY_BLOCK = "\u001B[47m";
    private static final String RESET_COLOR = "\u001B[0m";

    @Override
    public void render(Map map) {
        int width = map.getWidth();
        int height = map.getHeight();

        for (int row = 0; row < height; row++) {
            StringBuilder line = new StringBuilder();

            for (int column = 0; column < width; column++) {
                Coordinates coordinates = new Coordinates(row, column);

                if (map.isEmptyBlock(coordinates)) {
                    line.append(getEmptyBlockSprite());
                } else {
                    Entity takenEntity = map.getEntity(coordinates);
                    line.append(getFilledBlockSprite(takenEntity));
                }
            }

            System.out.println(line);
        }
    }

    private String getFilledBlockSprite(Entity entity) {
        return null;
    }

    private String getEmptyBlockSprite() {
        return EMPTY_BLOCK + "   " + RESET_COLOR;
    }

    public static void main(String[] args) {
        ConsoleRenderer renderer = new ConsoleRenderer();
        renderer.render(new Map(5, 5));
    }

}
