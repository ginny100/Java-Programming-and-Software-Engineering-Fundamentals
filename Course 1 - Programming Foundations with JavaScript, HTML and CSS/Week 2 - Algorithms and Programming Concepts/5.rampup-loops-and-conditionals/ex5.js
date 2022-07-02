/* Exercise 5 - Green square in top left corner */

var image = new SimpleImage("chapel.png");

for (var pixel of image.values()) {
    if (pixel.getX() < 50) {
        if (pixel.getY() < 50) {
            pixel.setRed(0);
            pixel.setGreen(255);
            pixel.setBlue(0);
        }
    }
}

print(image);