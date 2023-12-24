package jochim.vaclav;

public class SpinningDoughnut {

    public static void main(String[] args) throws InterruptedException {
        double rotationAngleX = 0, rotationAngleY = 0;
        while (true) {
            clearScreen();
            StringBuilder screenBuffer = new StringBuilder();
            double[] depthBuffer = new double[1760];
            char[] outputBuffer = new char[1760];
            for (int k = 0; k < 1760; k++) {
                outputBuffer[k] = ' ';
                depthBuffer[k] = 0;
            }

            for (double theta = 0; theta < 6.28; theta += 0.07) {
                for (double phi = 0; phi < 6.28; phi += 0.02) {
                    double sinPhi = Math.sin(phi), cosTheta = Math.cos(theta), sinRotationX = Math.sin(rotationAngleX), sinTheta = Math.sin(theta), cosRotationX = Math.cos(rotationAngleX), circleRadius = cosTheta + 2, depth = 1 / (sinPhi * circleRadius * sinRotationX + sinTheta * cosRotationX + 5), cosPhi = Math.cos(phi), cosRotationY = Math.cos(rotationAngleY), sinRotationY = Math.sin(rotationAngleY), circleHeight = sinPhi * circleRadius * cosRotationX - sinTheta * sinRotationX;
                    int screenX = (int) (40 + 30 * depth * (cosPhi * circleRadius * cosRotationY - circleHeight * sinRotationY)), screenY = (int) (12 + 15 * depth * (cosPhi * circleRadius * sinRotationY + circleHeight * cosRotationY)), linearIndex = screenX + 80 * screenY, luminanceIndex = (int) (8 * ((sinTheta * sinRotationX - sinPhi * cosTheta * cosRotationX) * cosRotationY - sinPhi * cosTheta * sinRotationX - sinTheta * cosRotationX - cosPhi * cosTheta * sinRotationY));
                    if (22 > screenY && screenY > 0 && screenX > 0 && 80 > screenX && depth > depthBuffer[linearIndex]) {
                        depthBuffer[linearIndex] = depth;
                        outputBuffer[linearIndex] = ".,-~:;=!*#$@".charAt(Math.max(luminanceIndex, 0));
                    }
                }
            }

            for (int k = 0; k < 1760; k++) {
                if (k % 80 == 0) {
                    screenBuffer.append("\n");
                } else {
                    screenBuffer.append(outputBuffer[k]);
                }
            }

            System.out.print(screenBuffer);
            rotationAngleX += 0.04;
            rotationAngleY += 0.02;
            Thread.sleep(20);
        }
    }

    private static void clearScreen() {
        System.out.print("\033\033");
    }
}
