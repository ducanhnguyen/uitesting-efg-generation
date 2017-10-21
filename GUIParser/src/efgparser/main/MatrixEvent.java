package efgparser.main;

import guitreeparser.object.EfgEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ducanhnguyen
 */
public class MatrixEvent {

    private int eventMatrixSize = 0;
    private int[][] eventMatrix = new int[MAX_EVENT][MAX_EVENT];
    private List<EfgEvent> efgEvents = new ArrayList<>();

    public void setValue(int hang, int cot, int numOfEvent) {
        eventMatrix[hang][cot] = numOfEvent;
    }

    public String exportEventMatrixToString() {
        System.out.println("eventMatrixSize = " + eventMatrixSize);

        System.out.println("EVENTS");
        for (EfgEvent event : efgEvents) {
            System.out.println("+ " + event);
        }

        String description = "";
        for (int i = 0; i < eventMatrixSize; i++) {
            description += "\n";
            for (int j = 0; j < eventMatrixSize; j++) {
                description += eventMatrix[i][j] + " ";
            }
        }
        return description;
    }

    public int[][] getEventMatrix() {
        return eventMatrix;
    }

    public int getEventMatrixSize() {
        return eventMatrixSize;
    }

    public void setEventMatrixSize(int eventMatrixSize) {
        this.eventMatrixSize = eventMatrixSize;
    }

    public void setEventMatrix(int[][] eventMatrix) {
        this.eventMatrix = eventMatrix;
    }

    public void setEfgEvents(List<EfgEvent> efgEvents) {
        this.efgEvents = efgEvents;
    }

    public List<EfgEvent> getEfgEvents() {
        return efgEvents;
    }
    public static int MAX_EVENT = 1000;
}
