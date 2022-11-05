package medium;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author ：zhumingyuan
 * @description：TODO
 * @date ：2022/11/1 9:28 PM
 */
public class WaterandJugProblem365 {

    static class State {
        int jug1;
        int jug2;

        public State(int jug1, int jug2) {
            this.jug1 = jug1;
            this.jug2 = jug2;
        }

        public String toString() {
            return this.jug1 + "-" + this.jug2;
        }
    }


    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        if (jug1Capacity + jug2Capacity == targetCapacity) {
            return true;
        }

        if (jug1Capacity + jug2Capacity < targetCapacity) {
            return false;
        }

        if (jug1Capacity % 2 == 0 && jug2Capacity%2==0 && targetCapacity%2 != 0) {
            return false;
        }


        Queue<State> queue = new LinkedList<>();
        State start = new State(0, 0);
        queue.add(start);
        Set<String> visited = new HashSet<>();
        visited.add(start.toString());
        int newJug1, newJug2;
        String vFlag;
        while (!queue.isEmpty()) {
            State curState = queue.poll();
            if (curState.jug1 == targetCapacity || curState.jug2 == targetCapacity ||
                    curState.jug1 + curState.jug2 == targetCapacity) {
                return true;
            }

            // 分情况
            // 1. fill jug1
            newJug1 = jug1Capacity;
            newJug2 =  curState.jug2;
            vFlag = newJug1+"-"+newJug2;
            if (!visited.contains(vFlag)) {
                State s1 = new State(newJug1, newJug2);
                visited.add(vFlag);
                queue.add(s1);
            }

            // 2. fill jug2
            newJug1 = curState.jug1;
            newJug2 =  jug2Capacity;
            vFlag = newJug1+"-"+newJug2;
            if (!visited.contains(vFlag)) {
                State s2 = new State(newJug1, newJug2);
                visited.add(vFlag);
                queue.add(s2);
            }
            // 3. jug1 empty
            newJug1 = 0;
            newJug2 =  curState.jug2;
            vFlag = newJug1+"-"+newJug2;
            if (!visited.contains(vFlag)) {
                State s3 = new State(newJug1, newJug2);
                visited.add(vFlag);
                queue.add(s3);
            }
            // 4 jug2 empty
            newJug1 = curState.jug1;
            newJug2 = 0;
            vFlag = newJug1+"-"+newJug2;
            if (!visited.contains(vFlag)) {
                State s4 = new State(newJug1, newJug2);
                visited.add(vFlag);
                queue.add(s4);
            }
            // 5. jug1 -> jug2
            newJug1 = Math.max(0, curState.jug1 - (jug2Capacity - curState.jug2));
            newJug2 = Math.min(jug2Capacity, curState.jug1 + curState.jug2);
            vFlag = newJug1+"-"+newJug2;
            if (!visited.contains(vFlag)) {
                State s5 = new State(newJug1, newJug2);
                visited.add(vFlag);
                queue.add(s5);
            }
            // 6. jug2 -> jug1
            newJug1 = Math.min(jug1Capacity, curState.jug1 + curState.jug2);
            newJug2 = Math.max(0, curState.jug2 - (jug1Capacity - curState.jug1));
            vFlag = newJug1+"-"+newJug2;
            if (!visited.contains(vFlag)) {
                State s6 = new State(newJug1, newJug2);
                visited.add(vFlag);
                queue.add(s6);
            }

        }
        return false;
    }

    /**
     * 104659
     * 104677
     * 142424
     * @param args
     */
    public static void main(String[] args) {
        WaterandJugProblem365 w = new WaterandJugProblem365();
        Boolean r = w.canMeasureWater(104659,104677, 142424);
        System.out.println(r);
    }

}
