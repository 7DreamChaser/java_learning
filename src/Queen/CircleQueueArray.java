package Queen;

import java.util.Scanner;

public class CircleQueueArray {
    public static void main(String[] args) {
        //创建一个队列
        CircleArray queue = new CircleArray(4);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出队列");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头数据");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int val = scanner.nextInt();
                    queue.addQueue(val);
                    break;
                case 'g':
                    try{
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try{
                        int res = queue.headQueue();
                        System.out.printf("取出的头数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
            }
        }
        System.out.println("程序退出");
    }
}


class CircleArray {
    private int maxSize;//数组的最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;//该数据用于存储数据，模拟队列

    public CircleArray(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        //front 和 rear 初始值为0，因此可以不初始化
    }

    //判断队列是否满
    public boolean isFull(){
        return (rear+1)%maxSize==front;
    }

    //判断数列是否为空
    public boolean isEmpty(){
        return rear==front;
    }

    //添加数据到队列
    public void addQueue(int n){
        //先判断队列是否满
        if(isFull())
        {
            System.out.println("队列已满，不能添加数据");
            return;
        }
        //直接在末尾位置添加元素
        arr[rear] = n;
        rear = (rear+1)%maxSize;
    }

    //数据出队列
    public  int getQueue(){
        //判断是否为空
        if(isEmpty()){
            //通过抛出异常来处理
            throw new RuntimeException("队列空，不能取数据");
        }
        int temp = arr[front];
        front = (front+1)%maxSize;

        return temp;
    }

    //显示队列的所有数据
    public void showQueue(){
        //遍历
        if(isEmpty()){
            System.out.println("队列空，没有数据");
            return;
        }
        for (int i = front; i <front+len() ; i++) {
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
        }

    }

    //求出当前数列有效元素的个数
    public int len(){
        return (rear+maxSize-front)%maxSize;
    }

    //显示队列头数据
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列空，没有数据");
        }
        return arr[front];
    }

}
