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
        int tempr=0, templ = 0,  tl = 0,  tr = 0, trR = 0, tlR = 0;
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
                if(i == 1){
                    tlR = Math.max(tl, tr);
                }
                if(i == 2){
                    trR = Math.max(tl, tr);
                }
                tempr = 0; templ = 0;

            }
            balancenumber[i] =   tr - tl;
            tr =0; tl = 0;
        }
        balancenumber[0] = trR - tlR;

    }

    public static void SystemPrint(int[] leafs, String[] position, int[]balancenumber){
        for(int i =0; i < leafs.length; i++){
        System.out.println(leafs[i] + position[i] + " balance:"  + balancenumber[i]);
    }
    }


    public static void main(String[] args) {

        int[] leafs = {45, 27, 67, 36, 56, 15, 75, 31, 53, 39, 64};
        int[] balancenumber = new int[leafs.length];
        String[] position = new String[leafs.length];

        CreateString(leafs, position);
        PlacePosition(leafs, position, leafs[0]);
        BalanceCheck(leafs, position, balancenumber, leafs[0]);
        SystemPrint(leafs, position, balancenumber);


        int[] leafsChange = new int[leafs.length + 1];
        System.arraycopy(leafs, 0, leafsChange, 0, leafs.length);
        leafsChange[leafsChange.length-1] = 32;
        String[] ChangePosition = new String[leafsChange.length];
        int[] balancenumber2 = new int[leafsChange.length];


        CreateString(leafsChange, ChangePosition);
        PlacePosition(leafsChange, ChangePosition, leafsChange[0]);
        BalanceCheck(leafsChange, ChangePosition, balancenumber2, leafsChange[0]);

        System.out.println("New Tree");
        SystemPrint(leafsChange, ChangePosition, balancenumber2);
    }

}
