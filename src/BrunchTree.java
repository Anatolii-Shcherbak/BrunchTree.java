public class BrunchTree {

    public static void main(String[] args) {
        int root=45, temp=0, j =1, num = 0;
        String t = "";

        int[] leafs = {45, 27, 67, 36, 56, 15, 75, 31, 53, 39, 64};
        int[] balancenumber = new int[leafs.length];
        String[] position = new String[leafs.length];

        for(int i =0; i < leafs.length; i++){
            position[i] = String.valueOf(leafs[i]);
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

                for (int k = 0; k < minLength - 2 ; k++) {
                 if (position[j].charAt(k + 2) != position[i].charAt(k + 2)) {
                    t = "";
                    break;
                } else if(k + 2 == position[j].length() - 1){
                    if (leafs[i] > leafs[j]) {t += "R";} else {t += "L";}
                }
                }


                position[i] += t;

            }
            j=1;
            temp++;
        }


        for(int i =1; i < leafs.length; i++){
            for(int k =10; k >= i ; k-- ) {
                for (int l = 2; l < position[i].length(); l++) {
                    if (position[i].charAt(l) == position[k].charAt(l)) {
                        temp += 1;
                    } else {
                        break;

                    }
                    num +=temp;
                }

                //System.out.println(leafs[i] + "nums" + leafs[k]);
            }

            position[i] += "  balance:" + balancenumber[i];
        }


        for(int i =0; i < leafs.length; i++){
               System.out.println(position[i]);
        }
    }

}
