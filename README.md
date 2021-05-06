# 정렬알고리즘  성능 비교하기

date: 2021/05/07

write by: 201800417 장진이

mission: ***배열을 다양한 정렬 방식을 사용하여 오름차순으로 정렬하기***

---

## **목차**

### **1. 코드 구조**

- make data

### 2. 정렬기능 별 원리 설명

- select sort
  - swap
- bubble sort
- insert sort
- shell sort

### 3. 메인

### 4. 결과 화면

### 5. 성능평가

### 6. 마무리

---

#### 1.코드구조

|           |  Sort.java  |           |
| :-------: | :---------: | :-------: |
| *Do_sort* | *make data* |  *main*   |
|  select   |   random    |   size    |
|  bubble   |    desc     | data type |
|  insert   |     asc     | sort type |
|   shell   |             |           |

*Sort.java* 코드는 세가지 부분으로 구성되어 있다.

- 정렬방법을 모아놓은 클래스 `Do_sort` 

- 배열 안 숫자의 초기 형태를 결정하는 클래스 `make data` 
- 위의 두 클래스를 사용할 수 있는 `main`

---

#### class makedata

```java
class makedata{
    public static void desc(int[] list){ //내림차순으로 배열을 만들기
        int n = list.length;
        for(int i=0;i<list.length;i++){ 
            list[i] = n; //가장 작은 인덱스부터 배열의 크기만큼의 수를 입력한다.
            n--; //배열 크기만큼의 수였던 n을 1씩 감소시킨다.
        }
    }
    public static void randnum(int[] list){//난수로 배열 만들기
        for(int i=0;i<list.length;i++){
            int m = (int) (Math.random() * 999 +1); //1부터 999까지의 정수 중에서 
           										   //난수를 발생시켜 변수 m에 대입한다.
            list[i]=m; //변수 m을 배열의 0번째 인덱스부터 입력한다.
        }
    }
    public static void someasc(int[] list){//일부분이 오름차순으로 정렬된 배열 만들기
        for(int i =0;i<(list.length)/3;i++){ //일부의 기준을 배열 길이의 3분의1로 하고 
            list[i]=i;//그만큼은 인덱스 그대로의 숫자가 입력되게 한다. 
            		//그렇게 하면 이부분은 오름차순으로 정렬된 것과 같다.
        }
        for(int i = (list.length)/3; i<list.length;i++){ //앞에서부터 3분의1이후의 인덱스에는
            list[i] = (int) (Math.random() * 999 + ((list.length) / 3));//난수를 발생시킨다.
        }
    }
}
```

---

#### 2.정렬기능 별 설명

#### select sort

```java
    public static void select_sort(int[] list){
        int i,j,least;
        for(i=0;i<(list.length)-1;i++){
            least = i;
            for(j = i+1; j<(list.length); j++){
                if(list[j] < list[least]) least=j;
            }
            SWAP(list,i,least);
        }
    }
```

선택 정렬은 배열에서 최소값을 찾아 맨 앞의 인덱스에 입력하는 것이다. 정렬하기 전에 생성된 배열은 이미 모든 인덱스에 값이 존재하므로, SWAP 함수를 만들어 temp라는 임시저장 공간에 원래 데이터를 잠시 저장해야한다.

- **swap**

```java
public static void SWAP(int[] list, int x, int y){
        int temp;
        temp=list[x];
        list[x]=list[y];
        list[y]=temp;
    }
```

---

#### bubble sort

```java
public static void bubble_sort(int[] list){
        int i,j;
        for(i=(list.length)-1; i>0; i--){
            for(j=0;j<i;j++){
                if(list[j]>list[j+1]){
                    SWAP(list, j, j+1);
                }
            }
        }
    }
```

버블정렬은 이웃한 인덱스의 값들끼리 비교하여 작은 값은 왼쪽으로 큰 값은 오른쪽으로 이동하는 방식이다. 버블정렬도 내부정렬이기 때문에 temp라는 임시저장 변수에 원래 데이터를 저장해야하므로 SWAP함수를 활용한다.

