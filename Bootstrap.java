public class Bootstrap {
    private static int[][] Bootstrap(int[] array,int B){
        int len = array.length;
        int[][] replicate = new int[B][len];
        for(int i=0;i<B;i++){
            for(int j=0;j<len;j++){
                RandomizeArray(array);
                replicate[i][j] =array[0];
            }
        }
        return replicate;
    }

    private static  int[] BalancedBootstrap(int[] arr,int B){
        int[][] array = Bootstrap(arr,B);
        int len = array.length*array[0].length;
        int[] tmp = new int[len];
        int h = 0;
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array[0].length;j++){
                tmp[h] = array[i][j];
                h++;
            }
        }
        RandomizeArray(tmp);
        return tmp;
    }

    public static int[] RandomizeArray(int[] array){
        Random rgen = new Random();  // Random number generator

        for (int i=0; i<array.length; i++) {
            int randomPosition = rgen.nextInt(array.length);
            int temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }

        return array;
    }

    private static double Variance(int[] array){
        double average = 0;
        for(int i=0;i<array.length;i++) average += array[i];
        average /= array.length;
        double var = 0;
        for(int i=0;i<array.length;i++) var += (average -array[i])*(average -array[i]);
        return var/array.length;
    }

    public static void main(String[] arg0){
        int[] test = new int[]{1,2,3,4,5,6,7,8,9};
        int Rlen = 3;//3 cpoies

        System.out.println("Bootstrap");
        //Bootstrap
        int[][] res = Bootstrap(test,Rlen);
        for(int i=0;i<Rlen;i++){
            int[] array = new int[test.length];
            for(int j=0;j<test.length;j++) array[j] = res[i][j];
            System.out.println(Arrays.toString(array));
        }
        for(int i=0;i<Rlen;i++){
            int[] array = new int[test.length];
            for(int j=0;j<test.length;j++) array[j] = res[i][j];
            System.out.println(Variance(array));
        }


        //balanced
        int[] balancedres = BalancedBootstrap(test,Rlen);
        System.out.println("Balanced");
        int h = 0;
        int count = 0;
        double BalancedVar = 0;
        for(int i=0;;i++){
            int[] array = new int[test.length];
            for(int j=0;j<test.length;j++) {
                array[j] = balancedres[h];
                h++;
            }
            count++;
            BalancedVar += Variance(array);
            System.out.println(Arrays.toString(array));
            if(count == Rlen) break;
        }
        System.out.println(BalancedVar/Rlen);
    }
}
