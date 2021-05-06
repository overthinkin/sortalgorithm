import java.util.Scanner;

class swap{
    public static void SWAP(int[] list, int x, int y){
        int temp;
        temp=list[x];
        list[x]=list[y];
        list[y]=temp;
    }
}

class select extends swap{

    public static void sort(int[] list){
        int i,j,least;
        for(i=0;i<(list.length)-1;i++){
            least = i;
            for(j = i+1; j<(list.length); j++){
                if(list[j] < list[least]) least=j;
            }
            SWAP(list,i,least);
        }
    }
}

class bubble extends swap{
public static void sort(int[] list){
    int i,j;
    for(i=(list.length)-1; i>0; i--){
        for(j=0;j<i;j++){
            if(list[j]>list[j+1]){
                SWAP(list, j, j+1);
            }
        }
    }
}
}

class insert{
    public static void sort(int[] list){
        int i,j,key;
        for(i=1; i<(list.length); i++){
            key = list[i];
            for(j=i-1; j>=0 && list[j]>key; j--){
                list[j+1] = list[j];
            }
            list[j+1]=key;
        }
    }
}

class shell{
    public static void inc_sort(int[] list,int first, int last, int gap){
        int i,j,key;
        for(i=first+gap;i<=last;i=i+gap){
            key=list[i];
            for(j=i-gap;j>=first&&key<list[j];j=j-gap){
                list[j+gap] =list[j];
            }
            list[j+gap]=key;
        }
    }
    public static void sort(int[] list){
        int i,gap;
        for(gap=(list.length)/2;gap>0;gap=gap/2){
            if((gap%2)==0) gap++;
            for(i=0;i<gap;i++)
                inc_sort(list,i,(list.length)-1,gap);
        }
    }
}

class makedata{
    public static void desc(int[] list){
        int n = list.length;
        for(int i=0;i<list.length;i++){
            list[i] = n;
            n--;
        }
    }
    public static void randnum(int[] list){
        for(int i=0;i<list.length;i++){
            int m = (int) (Math.random() * 999 +1);
            list[i]=m;
        }
    }
    public static void almost(int[] list){
        for(int i =0;i<(list.length)/3;i++){
            list[i]=i;
        }
        for(int i = (list.length)/3; i<list.length;i++){
            list[i] = (int) (Math.random() * 999 + ((list.length) / 3));
        }
    }
}

public class sort {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("배열의 크기를 입력하세요.");
        int n = scanner.nextInt();
        int[] arr = new int[n];
        System.out.println("배열 데이터의 생성방식을 고르세요.\n 내림차순(d) 랜덤(r) 일부오름차순정렬(a)");
        String k = scanner.next();
        if(k.equals("d")){
            makedata.desc(arr); }
        if(k.equals("r")){
            makedata.randnum(arr); }
        if(k.equals("a")){
            makedata.almost(arr); }
        System.out.println("배열의 현재 상태: ");
        for(int y :arr)
            System.out.println(y);
        System.out.println("배열의 정렬방식을 고르세요. \n 1.bubble \n 2.select \n 3.insert \n 4.shell");
        String choose = scanner.next();
        if (choose.equals("bubble")){
            long beforeTime = System.currentTimeMillis();
            bubble.sort(arr);
            long afterTime = System.currentTimeMillis();
            long secDiffTime = (afterTime - beforeTime)/1000;
            System.out.println("버블정렬 시간차이(m) : "+secDiffTime);
        }
        if (choose.equals("select")){
            long beforeTime = System.currentTimeMillis();
            select.sort(arr);
            long afterTime = System.currentTimeMillis();
            long secDiffTime = (afterTime - beforeTime)/1000;
            System.out.println("선택정렬 시간차이(m) : "+secDiffTime);
        }
        if (choose.equals("insert")){
            long beforeTime = System.currentTimeMillis();
            insert.sort(arr);
            long afterTime = System.currentTimeMillis();
            long secDiffTime = (afterTime - beforeTime)/1000;
            System.out.println("삽입정렬 시간차이(m) : "+secDiffTime);
        }
        if (choose.equals("shell")){
            long beforeTime = System.currentTimeMillis();
            shell.sort(arr);
            long afterTime = System.currentTimeMillis();
            long secDiffTime = (afterTime - beforeTime)/1000;
            System.out.println("쉘정렬 시간차이(m) : "+secDiffTime);
        }
        for(int z : arr)
            System.out.println(z + " ");

        scanner.close();
    }
}