---

#### insert sort

```java
public static void insert_sort(int[] list){
    int i,j,key;
    for(i=1; i<(list.length); i++){
        key = list[i];
        for(j=i-1; j>=0 && list[j]>key; j--){
            list[j+1] = list[j];
        }
        list[j+1]=key;
    }
}
```

삽입 정렬은 맨 왼쪽부터 왼쪽의 값이 오른쪽 값보다 크면 우측으로 이동시킨다. 삽입정렬은 한 칸씩만 움직일 수 있어서 비교와 이동을 많이 하게되어 시간이 오래 걸린다는 단점이 있다. 그렇기 때문에 삽입 정렬과 유사하지만 멀리 이동할 수 있다면 효율적으로 해결 가능한 쉘 정렬을 해결책으로 쓸 수 있다.

---

#### shell sort

```java
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
public static void shell_sort(int[] list){
    int i,gap;
    for(gap=(list.length)/2;gap>0;gap=gap/2){
        if((gap%2)==0) gap++;
        for(i=0;i<gap;i++)
            inc_sort(list,i,(list.length)-1,gap);
    }
}
```

배열을 일정한 간격 (`gap`)만큼 나누어 삽입정렬을 한다. `gap`은 배열 크기의 절반부터 시작하여 1/2씩 줄여간다. 간격이 1이 되면 삽입 정렬 연산을 하는 것과 같다.

---

#### 3.main

```java
public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);

    System.out.println("배열의 크기를 입력하세요.");
    int n = scanner.nextInt();
    int[] arr = new int[n];
   
```

사용자에게 배열의 사이즈를 입력받아 그만큼의 배열 `arr`을 생성한다.

```java
 System.out.println("배열 데이터의 생성방식을 고르세요.\n 내림차순(d) 랜덤(r) 일부오름차순정렬(a)");
    String k = scanner.next();
    if(k.equals("d")){
        makedata.desc(arr); }
    if(k.equals("r")){
        makedata.randnum(arr); }
    if(k.equals("a")){
        makedata.someasc(arr); }
    System.out.println("배열의 현재 상태: ");
    for(int y :arr)
        System.out.println(y);
    
```

배열 `arr`에 값을 입력하기 위해 내림차순, 랜덤, 일부 정렬 중에 선택하면 

데이터 타입에 맞는 오름차순으로 정렬되지 않은 현재 배열 상태를 출력한다.

```java
System.out.println("배열의 정렬방식을 고르세요. \n 1.bubble \n 2.select \n 3.insert \n 4.shell");
    String choose = scanner.next();
    if (choose.equals("bubble")){
        long beforeTime = System.currentTimeMillis();
        Do_sort.bubble_sort(arr);
        long afterTime = System.currentTimeMillis();
        long secDiffTime = (afterTime - beforeTime)/1000;
        System.out.println("버블정렬 시간차이(m) : "+secDiffTime);
    }
    if (choose.equals("select")){
        long beforeTime = System.currentTimeMillis();
        Do_sort.select_sort(arr);
        long afterTime = System.currentTimeMillis();
        long secDiffTime = (afterTime - beforeTime)/1000;
        System.out.println("선택정렬 시간차이(m) : "+secDiffTime);
    }
    if (choose.equals("insert")){
        long beforeTime = System.currentTimeMillis();
        Do_sort.insert_sort(arr);
        long afterTime = System.currentTimeMillis();
        long secDiffTime = (afterTime - beforeTime)/1000;
        System.out.println("삽입정렬 시간차이(m) : "+secDiffTime);
    }
    if (choose.equals("shell")){
        long beforeTime = System.currentTimeMillis();
        Do_sort.shell_sort(arr);
        long afterTime = System.currentTimeMillis();
        long secDiffTime = (afterTime - beforeTime)/1000;
        System.out.println("쉘정렬 시간차이(m) : "+secDiffTime);
    }
    
```

