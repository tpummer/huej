package at.nullpointer.hue.controller.huecontroller;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AvailableColors {
    RED("red", new int[]{100}, new int[]{-1}), BLUE("blue", 200), GREEN("green", 300), ORANGE("orange", 400), PINK("pink", 500), OCEAN("ocean", 600), YELLOW("yellow", 700), POLICE("police", 800), STOETOEKOETOE("stoetoekoetoe", 900);

    private final int NO_COLOR_CHANGE_AFTERWARDS = -1;
    private final int[] SINGLE_COLOR = new int[]{NO_COLOR_CHANGE_AFTERWARDS};

    private AvailableColors(String commando, int color) {
        this.commando = commando;
        this.colorcode = new int[]{color};
        this.milliseconds = SINGLE_COLOR;
    }

    String commando;
    int[] colorcode;
    int[] milliseconds;
}
