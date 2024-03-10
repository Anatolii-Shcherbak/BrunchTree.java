import java.awt.*;

import static java.lang.Integer.parseInt;

public class BrunchTree {

    public static void CreateString(int[] leafs, String[] position) {
        for (int i = 0; i < leafs.length; i++) {
            position[i] = "";
        }
        position[0] += "Root";
    }

    public static void PlacePosition(int[] leafs, String[] position, int root) {
        String t = "";
        for(int i =1; i < leafs.length; i++){
            if (leafs[i] > root){
                position[i] += "R";
            } else if (leafs[i] < root){
                position[i] += "L";
            }
            for(int j=1; j < i; j++){
                int minLength = Math.min(position[j].length(), position[i].length());

                for (int k = 0; k < minLength ; k++) {
                    if (position[j].charAt(k) != position[i].charAt(k)) {
                       t = "";
                        break;
                    } else if(k  == position[j].length() - 1){
                        if (leafs[i] > leafs[j]) {t += "R";} else {t += "L";}
                    }
                }
                position[i] += t;
            }
        }
    }
    public static void BalanceCheck(int[] leafs, String[] position, int[]balancenumber,  int root){
        int tempr=0, templ = 0,  tl = 0,  tr = 0;
        for(int i =1; i < leafs.length - 1; i++) {
            for (int k = 3; k < leafs.length; k++) {
                if(i == k) { k++;}
                for (int l = 0; l < position[i].length(); l++) {
                    if(position[k].length() < position[i].length()){  break;}
                    if (position[i].charAt(l) == position[k].charAt(l)) {

                        int cut = position[i].length();
                        String result = (position[k].substring(cut));
                        if(l == position[i].length() -1){
                            if(position[k].charAt(l + 1) == 'R' && (!result.contains("L") || !result.contains("R"))) {
                                tempr=1;
                            }
                            if(position[k].charAt(l + 1) == 'L' && (!result.contains("R") || !result.contains("L")))  {
                                templ=1;
                            }
                        }
                    } else {
                        break;
                    }

                }
                tr +=tempr;
                tl += templ;
                tempr = 0; templ = 0;

            }
            balancenumber[i] =   tr - tl;
            tr =0; tl = 0;
        }
    }

    public static void SystemPrint(int[] leafs, String[] position, int[]balancenumber){
        for(int i =0; i < leafs.length; i++){
        System.out.println(leafs[i] + position[i] + " balance:"  + balancenumber[i]);
    }
    }

    public static void Balancing(int[] leafsChange, String[] ChangePosition) {
        int temp = 1000, num = 0, check = 0;
        boolean T = false;
        for (int i = 3; i < leafsChange.length; i++) {

            if (ChangePosition[i].charAt(0) == 'L' && ChangePosition[i].charAt(1) == 'R') {
                if (leafsChange[i] < temp) {
                    temp = leafsChange[i];
                    num = i;
                }
            }
        }
        for (int i = 3; i < leafsChange.length - 1; i++) {
            if (ChangePosition[i].length() < ChangePosition[num].length()) {
                continue;
            }
            if (i == num) {
                continue;
            }
            for (int l = 0; l < ChangePosition[num].length(); l++) {
                if (ChangePosition[num].charAt(l) == ChangePosition[i].charAt(l)) {
                    T = true;
                } else {
                    T = false;
                    break;
                }

            }
            if (check == 0 && T) {
                check = i;
                T = false;
            }
        }

        ChangePosition[check] = ChangePosition[num];

        for (int i = 1; i < leafsChange.length; i++) {
            if (ChangePosition[i].length() > 1) {
                continue;
            }
            if (ChangePosition[i].contains("L")) {
                check = i;
            }
        }
        ChangePosition[num] = ChangePosition[check];

        for (int i = 1; i < leafsChange.length; i++) {
            if (i == num) {
                continue;
            }
            if (!ChangePosition[i].contains("R")) {
                T = true;
            }
            if (T) {
                ChangePosition[i] += "L";
            }
            T = false;
        }

//Made by Anatolii Shcherbak
        for (int i = 1; i < leafsChange.length; i++) {
            if (ChangePosition[i].length() < 4) {
                continue;
            }
            if (ChangePosition[i].charAt(0) == 'L') {
                T = true;
            }
            if (T) {
                ChangePosition[i] = ChangePosition[1] + "R";
            }
            T = false;
        }

        num = 0;
        check = 0;
        for (int i = 1; i < leafsChange.length; i++) {
            if (ChangePosition[i].length() < 4) {
                continue;
            }
            if (ChangePosition[i].charAt(0) == 'R') {
                T = true;
            }
            if (T && (num == 0)) {
                num = i;
            } else if (T && num != 0) {
                check = i;
            }
            T = false;
        }
        int ch = 0;
        if (leafsChange[check] > leafsChange[num]) {
            ChangePosition[num] = ChangePosition[check];
        } else {
            ch = check;
            check = num;
            num = ch;
            ChangePosition[num] = ChangePosition[check];
        }
        ch = 0;

//Made by Anatolii Shcherbak
        for (int i = 1; i < leafsChange.length; i++) {
            if (ChangePosition[i].length() != 3) {
                continue;
            }
            if (ChangePosition[i].charAt(0) == 'R') {
                T = true;
            }
            if (T && leafsChange[i] > ch) {
                ch = i;
            }
            T = false;
        }
        ChangePosition[check] = ChangePosition[ch];
        ChangePosition[ch] += "R";
    }

    public static void main(String[] args) {
        int[] leafs = {45, 27, 67, 36, 56, 15, 75, 31, 53, 39, 64};
        int[] balancenumber = new int[leafs.length];
        String[] position = new String[leafs.length];

        CreateString(leafs, position);
        PlacePosition(leafs, position, leafs[0]);
        BalanceCheck(leafs, position, balancenumber, leafs[0]);
        System.out.println("Given Balanced Tree");
        SystemPrint(leafs, position, balancenumber);


       int[] leafsChange = {45,27, 67, 36, 56, 15, 75, 31, 53, 39, 64, 32,  42 , 52, 62};
      //  int[] leafsChange = new int[leafs.length + 1];
      // System.arraycopy(leafs, 0, leafsChange, 0, leafs.length);
      //  leafsChange[leafsChange.length - 1] = 32;

        String[] ChangePosition = new String[leafsChange.length];
        int[] balancenumber2 = new int[leafsChange.length];


        System.out.println("New Tree");
        CreateString(leafsChange, ChangePosition);
        PlacePosition(leafsChange, ChangePosition, leafsChange[0]);
        BalanceCheck(leafsChange, ChangePosition, balancenumber2, leafsChange[0]);
        SystemPrint(leafsChange, ChangePosition, balancenumber2);

          Balancing(leafsChange,ChangePosition);
          BalanceCheck(leafsChange, ChangePosition, balancenumber2, leafsChange[0]);
        System.out.println("Auto Balanced Tree");
        SystemPrint(leafsChange, ChangePosition, balancenumber2);
    }

}
//Made by Anatolii Shcherbak