정렬 방식을 사용자로부터 입력받아 실행한다.

정렬 알고리즘을 실행하기 전 후에 시간을 기록하여 그 차를 실행한 시간으로 계산한다.

```java
for(int z : arr)
        System.out.println(z);

    scanner.close();
}
```

정렬이 끝난 배열을 for each 반복문을 통해 출력하고 입력을 종료한다.

---

#### 4. 결과 예시

```java
"C:\Program Files\Java\jdk-14.0.2\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2020.3.2\lib\idea_rt.jar=5171:C:\Program Files\JetBrains\IntelliJ IDEA 2020.3.2\bin" -Dfile.encoding=UTF-8 -classpath C:\Users\jangj\IdeaProjects\sort\out\production\sort sort
배열의 크기를 입력하세요.
50
배열 데이터의 생성방식을 고르세요.
 내림차순(d) 랜덤(r) 일부오름차순정렬(a)
a
배열의 현재 상태: 
0
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
849
254
552
881
552
340
563
502
991
784
417
355
413
735
861
397
155
876
767
301
123
724
159
569
776
505
326
75
574
36
346
88
137
190
배열의 정렬방식을 고르세요. 
 1.bubble 
 2.select 
 3.insert 
 4.shell
shell
쉘정렬 시간차이(m) : 0
0
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
36
75
88
123
137
155
159
190
254
301
326
340
346
355
397
413
417
502
505
552
552
563
569
574
724
735
767
776
784
849
861
876
881
991

Process finished with exit code 0

```



---

#### 5. 성능평가

너무 큰 수를 입력하면 낡은 노트북이 고장날까봐 못했습니다....

1천단위의 사이즈에도 시간은 0으로 나와서 수치로 성능을 비교할 수 없었습니다.

네 정렬 방식의 성능 특징에 대하여 설명하겠습니다.

- **select**

  시간복잡도: 
  $$
  O(n^2)
  $$

  - 장점: 데이터의 이동 횟수가 확실하게 결정된다.

  - 단점: 불필요하게 자기 자신이 이동될 수 있다. 만약 여러 인덱스에 같은 값이 있으면 상대적인 위치가 변경될 수 있다.

- **bubble**

  시간복잡도: 
  $$
  O(n^2)
  $$

  - 장점: 버블정렬은 최선, 최악, 평균이 모두 같은 시간복잡도를 가진다. 
  - 단점: 한 개의 값이 끝으로 이동하려고 할때 그 안의 모든 인덱스의 값들과 비교되어야한다. 

- **insert**

  시간복잡도: 
  $$
  O(n^2)
  $$

  - 장점: 안정적이다. 데이터의 크기가 작을 수록 성능이 좋다. 이유는 데이터를 비교하고, 이동하는데에 소요되는 메모리가 크기 때문이다. 데이터의 대부분이 정렬되어 있을수록 효율적으로 작동한다.
  - 단점: 데이터의 양이 크면 비효율적인 정렬방식이다.  데이터가 정렬 방식과 반대로 정렬되어있을 때 가장 불리하다.

- **shell**

  시간복잡도: 
  $$
  O(n log{_2}{n})
  $$

  - 장점: `gap`만큼 나눠진 배열에서 데이터가 정렬될 때 삽입정렬보다 더 큰 거리를 이동할 수 있다. 이렇게 여러 부분 배열에서 일부분들이 정렬되었기 때문에 빠르게 삽입정렬을 수행할 수 있다.
  - 단점: 크기가 큰 데이터를 정렬하기에는 비효율적이다.

---

#### 6.마무리

1. 메인에서 실행할 때 while 문 안에서 여러번 실행할 수 있었으면 코드를 매번 다시 실행하는 것보다 편하게 실험할 수 있었을텐데 아쉽다.
2. 알고리즘 성능비교에 적합한 시간 함수를 찾아야한다. 웬만하면 다 0으로 나와서 비교할 수가 없다.





