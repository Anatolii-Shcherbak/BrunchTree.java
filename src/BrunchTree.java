import static java.lang.Integer.parseInt;

public class BrunchTree {

    public static void main(String[] args) {
        int root=45, tempr=0, templ = 0,  j =1, num = 0, tl = 0, tlr = 0, tr = 0, trl = 0, contl, countr;
        String t = "";

        int[] leafs = {45, 27, 67, 36, 56, 15, 75, 31, 53, 39, 64};
        int[] balancenumber = new int[leafs.length];
        String[] position = new String[leafs.length];

       for(int i =0; i < leafs.length; i++){
            position[i] = "";
       //     System.out.println(position[i]);
        }
        position[0] += "Root";

        for(int i =1; i < leafs.length; i++){
           // if(i == temp){root = leafs[i]; temp--; position[i] += "Root";}

            if (leafs[i] > root){
                position[i] += "R";
            } else if (leafs[i] < root){
                position[i] += "L";
            }
            for(j=1; j < i; j++){
                boolean areCharactersEqual = true;

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
            j=1;
        }
        for(int i =1; i < leafs.length; i++) {
            for (int k = 3; k < leafs.length- 1; k++) {
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

        for(int i =0; i < leafs.length; i++){
               System.out.println(leafs[i] + position[i] + " balance:"  + balancenumber[i]);
        }
    }

}
